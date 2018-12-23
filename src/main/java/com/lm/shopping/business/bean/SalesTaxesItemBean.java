package com.lm.shopping.business.bean;

import com.lm.shopping.common.bean.LoggableBean;

import java.math.BigDecimal;

public class SalesTaxesItemBean extends LoggableBean {

    private ItemBean item;
    private Long amount;
    private BigDecimal saleTax;
    private BigDecimal total;

    public SalesTaxesItemBean() {
    }

    public SalesTaxesItemBean(ItemBean item, Long amount, BigDecimal saleTax, BigDecimal total) {
        this.item = item;
        this.amount = amount;
        this.saleTax = saleTax;
        this.total = total;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getSaleTax() {
        return saleTax;
    }

    public void setSaleTax(BigDecimal saleTax) {
        this.saleTax = saleTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
