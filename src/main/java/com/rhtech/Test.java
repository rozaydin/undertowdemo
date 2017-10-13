package com.rhtech;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathTemplateHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.*;
import io.undertow.util.Headers;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

public class Test {

    // https://github.com/spring-projects/spring-boot/issues/8283
    public static void main(String[] args) throws Exception {

        // define handlers
        HttpHandler helloHandler = (exchange) -> {
            // get name parameter
            exchange.getQueryParameters().get("name");
            System.out.println(exchange.getQueryParameters());
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Hello World");
        };

        HttpHandler greetHandler = (exchange) -> {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("You reached me!");
        };

        // add routing
        PathTemplateHandler pathTemplateHandler = new PathTemplateHandler();
        pathTemplateHandler.add("/greet/{name}", greetHandler);
        pathTemplateHandler.add("/hello/{name}", helloHandler);

        // PathHandler pathHandler = Handlers.path();
        // register handlers
        // pathHandler.addPrefixPath("/greet", greetHandler);
        // pathHandler.addPrefixPath("/hello", helloHandler);

        // spring integration
        // Load Spring web application configuration
        final AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
        cxt.register(AppConfig.class);
        cxt.refresh();

        // create a servlet container
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(Test.class.getClassLoader())
                .setContextPath("/app")
                .setDeploymentName("rozaydin")
                .addServlets(
                        /*Servlets.servlet("MessageServlet", MessageServlet.class)
                                .addInitParam("message", "Hello World")
                                .addMapping("/*")*/
                        Servlets.servlet("DispatcherServlet", DispatcherServlet.class, new InstanceFactory<Servlet>() {
                            @Override
                            public InstanceHandle<Servlet> createInstance() throws InstantiationException {
                                // create dispatcher servlet
                                DispatcherServlet servlet = new DispatcherServlet(cxt);
                                // instance handler
                                return new InstanceHandle<Servlet>() {
                                    @Override
                                    public Servlet getInstance() {
                                        return servlet;
                                    }

                                    @Override
                                    public void release() {
                                        cxt.close();
                                    }
                                };
                            }
                        }).addMapping("/*")
                );

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        HttpHandler servletHandler = manager.start();
        /*pathTemplateHandler.add("/myapp/*", (exchange)->{
            exchange.getResponseSender().send("servlet url here you reached here using url: " + exchange.getRequestURL());
        });*/
        pathTemplateHandler.add("/app/*", servletHandler);
        /*PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
                .addPrefixPath("/myapp", manager.start());*/

        // build server
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost", pathTemplateHandler)
                .build();
        // start server
        server.start();
    }

}
