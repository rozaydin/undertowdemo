package com.rhtech.spring;

import com.rhtech.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppIniter implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        final AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
        cxt.register(AppConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(cxt);
        // Dispatcher Servlet Configuration
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("Dispathcer Servlet", servlet);

        dispatcher.addMapping("/*");
    }

}
