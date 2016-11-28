package com.anchorcms.core.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.session.SessionProvider;
import com.anchorcms.core.model.Authentication;
import com.anchorcms.core.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.anchorcms.core.service.AuthenticationService.AUTH_KEY;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/28 18:47
 * @Desc 登录成功后的处理类
 */

@Controller
public class ProcessController {
	private static Logger log = LoggerFactory.getLogger(ProcessController.class);

	@RequestMapping(value = "/process.jspx", method = RequestMethod.GET)
	public String process(HttpServletRequest request,
			HttpServletResponse response) {
		String returnUrl = RequestUtils.getQueryParam(request,
				CLoginController.RETURN_URL);
		String authId = RequestUtils.getQueryParam(request, AUTH_KEY);
		Authentication auth = authenticationService.retrieve(authId);
		if (auth != null) {
			authenticationService.storeAuthIdToSession(session, request, response, auth
					.getAuthenticationId());
		} else {
			log.warn("Authentication id not found: {}", authId);
		}
		return "redirect:" + returnUrl;
	}

	@Resource
	private AuthenticationService authenticationService;
	@Resource
	private SessionProvider session;
}
