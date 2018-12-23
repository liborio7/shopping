package com.lm.shopping.business.bean;

import java.math.BigDecimal;

public class SalesTaxesItemBeanBuilder {
    private ItemBean item;
    private Long amount;
    private BigDecimal saleTax;
    private BigDecimal total;

    public SalesTaxesItemBeanBuilder withItem(ItemBean item) {
        this.item = item;
        return this;
    }

    public SalesTaxesItemBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesTaxesItemBeanBuilder withSaleTax(BigDecimal saleTax) {
        this.saleTax = saleTax;
        return this;
    }

    public SalesTaxesItemBeanBuilder withTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public SalesTaxesItemBean build() {
        return new SalesTaxesItemBean(item, amount, saleTax, total);
    }
}