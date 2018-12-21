package com.lm.shopping.items.persistence.model;

import com.lm.shopping.items.enums.ItemCategoryEnum;

import java.util.UUID;

public class Item {

    private UUID id;
    private String name;
    private ItemCategoryEnum category;
    private Long price;
    private Boolean imported;

    public Item() {
    }

    public Item(UUID id, String name, ItemCategoryEnum category, Long price, Boolean imported) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imported = imported;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryEnum category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }
}
