package com.lm.shopping.business.bean;

public class SalesTaxesItemBeanBuilder {
    private ItemBean item;
    private Long amount;
    private Long saleTax;
    private Long total;

    public SalesTaxesItemBeanBuilder withItem(ItemBean item) {
        this.item = item;
        return this;
    }

    public SalesTaxesItemBeanBuilder withAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public SalesTaxesItemBeanBuilder withSaleTax(Long saleTax) {
        this.saleTax = saleTax;
        return this;
    }

    public SalesTaxesItemBeanBuilder withTotal(Long total) {
        this.total = total;
        return this;
    }

    public SalesTaxesItemBean build() {
        return new SalesTaxesItemBean(item, amount, saleTax, total);
    }
}