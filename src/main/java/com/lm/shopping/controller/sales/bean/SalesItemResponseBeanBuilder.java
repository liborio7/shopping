package com.lm.shopping.controller.sales.bean;

import com.lm.shopping.common.enums.ItemCategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesItemResponseBeanBuilder {
    private UUID id;
    private Long amount;
    private String name;
    private ItemCategoryEnum category;
    private BigDecimal price;
    private Boolean imported;

    public SalesItemResponseBeanBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public SalesItemResponseBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesItemResponseBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SalesItemResponseBeanBuilder withCategory(ItemCategoryEnum category) {
        this.category = category;
        return this;
    }

    public SalesItemResponseBeanBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public SalesItemResponseBeanBuilder withImported(Boolean imported) {
        this.imported = imported;
        return this;
    }

    public SalesItemResponseBean build() {
        return new SalesItemResponseBean(id, amount, name, category, price, imported);
    }
}