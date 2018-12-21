package com.lm.shopping.common.bean;

public class ErrorResponseBeanBuilder {
    private Integer code;
    private String status;
    private String message;

    public ErrorResponseBeanBuilder withCode(Integer code) {
        this.code = code;
        return this;
    }

    public ErrorResponseBeanBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBeanBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponseBean build() {
        return new ErrorResponseBean(code, status, message);
    }
}