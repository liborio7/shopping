package com.lm.shopping.business;

import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.business.bean.SalesTaxesItemBean;
import com.lm.shopping.business.bean.SalesTaxesItemBeanBuilder;
import com.lm.shopping.business.exception.InvalidItemAmountException;
import com.lm.shopping.business.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.UUID;

@Singleton
public class SalesTaxesService {
    private final Logger logger = LoggerFactory.getLogger(SalesTaxesService.class);

    @Inject private ItemsService itemsService;

    public SalesTaxesItemBean calculateSalesTaxes(UUID itemId, Long amount) throws ItemNotFoundException, InvalidItemAmountException {
        logger.info("calculate sales taxes for #{} item id {}", amount, itemId);

        if (amount <= 0) {
            logger.warn("wrong item amount {}", amount);
            throw new InvalidItemAmountException(amount + " is not a valid amount");
        }

        ItemBean itemBean = itemsService.getItem(itemId)
                .orElseThrow(() -> new ItemNotFoundException("no item found with id " + itemId));

        Long tax = calculateSalesTaxes(itemBean);
        return new SalesTaxesItemBeanBuilder()
                .withItem(itemBean)
                .withAmount(amount)
                .withSaleTax(tax * amount)
                .withTotal((itemBean.getPrice() + tax) * amount)
                .build();
    }

    //============

    Long calculateSalesTaxes(ItemBean item) {
        logger.info("calculate sales taxes for item: {}", item);

        BigDecimal tax = BigDecimal.ZERO;
        tax = tax.add(calculateCategoryTax(item));
        if (item.getImported()) {
            tax = tax.add(calculateImportTax(item));
        }

        return roundToNearestFive(tax);
    }

    BigDecimal calculateCategoryTax(ItemBean item) {
        switch (item.getCategory()) {
            case BOOK:
            case FOOD:
            case MEDICAL:
                return BigDecimal.ZERO;
            case OTHER:
            default:
                return new BigDecimal(item.getPrice())
                        .multiply(new BigDecimal(0.1d));
        }
    }

    BigDecimal calculateImportTax(ItemBean item) {
        return new BigDecimal(item.getPrice())
                .multiply(new BigDecimal(0.05d));
    }

    Long roundToNearestFive(BigDecimal tax) {
        return 5 * (Math.round(tax.doubleValue() / 5));
    }
}
