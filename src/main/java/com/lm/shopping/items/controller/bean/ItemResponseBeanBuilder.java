package com.lm.shopping.items.controller.bean;

import com.lm.shopping.items.enums.ItemCategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemResponseBeanBuilder {
    private UUID id;
    private String name;
    private ItemCategoryEnum category;
    private BigDecimal price;
    private Boolean imported;

    public ItemResponseBeanBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ItemResponseBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemResponseBeanBuilder withCategory(ItemCategoryEnum category) {
        this.category = category;
        return this;
    }

    public ItemResponseBeanBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemResponseBeanBuilder withImported(Boolean imported) {
        this.imported = imported;
        return this;
    }

    public ItemResponseBean build() {
        return new ItemResponseBean(id, name, category, price, imported);
    }
}