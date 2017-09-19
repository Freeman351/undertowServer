package com.freenan.undertow.undertow.server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;

public class AppEngine4Servlet {

	public static final String MYAPP = "/myapp";

	public static void main(final String[] args)  {

		try {

			DeploymentInfo servletBuilder = Servlets.deployment()
					.setClassLoader(AppEngine4Servlet.class.getClassLoader())
					.setContextPath(MYAPP)
					.setDeploymentName("myapp")
					.addServlets(new AppServletInfo("myservlet", AppServlet.class).addMappings("/myservlet"));
					
			DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
			manager.deploy();

			HttpHandler servletHandler = manager.start();
			PathHandler path = Handlers.path(Handlers.redirect(MYAPP))
					.addPrefixPath(MYAPP, servletHandler);
			Undertow server = Undertow.builder()
					.addHttpListener(8080, "localhost")
					.setHandler(path)
					.build();
			server.start();
		}  catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
