package com.lm.shopping.sales.business;

import com.lm.shopping.items.business.ItemsService;
import com.lm.shopping.items.business.bean.ItemBean;
import com.lm.shopping.sales.business.bean.SalesTaxesItemBean;
import com.lm.shopping.sales.business.bean.SalesTaxesItemBeanBuilder;
import com.lm.shopping.sales.business.exception.InvalidItemAmountException;
import com.lm.shopping.sales.business.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class SalesTaxesService {
    private final Logger logger = LoggerFactory.getLogger(SalesTaxesService.class);

    @Inject private ItemsService itemsService;

    public SalesTaxesItemBean calculateSaleTax(UUID itemId, Long amount) throws ItemNotFoundException, InvalidItemAmountException {
        logger.debug("calculate sales taxes for #{} item id {}", amount, itemId);

        if (amount <= 0) {
            logger.warn("wrong item amount {}", amount);
            throw new InvalidItemAmountException(amount + " is not a valid amount");
        }

        ItemBean itemBean = itemsService.getItem(itemId)
                .orElseThrow(() -> new ItemNotFoundException("no item found with id " + itemId));

        Long tax = calculateSaleTax(itemBean);
        return new SalesTaxesItemBeanBuilder()
                .withItem(itemBean)
                .withAmount(amount)
                .withSaleTax(tax * amount)
                .withTotal((itemBean.getPrice() + tax) * amount)
                .build();
    }

    //============

    private Long calculateSaleTax(ItemBean item) {
        Long tax = 0L;
        tax += calculateCategoryTax(item);
        if (item.getImported()) {
            tax += calculateImportTax(item);
        }
        return tax;
    }

    private long calculateCategoryTax(ItemBean item) {
        double tax;
        switch (item.getCategory()) {
            case BOOK:
            case FOOD:
            case MEDICAL:
                return 0L;
            case OTHER:
            default:
                tax = 0.1d;
        }

        return roundToNearestFive(tax * item.getPrice());
    }

    private long calculateImportTax(ItemBean item) {
        return roundToNearestFive(0.05f * item.getPrice());
    }

    private long roundToNearestFive(double price) {
        return 5 * (Math.round(price / 5));
    }
}
