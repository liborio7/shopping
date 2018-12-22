package com.lm.shopping.controller.sales.bean;

import com.lm.shopping.common.bean.LoggableBean;

import java.math.BigDecimal;
import java.util.List;

public class SalesResponseBean extends LoggableBean {

    private List<SalesItemResponseBean> items;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public SalesResponseBean() {
    }

    public SalesResponseBean(List<SalesItemResponseBean> items, BigDecimal salesTaxes, BigDecimal total) {
        this.items = items;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public List<SalesItemResponseBean> getItems() {
        return items;
    }

    public void setItems(List<SalesItemResponseBean> items) {
        this.items = items;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
