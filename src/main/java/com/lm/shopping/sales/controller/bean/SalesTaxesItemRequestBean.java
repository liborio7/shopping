package com.lm.shopping.sales.controller.bean;

import com.lm.shopping.common.bean.LoggableBean;

import java.util.UUID;

public class SalesTaxesItemRequestBean extends LoggableBean {

    private UUID itemId;
    private Long amount;

    public SalesTaxesItemRequestBean() {
    }

    public SalesTaxesItemRequestBean(UUID itemId, Long amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
