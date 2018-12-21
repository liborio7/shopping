package com.lm.shopping.sales.service.bean;

import com.lm.shopping.common.bean.LoggableBean;
import com.lm.shopping.items.service.bean.ItemBean;

public class SalesTaxesItemBean extends LoggableBean {

    private ItemBean item;
    private Long amount;
    private Long saleTax;
    private Long total;

    public SalesTaxesItemBean() {
    }

    public SalesTaxesItemBean(ItemBean item, Long amount, Long saleTax, Long total) {
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

    public Long getSaleTax() {
        return saleTax;
    }

    public void setSaleTax(Long saleTax) {
        this.saleTax = saleTax;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
