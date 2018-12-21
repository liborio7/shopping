package com.lm.shopping.common.controller;

import
        org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.util.Objects;
import java.util.Optional;

import static com.lm.shopping.common.AppConstants.*;
import static java.lang.System.currentTimeMillis;

@Provider
public class RequestFilter implements ContainerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Context private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MDC.put(START_TIME, String.valueOf(currentTimeMillis()));
        MDC.put(RID, Optional.ofNullable(requestContext.getHeaders().get(RID))
                .map(Objects::toString)
                .filter(StringUtils::isAlphanumeric)
                .orElse(RandomStringUtils.randomAlphanumeric(8)));

        logger.info("------- REQUEST : {} /{} ", requestContext.getMethod(), requestContext.getUriInfo().getPath());
    }
}
