package com.lm.shopping.controller.sales.bean;

import java.math.BigDecimal;
import java.util.List;

public class SalesResponseBeanBuilder {
    private List<SalesItemResponseBean> items;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public SalesResponseBeanBuilder withItems(List<SalesItemResponseBean> items) {
        this.items = items;
        return this;
    }

    public SalesResponseBeanBuilder withSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
        return this;
    }

    public SalesResponseBeanBuilder withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public SalesResponseBean build() {
        return new SalesResponseBean(items, salesTaxes, total);
    }
}