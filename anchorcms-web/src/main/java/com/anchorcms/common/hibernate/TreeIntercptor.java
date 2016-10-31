package com.anchorcms.common.hibernate;

import com.anchorcms.common.pojo.HTreeInfo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-10-31
 * @Desc 树形结构拦截器
 */
public class TreeIntercptor extends EmptyInterceptor implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(TreeIntercptor.class);
    private ApplicationContext appContext;
    private SessionFactory sessionFactory;
    public static final String SESSION_FACTORY = "sessionFactory";
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = appContext;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-10-31 16:25
     * @Desc 获取session工厂
     */
    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = (SessionFactory) appContext.getBean(SESSION_FACTORY,
                    SessionFactory.class);
            if (sessionFactory == null) {
                throw new IllegalStateException("not found bean named '"
                        + SESSION_FACTORY
                        + "',please check you spring config file.");
            }
        }
        return sessionFactory;
    }
    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-10-31 16:36
     * @Desc 保存
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
                          String[] propertyNames, Type[] types) {
        if (entity instanceof HTreeInfo) {
            HTreeInfo<?> tree = (HTreeInfo<?>) entity;
            Number parentId = tree.getParentId();
            String beanName = tree.getClass().getName();
            Session session = getSession();
            FlushMode model = session.getFlushMode();
            session.setFlushMode(FlushMode.MANUAL);
            Integer myPosition;
            if (parentId != null) {
                // 如果父节点不为null，则获取节点的右边位置
                String hql = "select bean." + tree.getRgtName() + " from "
                        + beanName + " bean where bean.id=:pid";
                myPosition = ((Number) session.createQuery(hql).setParameter(
                        "pid", parentId).uniqueResult()).intValue();
                String hql1 = "update " + beanName + " bean set bean."
                        + tree.getRgtName() + " = bean." + tree.getRgtName()
                        + " + 2 WHERE bean." + tree.getRgtName()
                        + " >= :myPosition";
                String hql2 = "update " + beanName + " bean set bean."
                        + tree.getLftName() + " = bean." + tree.getLftName()
                        + " + 2 WHERE bean." + tree.getLftName()
                        + " >= :myPosition";
                if (!StringUtils.isBlank(tree.getTreeCondition())) {
                    hql1 += " and (" + tree.getTreeCondition() + ")";
                    hql2 += " and (" + tree.getTreeCondition() + ")";
                }
                session.createQuery(hql1)
                        .setParameter("myPosition", myPosition).executeUpdate();
                session.createQuery(hql2)
                        .setParameter("myPosition", myPosition).executeUpdate();
            } else {
                // 否则查找最大的右边位置
                String hql = "select max(bean." + tree.getRgtName() + ") from "
                        + beanName + " bean";
                if (!StringUtils.isBlank(tree.getTreeCondition())) {
                    hql += " where " + tree.getTreeCondition();
                }
                Number myPositionNumber = (Number) session.createQuery(hql)
                        .uniqueResult();
                // 如不存在，则为0
                if (myPositionNumber == null) {
                    myPosition = 1;
                } else {
                    myPosition = myPositionNumber.intValue() + 1;
                }
            }
            session.setFlushMode(model);
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(tree.getLftName())) {
                    state[i] = myPosition;
                }
                if (propertyNames[i].equals(tree.getRgtName())) {
                    state[i] = myPosition + 1;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-10-31 16:36
     * @Desc 刷新
     */
    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
                                Object[] currentState, Object[] previousState,
                                String[] propertyNames, Type[] types) {
        if (!(entity instanceof HTreeInfo)) {
            return false;
        }
        HTreeInfo<?> tree = (HTreeInfo<?>) entity;
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyNames[i].equals(tree.getParentName())) {
                HTreeInfo<?> preParent = (HTreeInfo<?>) previousState[i];
                HTreeInfo<?> currParent = (HTreeInfo<?>) currentState[i];
                return updateParent(tree, preParent, currParent);
            }
        }
        return false;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-10-31 16:38
     * @Desc 更新父节点
     */
    private boolean updateParent(HTreeInfo<?> tree,HTreeInfo<?> preParent, HTreeInfo<?> currParent) {
        // 都为空、或都不为空且相等时，不作处理
        if ((preParent == null && currParent == null)
                || (preParent != null && currParent != null && preParent
                .getId().equals(currParent.getId()))) {
            return false;
        }
        String beanName = tree.getClass().getName();
        if (log.isDebugEnabled()) {
            log.debug("update Tree {}, id={}, "
                    + "pre-parent id={}, curr-parent id={}", new Object[] {
                    beanName, tree.getId(),
                    preParent == null ? null : preParent.getId(),
                    currParent == null ? null : currParent.getId() });
        }
        Session session = getSession();
        // 保存刷新模式，并设置成手动刷新
        FlushMode model = session.getFlushMode();
        session.setFlushMode(FlushMode.MANUAL);
        // 先空出位置。当前父节点存在时，才需要空出位置。
        Integer currParentRgt;
        if (currParent != null) {
            // 获得节点跨度
            String hql = "select bean." + tree.getLftName() + ",bean."
                    + tree.getRgtName() + " from " + beanName
                    + " bean where bean.id=:id";
            Object[] position = (Object[]) session.createQuery(hql)
                    .setParameter("id", tree.getId()).uniqueResult();
            int nodeLft = ((Number) position[0]).intValue();
            int nodeRgt = ((Number) position[1]).intValue();
            int span = nodeRgt - nodeLft + 1;
            log.debug("current node span={}", span);

            // 获得当前父节点右位置
            Object[] currPosition = (Object[]) session.createQuery(hql)
                    .setParameter("id", currParent.getId()).uniqueResult();
            int currParentLft = ((Number) currPosition[0]).intValue();
            currParentRgt = ((Number) currPosition[1]).intValue();
            log.debug("current parent lft={} rgt={}", currParentLft,
                    currParentRgt);

            // 空出位置
            String hql1 = "update " + beanName + " bean set bean."
                    + tree.getRgtName() + " = bean." + tree.getRgtName()
                    + " + " + span + " WHERE bean." + tree.getRgtName()
                    + " >= :parentRgt";
            String hql2 = "update " + beanName + " bean set bean."
                    + tree.getLftName() + " = bean." + tree.getLftName()
                    + " + " + span + " WHERE bean." + tree.getLftName()
                    + " >= :parentRgt";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql1 += " and (" + tree.getTreeCondition() + ")";
                hql2 += " and (" + tree.getTreeCondition() + ")";
            }
            session.createQuery(hql1).setInteger("parentRgt", currParentRgt)
                    .executeUpdate();
            session.createQuery(hql2).setInteger("parentRgt", currParentRgt)
                    .executeUpdate();
            log.debug("vacated span hql: {}, {}, parentRgt={}", new Object[] {
                    hql1, hql2, currParentRgt });
        } else {
            // 否则查找最大的右边位置
            String hql = "select max(bean." + tree.getRgtName() + ") from "
                    + beanName + " bean";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql += " where " + tree.getTreeCondition();
            }
            currParentRgt = ((Number) session.createQuery(hql).uniqueResult())
                    .intValue();
            currParentRgt++;
            log.debug("max node left={}", currParentRgt);
        }

        // 再调整自己
        String hql = "select bean." + tree.getLftName() + ",bean."
                + tree.getRgtName() + " from " + beanName
                + " bean where bean.id=:id";
        Object[] position = (Object[]) session.createQuery(hql).setParameter(
                "id", tree.getId()).uniqueResult();
        int nodeLft = ((Number) position[0]).intValue();
        int nodeRgt = ((Number) position[1]).intValue();
        int span = nodeRgt - nodeLft + 1;
        if (log.isDebugEnabled()) {
            log.debug("before adjust self left={} right={} span={}",
                    new Object[] { nodeLft, nodeRgt, span });
        }
        int offset = currParentRgt - nodeLft;
        hql = "update " + beanName + " bean set bean." + tree.getLftName()
                + "=bean." + tree.getLftName() + "+:offset, bean."
                + tree.getRgtName() + "=bean." + tree.getRgtName()
                + "+:offset WHERE bean." + tree.getLftName()
                + " between :nodeLft and :nodeRgt";
        if (!StringUtils.isBlank(tree.getTreeCondition())) {
            hql += " and (" + tree.getTreeCondition() + ")";
        }
        session.createQuery(hql).setParameter("offset", offset).setParameter(
                "nodeLft", nodeLft).setParameter("nodeRgt", nodeRgt)
                .executeUpdate();
        if (log.isDebugEnabled()) {
            log.debug("adjust self hql: {}, offset={}, nodeLft={}, nodeRgt={}",
                    new Object[] { hql, offset, nodeLft, nodeRgt });
        }

        // 最后删除（清空位置）
        String hql1 = "update " + beanName + " bean set bean."
                + tree.getRgtName() + " = bean." + tree.getRgtName() + " - "
                + span + " WHERE bean." + tree.getRgtName() + " > :nodeRgt";
        String hql2 = "update " + beanName + " bean set bean."
                + tree.getLftName() + " = bean." + tree.getLftName() + " - "
                + span + " WHERE bean." + tree.getLftName() + " > :nodeRgt";
        if (!StringUtils.isBlank(tree.getTreeCondition())) {
            hql1 += " and (" + tree.getTreeCondition() + ")";
            hql2 += " and (" + tree.getTreeCondition() + ")";
        }
        session.createQuery(hql1).setParameter("nodeRgt", nodeRgt)
                .executeUpdate();
        session.createQuery(hql2).setParameter("nodeRgt", nodeRgt)
                .executeUpdate();
        if (log.isDebugEnabled()) {
            log.debug("clear span hql1:{}, hql2:{}, nodeRgt:{}", new Object[] {
                    hql1, hql2, nodeRgt });
        }
        session.setFlushMode(model);
        return true;
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-10-31 16:38
     * @Desc 删除
     */
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
                         String[] propertyNames, Type[] types) {
        if (entity instanceof HTreeInfo) {
            HTreeInfo<?> tree = (HTreeInfo<?>) entity;
            String beanName = tree.getClass().getName();
            Session session = getSession();
            FlushMode model = session.getFlushMode();
            session.setFlushMode(FlushMode.MANUAL);
            String hql = "select bean." + tree.getLftName() + " from "
                    + beanName + " bean where bean.id=:id";
            Integer myPosition = ((Number) session.createQuery(hql)
                    .setParameter("id", tree.getId()).uniqueResult())
                    .intValue();
            String hql1 = "update " + beanName + " bean set bean."
                    + tree.getRgtName() + " = bean." + tree.getRgtName()
                    + " - 2 WHERE bean." + tree.getRgtName() + " > :myPosition";
            String hql2 = "update " + beanName + " bean set bean."
                    + tree.getLftName() + " = bean." + tree.getLftName()
                    + " - 2 WHERE bean." + tree.getLftName() + " > :myPosition";
            if (!StringUtils.isBlank(tree.getTreeCondition())) {
                hql1 += " and (" + tree.getTreeCondition() + ")";
                hql2 += " and (" + tree.getTreeCondition() + ")";
            }
            session.createQuery(hql1).setInteger("myPosition", myPosition)
                    .executeUpdate();
            session.createQuery(hql2).setInteger("myPosition", myPosition)
                    .executeUpdate();
            session.setFlushMode(model);
        }
    }
}
