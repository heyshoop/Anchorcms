package com.anchorcms.cms.dao.assist.impl;

import java.util.List;

import com.anchorcms.cms.dao.assist.CommentDao;
import com.anchorcms.cms.model.assist.CmsComment;
import com.anchorcms.common.hibernate.Finder;
import com.anchorcms.common.hibernate.HibernateBaseDao;
import com.anchorcms.common.page.Pagination;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDaoImpl extends HibernateBaseDao<CmsComment, Integer>
		implements CommentDao {
	public Pagination getPage(Integer siteId, Integer contentId,
							  Integer greaterThen, Boolean checked, Boolean recommend,
							  boolean desc, int pageNo, int pageSize, boolean cacheable) {
		Finder f = getFinder(siteId, contentId,null,null,null,greaterThen, checked,
				recommend, desc, cacheable);
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<CmsComment> getList(Integer siteId, Integer contentId,
			Integer parentId,Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int count, boolean cacheable) {
		Finder f = getFinder(siteId, contentId,parentId,null,null,greaterThen, checked,
				recommend, desc, cacheable);
		f.setMaxResults(count);
		return find(f);
	}
	public Pagination getPageForMember(Integer siteId, Integer contentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, int pageNo, int pageSize,boolean cacheable){
		Finder f = getFinder(siteId, contentId,null, toUserId,fromUserId,greaterThen, checked,
				recommend, desc, cacheable);
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<CmsComment> getListForDel(Integer siteId, Integer userId,
			Integer commentUserId, String ip){
		Finder f = Finder.create("from CmsComment bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		if(commentUserId!=null){
			f.append(" and bean.commentUser.userId=:commentUserId");
			f.setParam("commentUserId", commentUserId);
		}
		if(userId!=null){
			f.append(" and bean.content.user.userId=:fromUserId");
			f.setParam("fromUserId", userId);
		}
		f.setCacheable(false);
		return find(f);
	}

	private Finder getFinder(Integer siteId, Integer contentId,
			Integer parentId,Integer toUserId,Integer fromUserId,
			Integer greaterThen, Boolean checked, Boolean recommend,
			boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsComment bean where 1=1");
		if(parentId!=null){
			f.append(" and bean.parent.commentId=:parentId");
			f.setParam("parentId", parentId);
		}else if (contentId != null) {
			//按照内容ID来查询对内容的直接评论
			f.append(" and (bean.content.commentId=:contentId and bean.parent is null )");
			f.setParam("contentId", contentId);
		} else if (siteId != null) {
			f.append(" and bean.site.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		if(toUserId!=null){
			f.append(" and bean.commentUser.userId=:commentUserId");
			f.setParam("commentUserId", toUserId);
		}else if(fromUserId!=null){
			f.append(" and bean.content.user.userId=:fromUserId");
			f.setParam("fromUserId", fromUserId);
		}
		if (greaterThen != null) {
			f.append(" and bean.ups>=:greatTo");
			f.setParam("greatTo", greaterThen);
		}
		if (checked != null) {
			f.append(" and bean.isChecked=:checked");
			f.setParam("checked", checked);
		}
		if(recommend!=null){
			f.append(" and bean.isRecommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (desc) {
			f.append(" order by bean.commentId desc");
		} else {
			f.append(" order by bean.commentId asc");
		}
		f.setCacheable(cacheable);
		return f;
	}

	public CmsComment findById(Integer id) {
		CmsComment entity = get(id);
		return entity;
	}

	public CmsComment save(CmsComment bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsComment deleteById(Integer id) {
		CmsComment entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public int deleteByContentId(Integer contentId) {
		String hql = "delete from CmsComment bean where bean.content.contentId=:contentId";
		return getSession().createQuery(hql).setParameter("contentId",
				contentId).executeUpdate();
	}
	
	@Override
	protected Class<CmsComment> getEntityClass() {
		return CmsComment.class;
	}
}