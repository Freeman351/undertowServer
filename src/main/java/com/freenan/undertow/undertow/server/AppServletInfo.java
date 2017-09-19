package com.freenan.undertow.undertow.server;

import io.undertow.servlet.api.ServletInfo;

import javax.servlet.http.HttpServlet;

public class AppServletInfo extends ServletInfo {

	public AppServletInfo(String servletName, Class<? extends HttpServlet> servletClass) {
		super(servletName, servletClass);		
	}

}
