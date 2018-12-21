package com.lm.shopping.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Optional;

import static com.lm.shopping.common.AppConstants.START_TIME;

@Provider
public class ResponseFilter implements ContainerResponseFilter {
    private final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String executionTime = Optional.ofNullable(MDC.get(START_TIME))
                .map(Long::parseLong)
                .map(startTime -> System.currentTimeMillis() - startTime)
                .map(String::valueOf)
                .orElse("-");

        Response.StatusType statusInfo = responseContext.getStatusInfo();
        if (Response.Status.Family.SUCCESSFUL.equals(statusInfo.getFamily())) {
            Object response = Optional.ofNullable(responseContext.getEntity())
                    .orElse("no content");
            logger.info("------- RESPONSE SUCCESS : {} {}", responseContext.getStatus(), response);
        } else {
            logger.info("------- RESPONSE ERROR : {}", responseContext.getStatus());
        }

        logger.info("------- EXECUTION TIME : {} ms", executionTime);

        MDC.clear();
    }
}
