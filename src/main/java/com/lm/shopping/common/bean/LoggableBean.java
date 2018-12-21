package com.lm.shopping.common.bean;


import com.lm.shopping.common.AppObjectMapper;

public class LoggableBean {
    @Override
    public String toString() {
        try {
            return AppObjectMapper.getObjectMapper().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
