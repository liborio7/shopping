package com.lm.shopping.items.persistence.model;

import com.lm.shopping.items.enums.ItemCategoryEnum;

import java.util.UUID;

public class ItemBuilder {
    private UUID id;
    private String name;
    private ItemCategoryEnum category;
    private Long price;
    private Boolean imported;

    public ItemBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withCategory(ItemCategoryEnum category) {
        this.category = category;
        return this;
    }

    public ItemBuilder withPrice(Long price) {
        this.price = price;
        return this;
    }

    public ItemBuilder withImported(Boolean imported) {
        this.imported = imported;
        return this;
    }

    public Item build() {
        return new Item(id, name, category, price, imported);
    }
}