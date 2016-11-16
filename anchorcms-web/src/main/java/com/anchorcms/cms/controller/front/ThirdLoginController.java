package com.anchorcms.cms.controller.front;


import static com.anchorcms.common.constants.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.main.CmsThirdAccount;
import com.anchorcms.cms.service.main.CmsThirdAccountMng;
import com.anchorcms.common.security.encoder.PwdEncoder;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.utils.FrontUtils;
import com.anchorcms.common.web.HttpRequestUtil;
import com.anchorcms.common.web.LoginUtils;
import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.common.web.session.SessionProvider;
import com.anchorcms.core.model.*;
import com.anchorcms.core.service.CmsConfigMng;
import com.anchorcms.core.service.CmsUserMng;
import com.anchorcms.core.service.UnifiedUserMng;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.subject.WebSubject.Builder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 第三方登录Action
 * 腾讯qq、新浪微博登陆
 */
@Controller
public class ThirdLoginController {
	public static final String TPL_BIND = "tpl.member.bind";
	public static final String TPL_AUTH = "tpl.member.auth";
	
	public static final String USER_LOG_OUT_FLAG = "logout";
	
	
	@RequestMapping(value = "/public_auth.jspx")
	public String auth(String openId,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_AUTH);
	}
	
	@RequestMapping(value = "/public_auth_login.jspx")
	public void authLogin(String key,String source,HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		if(StringUtils.isNotBlank(source)){
			if(source.equals(CmsThirdAccount.QQ_PLAT)){
				session.setAttribute(request,response,CmsThirdAccount.QQ_KEY, key);
			}else if(source.equals(CmsThirdAccount.QQ_WEBO_PLAT)){
				session.setAttribute(request,response,CmsThirdAccount.QQ_WEBO_KEY, key);
			}else if(source.equals(CmsThirdAccount.SINA_PLAT)){
				session.setAttribute(request,response,CmsThirdAccount.SINA_KEY, key);
			}
		}
		JSONObject json=new JSONObject();
		//库中存放的是加密后的key
		if(StringUtils.isNotBlank(key)){
			key=pwdEncoder.encodePassword(key);
		}
		CmsThirdAccount account=accountMng.findByKey(key);
		if(account!=null){
			json.put("succ", true);
			//已绑定直接登陆
			loginByKey(key, request, response, model);
		}else{
			json.put("succ", false);
		}
		ResponseUtils.renderJson(response, json.toString());
	}
	
	@RequestMapping(value = "/public_bind.jspx",method = RequestMethod.GET)
	public String bind_get(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_BIND);
	}
	
	@RequestMapping(value = "/public_bind.jspx",method = RequestMethod.POST)
	public String bind_post(String username,String password,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		boolean usernameExist=unifiedUserMng.usernameExist(username);
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors=WebErrors.create(request);
		String source="";
		if(!usernameExist){
			//用户名不存在
			errors.addErrorCode("error.usernameNotExist");
		}else{
			UnifiedUser u=unifiedUserMng.getByUsername(username);
			boolean passwordValid=unifiedUserMng.isPasswordValid(u.getUserId(), password);
			if(!passwordValid){
				errors.addErrorCode("error.passwordInvalid");
			}else{
				//获取用户来源
				String openId=(String) session.getAttribute(request, CmsThirdAccount.QQ_KEY);
				String uid=(String) session.getAttribute(request, CmsThirdAccount.SINA_KEY);
				String weboOpenId=(String) session.getAttribute(request, CmsThirdAccount.QQ_WEBO_KEY);
				if(StringUtils.isNotBlank(openId)){
					source=CmsThirdAccount.QQ_PLAT;
				}else if(StringUtils.isNotBlank(uid)){
					source=CmsThirdAccount.SINA_PLAT;
				}else if(StringUtils.isNotBlank(weboOpenId)){
					source=CmsThirdAccount.QQ_WEBO_PLAT;
				}
				//提交登录并绑定账号
				loginByUsername(username, request, response, model);
			}
		}
		if(errors.hasErrors()){
			errors.toModel(model);
			model.addAttribute("success",false);
		}else{
			model.addAttribute("success",true);
		}
		model.addAttribute("source", source);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_MEMBER, TPL_BIND);
	}
	
	@RequestMapping(value = "/public_bind_username.jspx")
	public String bind_username_post(String username,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors=WebErrors.create(request);
		String source="";
		if(StringUtils.isBlank(username)){
			//用户名为空
			errors.addErrorCode("error.usernameRequired");
		}else{
			boolean usernameExist=unifiedUserMng.usernameExist(username);
			if(usernameExist){
				//用户名存在
				errors.addErrorCode("error.usernameExist");
			}else{
				//获取用户来源
				String openId=(String) session.getAttribute(request, CmsThirdAccount.QQ_KEY);
				String uid=(String) session.getAttribute(request, CmsThirdAccount.SINA_KEY);
				String weboOpenId=(String) session.getAttribute(request, CmsThirdAccount.QQ_WEBO_KEY);
				//(获取到登录授权key后可以注册用户)
				if(StringUtils.isNotBlank(openId)||StringUtils.isNotBlank(uid)||StringUtils.isNotBlank(weboOpenId)){
					//初始设置密码同用户名
					cmsUserMng.registerMember(username, null, username, RequestUtils.getIpAddr(request), null, null, false, new CmsUserExt(), null);
				}
				if(StringUtils.isNotBlank(openId)){
					source=CmsThirdAccount.QQ_PLAT;
				}else if(StringUtils.isNotBlank(uid)){
					source=CmsThirdAccount.SINA_PLAT;
				}else if(StringUtils.isNotBlank(weboOpenId)){
					source=CmsThirdAccount.QQ_WEBO_PLAT;
				}
				//提交登录并绑定账号
				loginByUsername(username, request, response, model);
			}
		}
		if(errors.hasErrors()){
			errors.toModel(model);
			model.addAttribute("success",false);
		}else{
			model.addAttribute("success",true);
		}
		model.addAttribute("source", source);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),TPLDIR_MEMBER, TPL_BIND);
	}
	
	//判断用户是否登录
	@RequestMapping(value = "/sso/authenticate.jspx")
	public void authenticate(String username,String sessionId,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsUser user= cmsUserMng.findByUsername(username);
		if(user!=null&&sessionId!=null){
			String userSessionId=user.getSessionId();
			if(StringUtils.isNotBlank(userSessionId)){
				if(userSessionId.equals(sessionId)){
					ResponseUtils.renderJson(response, "true");
				}
			}else{
				ResponseUtils.renderJson(response, "false");
			}
		}
	}
	
	@RequestMapping(value = "/sso/login.jspx")
	public void loginSso(String username,String sessionId,String ssoLogout,HttpServletRequest request,HttpServletResponse response) {
		CmsUser user =CmsUtils.getUser(request);
		if(StringUtils.isNotBlank(username)){
			JSONObject object =new JSONObject();
			try {
				if(user==null){
					//未登录，其他地方已经登录，则登录自身
					CmsConfig config=cmsConfigMng.get();
					List<String>authenticateUrls=config.getSsoAuthenticateUrls();
					String success=authenticate(username, sessionId, authenticateUrls);
					if(success.equals("true")){
						LoginUtils.loginShiro(request, response, username);
						user = cmsUserMng.findByUsername(username);
						if(user!=null){
							cmsUserMng.updateLoginInfo(user.getUserId(), null,null,sessionId);
						}
						object.put("result", "login");
					}
				}else if(StringUtils.isNotBlank(ssoLogout)&&ssoLogout.equals("true")){
					LoginUtils.logout();
					object.put("result", "logout");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResponseUtils.renderJson(response, object.toString());
		}
	}
	
	private String authenticate(String username,String sessionId,List<String>authenticateUrls){
		String result="false";
		for(String url:authenticateUrls){
			result=authenticate(username, sessionId, url);
			if(result.equals("true")){
				break;
			}
		}
		return result;
	}
	
	private String authenticate(String username,String sessionId,String authenticateUrl){
		Map<String,String>params=new HashMap<String, String>();
		params.put("username", username);
		params.put("sessionId", sessionId);
		String success="false";
		try {
			success= HttpRequestUtil.request(authenticateUrl, params, "post", "utf-8");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * 用户名登陆,绑定用户名和第三方账户key
	 * @param username
	 * @param request
	 * @param response
	 * @param model
	 */
	private void loginByUsername(String username, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String openId=(String) session.getAttribute(request, CmsThirdAccount.QQ_KEY);
		String uid=(String) session.getAttribute(request, CmsThirdAccount.SINA_KEY);
		String weboOpenId=(String) session.getAttribute(request, CmsThirdAccount.QQ_WEBO_KEY);
		if(StringUtils.isNotBlank(openId)){
			loginShiro(request, response, username);
			//绑定账户
			bind(username, openId,  CmsThirdAccount.QQ_PLAT);
		}
		if(StringUtils.isNotBlank(uid)){
			loginShiro(request, response, username);
			//绑定账户
			bind(username, uid,  CmsThirdAccount.SINA_PLAT);
		}
		if(StringUtils.isNotBlank(weboOpenId)){
			loginShiro(request, response, username);
			//绑定账户
			bind(username, weboOpenId,  CmsThirdAccount.QQ_WEBO_PLAT);
		}
	}
	
	/**
	 * 已绑定用户key登录
	 * @param key
	 * @param request
	 * @param response
	 * @param model
	 */
	private void loginByKey(String key,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsThirdAccount account=accountMng.findByKey(key);
		if(StringUtils.isNotBlank(key)&&account!=null){
			String username=account.getUsername();
			loginShiro(request, response, username);
		}
	}
	
	
	private void loginShiro(HttpServletRequest request,HttpServletResponse response,String username){
		PrincipalCollection principals = new SimplePrincipalCollection(username, username);  
		Builder builder = new Builder( request,response);
		builder.principals(principals);  
		builder.authenticated(true);  
		WebSubject subject = builder.buildWebSubject();  
		ThreadContext.bind(subject); 
	}
	
	private void bind(String username,String openId,String source){
		CmsThirdAccount account=accountMng.findByKey(openId);
		if(account==null){
			account=new CmsThirdAccount();
			account.setUsername(username);
			//第三方账号唯一码加密存储 防冒名登录
			openId=pwdEncoder.encodePassword(openId);
			account.setAccountKey(openId);
			account.setSource(source);
			accountMng.save(account);
		}
	}
	
	@Resource
	private UnifiedUserMng unifiedUserMng;
	@Resource
	private CmsUserMng cmsUserMng;
	@Resource
	private CmsThirdAccountMng accountMng;
	@Resource
	private SessionProvider session;
	@Resource
	private PwdEncoder pwdEncoder;
	@Resource
	private CmsConfigMng cmsConfigMng;
}
