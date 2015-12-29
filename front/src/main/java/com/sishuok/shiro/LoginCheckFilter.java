package com.sishuok.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.sishuok.architecture1.customermgr.vo.CustomerModel;

public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(req.getRequestURI().contains("toLogin") || (req.getRequestURI().contains("login"))){
			chain.doFilter(request, response);
		}else{
			ApplicationContext ctx = (ApplicationContext) request.getServletContext().
					getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			SecurityManager securityManager = ctx.getBean(SecurityManager.class);
			SecurityUtils.setSecurityManager(securityManager);
			Subject currentUser = SecurityUtils.getSubject();
			CustomerModel customerModel = (CustomerModel) currentUser.getSession().getAttribute("Login_Customer");
			if(customerModel==null || StringUtils.isBlank(customerModel.getCustomerId())){
				resp.sendRedirect("/toLogin");
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
