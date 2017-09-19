package com.freenan.undertow.undertow.server;

import io.undertow.Undertow;
import io.undertow.jsp.HackInstanceManager;
import io.undertow.jsp.JspServletBuilder;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.jasper.deploy.JspPropertyGroup;
import org.apache.jasper.deploy.TagLibraryInfo;

public class AppEngine4JSP {
  public static void main(String [] args) throws ServletException, IOException {
    final PathHandler servletPath = new PathHandler();
    final ServletContainer container = ServletContainer.Factory.newInstance();

    DeploymentInfo builder = new DeploymentInfo()
	        .setClassLoader(AppEngine4JSP.class.getClassLoader())
	        .setContextPath("/servletContext")
	        .setDeploymentName("servletContext.war")
	        .setResourceManager(new DefaultResourceLoader(AppEngine4JSP.class))
	        .addServlet(JspServletBuilder.createServlet("Default Jsp Servlet", "*.jsp"));

    HashMap<String, TagLibraryInfo> tagLibraryInfo = TldLocator.createTldInfos();
    JspServletBuilder.setupDeployment(builder, new HashMap<String, JspPropertyGroup>(), tagLibraryInfo, new HackInstanceManager());
    DeploymentManager manager = container.addDeployment(builder);
    manager.deploy();

    servletPath.addPrefixPath(builder.getContextPath(), manager.start());

    // All JSPs will be exposed under http://localhost:8080/servletContext/
    //
    //
    Undertow.builder().addHttpListener(8080, "localhost")
        .setHandler(servletPath)
        .build()
        .start();

    System.out.println("http://localhost:8080/servletContext/test.jsp");
  }
  public static class DefaultResourceLoader extends ClassPathResourceManager {
    public DefaultResourceLoader(final Class<?> clazz) {
      super(clazz.getClassLoader(), "");
    }
}
}
