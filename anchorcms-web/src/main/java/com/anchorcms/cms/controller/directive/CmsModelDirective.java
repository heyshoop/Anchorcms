package com.anchorcms.cms.controller.directive;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.anchorcms.cms.model.main.CmsModel;
import com.anchorcms.cms.service.main.CmsModelMng;
import com.anchorcms.common.web.freemarker.DefaultObjectWrapperBuilderFactory;
import com.anchorcms.common.web.freemarker.DirectiveUtils;
import com.anchorcms.common.web.freemarker.ParamsRequiredException;
import org.apache.commons.lang.StringUtils;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import javax.annotation.Resource;

import static com.anchorcms.common.web.freemarker.DirectiveUtils.OUT_BEAN;

/**
 * 模型对象标签
 * 
 */
public class CmsModelDirective implements TemplateDirectiveModel {
	/**
	 * 输入参数，栏目ID。
	 */
	public static final String PARAM_ID = "id";
	/**
	 * 输入参数，栏目路径。
	 */
	public static final String PARAM_PATH = "path";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer id = DirectiveUtils.getInt(PARAM_ID, params);
		CmsModel model;
		if (id != null) {
			model = modelMng.findById(id);
		} else {
			String path = DirectiveUtils.getString(PARAM_PATH, params);
			if (StringUtils.isBlank(path)) {
				// 如果path不存在，那么id必须存在。
				throw new ParamsRequiredException(PARAM_ID);
			}
			model = modelMng.findByPath(path);
		}

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_BEAN, DefaultObjectWrapperBuilderFactory.getDefaultObjectWrapper().wrap(model));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Resource
	private CmsModelMng modelMng;
}
