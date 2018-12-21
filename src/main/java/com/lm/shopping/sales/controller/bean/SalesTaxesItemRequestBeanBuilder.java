package com.lm.shopping.sales.controller.bean;

import java.util.UUID;

public class SalesTaxesItemRequestBeanBuilder {
    private UUID itemId;
    private Long amount;

    public SalesTaxesItemRequestBeanBuilder withItemId(UUID itemId) {
        this.itemId = itemId;
        return this;
    }

    public SalesTaxesItemRequestBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesTaxesItemRequestBean build() {
        return new SalesTaxesItemRequestBean(itemId, amount);
    }
}