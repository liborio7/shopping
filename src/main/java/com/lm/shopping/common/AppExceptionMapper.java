package com.lm.shopping.common;

import com.lm.shopping.common.bean.ErrorResponseBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {
    private final Logger logger = LoggerFactory.getLogger(AppExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        logger.warn("exception occurred {} : {}", e.getClass().getSimpleName(), e.getMessage());

        if (e instanceof NotAuthorizedException) {
            Response.Status status = Response.Status.UNAUTHORIZED;
            return Response.status(status)
                    .entity(new ErrorResponseBeanBuilder()
                            .withCode(status.getStatusCode())
                            .withStatus(status.getReasonPhrase())
                            .withMessage(e.getMessage())
                            .build())
                    .build();

        } else if (e instanceof ForbiddenException) {
            Response.Status status = Response.Status.FORBIDDEN;
            return Response.status(status)
                    .entity(new ErrorResponseBeanBuilder()
                            .withCode(status.getStatusCode())
                            .withStatus(status.getReasonPhrase())
                            .withMessage(e.getMessage())
                            .build())
                    .build();

        } else if (e instanceof NotFoundException) {
            Response.Status status = Response.Status.NOT_FOUND;
            return Response.status(status)
                    .entity(new ErrorResponseBeanBuilder()
                            .withCode(status.getStatusCode())
                            .withStatus(status.getReasonPhrase())
                            .withMessage(e.getMessage())
                            .build())
                    .build();

        } else if (e instanceof BadRequestException) {
            Response.Status status = Response.Status.BAD_REQUEST;
            return Response.status(status)
                    .entity(new ErrorResponseBeanBuilder()
                            .withCode(status.getStatusCode())
                            .withStatus(status.getReasonPhrase())
                            .withMessage(e.getMessage())
                            .build())
                    .build();

        } else {
            logger.error("error: " + e);
            Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
            return Response.status(status)
                    .entity(new ErrorResponseBeanBuilder()
                            .withCode(status.getStatusCode())
                            .withStatus(status.getReasonPhrase())
                            .withMessage(e.getMessage())
                            .build())
                    .build();
        }
    }
}