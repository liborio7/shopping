package com.lm.shopping.controller.sales.bean;

import com.lm.shopping.common.bean.LoggableBean;
import com.lm.shopping.common.enums.ItemCategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesItemResponseBean extends LoggableBean {

    private UUID id;
    private Long amount;
    private String name;
    private ItemCategoryEnum category;
    private BigDecimal price;
    private Boolean imported;

    public SalesItemResponseBean() {
    }

    public SalesItemResponseBean(UUID id, Long amount, String name, ItemCategoryEnum category, BigDecimal price, Boolean imported) {
        this.id = id;
        this.amount = amount;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }
}
