package com.lm.shopping.items.business.bean;

import com.lm.shopping.items.enums.ItemCategoryEnum;

import java.util.UUID;

public class ItemBeanBuilder {
    private UUID id;
    private String name;
    private ItemCategoryEnum category;
    private Long price;
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

    public ItemBeanBuilder withPrice(Long price) {
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