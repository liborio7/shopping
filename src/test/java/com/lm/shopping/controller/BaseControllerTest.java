package com.lm.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lm.shopping.App;
import com.lm.shopping.common.AppObjectMapper;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

abstract class BaseControllerTest extends JerseyTest {

    static ObjectMapper objectMapper = AppObjectMapper.getObjectMapper();

    @Override
    protected Application configure() {
        return App.configureResources();
    }

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }
}
