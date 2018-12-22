package com.lm.shopping.controller.sales.bean;

import java.math.BigDecimal;
import java.util.List;

public class SalesTaxesResponseBeanBuilder {
    private List<SalesTaxesItemResponseBean> items;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public SalesTaxesResponseBeanBuilder withItems(List<SalesTaxesItemResponseBean> items) {
        this.items = items;
        return this;
    }

    public SalesTaxesResponseBeanBuilder withSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
        return this;
    }

    public SalesTaxesResponseBeanBuilder withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public SalesTaxesResponseBean build() {
        return new SalesTaxesResponseBean(items, salesTaxes, total);
    }
}