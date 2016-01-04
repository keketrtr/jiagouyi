package com.sishuok.staticize;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenStaticHtmlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//1：取到访问的url
		String urlPath = req.getRequestURI();
		String genParam = req.getParameter("gen");
		//2：判断是否需要静态化
		if(urlPath.endsWith("/toIndex") && !"true".equals(genParam)){
			byte[] bs = GenHtml.mapHtml.get("toIndex");
			if(bs==null){
				new GenHtml().startGenStaticHtml();
				bs = GenHtml.mapHtml.get("toIndex");
			}
			if(bs != null){
				resp.getOutputStream().write(bs);
			}
		}else{
			chain.doFilter(request, response);
		}
		//3：对于需要静态化的，判断是否已经有了静态化的内容
		//3.1：如果有，就直接取出来，返回客户端
		
		//3.2：如果没有，就调用后端生成html的方法，然后获取内容返回
		
		
	}

	@Override
	public void destroy() {
		
	}

}
