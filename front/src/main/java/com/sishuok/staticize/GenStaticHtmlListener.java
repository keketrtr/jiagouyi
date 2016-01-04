package com.sishuok.staticize;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GenStaticHtmlListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		GenHtml genHtml = new GenHtml();
		genHtml.startGenStaticHtml();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
