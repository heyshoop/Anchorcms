package com.anchorcms.cms.service.main.impl;

import com.anchorcms.cms.dao.main.ModelItemDao;
import com.anchorcms.cms.model.main.CmsModelItem;
import com.anchorcms.cms.service.main.ModelItemService;
import com.anchorcms.cms.service.main.ModelService;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ModelItemServiceImpl implements ModelItemService {
	@Transactional(readOnly = true)
	public List<CmsModelItem> getList(Integer modelId, boolean isChannel,
									  boolean hasDisabled) {
		return dao.getList(modelId, isChannel, hasDisabled);
	}

	@Transactional(readOnly = true)
	public CmsModelItem findById(Integer id) {
		CmsModelItem entity = dao.findById(id);
		return entity;
	}

	public CmsModelItem save(CmsModelItem bean, Integer modelId) {
		bean.setModel(modelService.findById(modelId));
		return save(bean);
	}

	public CmsModelItem save(CmsModelItem bean) {
		bean.init();
		dao.save(bean);
		return bean;
	}

	public void saveList(List<CmsModelItem> list) {
		for (CmsModelItem item : list) {
			save(item);
		}
	}

	public void updatePriority(Integer[] wids, Integer[] priority,
							   String[] label, Boolean[] single, Boolean[] display) {
		CmsModelItem item;
		for (int i = 0, len = wids.length; i < len; i++) {
			item = findById(wids[i]);
			item.setLabel(label[i]);
			item.setPriority(priority[i]);
			item.setSingle(single[i]);
			item.setDisplay(display[i]);
		}
	}

	public CmsModelItem update(CmsModelItem bean) {
		Updater<CmsModelItem> updater = new Updater<CmsModelItem>(bean);
		CmsModelItem entity = dao.updateByUpdater(updater);
		entity.emptyToNull();
		return entity;
	}

	public CmsModelItem deleteById(Integer id) {
		CmsModelItem bean = dao.deleteById(id);
		return bean;
	}

	public CmsModelItem[] deleteByIds(Integer[] ids) {
		CmsModelItem[] beans = new CmsModelItem[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Resource
	private ModelService modelService;
	@Resource
	private ModelItemDao dao;

}