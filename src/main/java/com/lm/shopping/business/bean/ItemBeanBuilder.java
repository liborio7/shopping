package com.lm.shopping.business.bean;

import com.lm.shopping.common.enums.ItemCategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemBeanBuilder {
    private UUID id;
    private String name;
    private ItemCategoryEnum category;
    private BigDecimal price;
    private Boolean imported;

    public ItemBeanBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ItemBeanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBeanBuilder withCategory(ItemCategoryEnum category) {
        this.category = category;
        return this;
    }

    public ItemBeanBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemBeanBuilder withImported(Boolean imported) {
        this.imported = imported;
        return this;
    }

    public ItemBean build() {
        return new ItemBean(id, name, category, price, imported);
    }
}