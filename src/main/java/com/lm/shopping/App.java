package com.lm.shopping;

import com.lm.shopping.common.AppInjectionResolver;
import com.lm.shopping.common.AppSqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class App {

    public static void main(String[] args) {
        // configure server
        ResourceConfig resourceConfig = configureResources();
        URI uri = UriBuilder.fromUri("http://localhost/").port(9999).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        // set shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server stopped :(");
            server.shutdownNow();
        }));

        // start server
        try {
            System.out.println("Server started :)");
            server.start();
            Thread.currentThread().join();

        } catch (Exception e) {
            System.out.println("There was an error while starting Grizzly HTTP server.\n" + e);
        }
    }

    //============

    public static ResourceConfig configureResources() {
        ResourceConfig resourceConfig = new ResourceConfig().packages("com.lm.shopping");
        resourceConfig.register(configureBinder());
        return resourceConfig;
    }

    public static AbstractBinder configureBinder() {
        return new AbstractBinder() {
            @Override
            protected void configure() {
                bind(AppInjectionResolver.class).to(JustInTimeInjectionResolver.class);
                bind(AppSqlSessionFactory.class).to(SqlSessionFactory.class);
            }
        };
    }
}
