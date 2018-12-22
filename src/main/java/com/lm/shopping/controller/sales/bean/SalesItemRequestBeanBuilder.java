package com.lm.shopping.controller.sales.bean;

import java.util.UUID;

public class SalesItemRequestBeanBuilder {
    private UUID itemId;
    private Long amount;

    public SalesItemRequestBeanBuilder withItemId(UUID itemId) {
        this.itemId = itemId;
        return this;
    }

    public SalesItemRequestBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesItemRequestBean build() {
        return new SalesItemRequestBean(itemId, amount);
    }
}