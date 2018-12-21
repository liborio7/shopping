package com.lm.shopping.sales.controller.bean;

import com.lm.shopping.common.bean.LoggableBean;

import java.math.BigDecimal;
import java.util.List;

public class SalesTaxesResponseBean extends LoggableBean {

    private List<SalesTaxesItemResponseBean> items;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public SalesTaxesResponseBean() {
    }

    public SalesTaxesResponseBean(List<SalesTaxesItemResponseBean> items, BigDecimal salesTaxes, BigDecimal total) {
        this.items = items;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public List<SalesTaxesItemResponseBean> getItems() {
        return items;
    }

    public void setItems(List<SalesTaxesItemResponseBean> items) {
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
