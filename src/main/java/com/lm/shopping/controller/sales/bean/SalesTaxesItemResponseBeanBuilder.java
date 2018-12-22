package com.lm.shopping.controller.sales.bean;

import com.lm.shopping.common.enums.ItemCategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTaxesItemResponseBeanBuilder {
    private UUID id;
    private Long amount;
    private String name;
    private ItemCategoryEnum category;
    private BigDecimal price;
    private Boolean imported;

    public SalesTaxesItemResponseBeanBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public SalesTaxesItemResponseBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesTaxesItemResponseBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SalesTaxesItemResponseBeanBuilder withCategory(ItemCategoryEnum category) {
        this.category = category;
        return this;
    }

    public SalesTaxesItemResponseBeanBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public SalesTaxesItemResponseBeanBuilder withImported(Boolean imported) {
        this.imported = imported;
        return this;
    }

    public SalesTaxesItemResponseBean build() {
        return new SalesTaxesItemResponseBean(id, amount, name, category, price, imported);
    }
}