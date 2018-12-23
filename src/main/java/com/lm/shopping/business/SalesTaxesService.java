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
import java.math.RoundingMode;
import java.util.UUID;

@Singleton
public class SalesTaxesService {
    private final Logger logger = LoggerFactory.getLogger(SalesTaxesService.class);

    @Inject private ItemsService itemsService;
    @Inject private PriceService priceService;

    static final BigDecimal OTHER_CATEGORY_TAX = new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP);
    static final BigDecimal IMPORT_TAX = new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP);

    public SalesTaxesItemBean calculateSalesTaxes(UUID itemId, Long amount) throws ItemNotFoundException, InvalidItemAmountException {
        logger.info("calculate sales taxes for #{} item id {}", amount, itemId);

        if (amount <= 0) {
            logger.warn("wrong item amount {}", amount);
            throw new InvalidItemAmountException(amount + " is not a valid amount");
        }

        ItemBean itemBean = itemsService.getItem(itemId)
                .orElseThrow(() -> new ItemNotFoundException("no item found with id " + itemId));

        BigDecimal tax = calculateSalesTaxes(itemBean);
        BigDecimal saleTax = tax.multiply(new BigDecimal(amount)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = tax.add(itemBean.getPrice()).multiply(new BigDecimal(amount)).setScale(2, RoundingMode.HALF_UP);

        return new SalesTaxesItemBeanBuilder()
                .withItem(itemBean)
                .withAmount(amount)
                .withSaleTax(saleTax)
                .withTotal(total)
                .build();
    }

    //============

    BigDecimal calculateSalesTaxes(ItemBean item) {
        logger.info("calculate sales taxes for item: {}", item);

        BigDecimal tax = new BigDecimal(0);
        tax = tax.add(calculateCategoryTax(item));
        if (item.getImported()) {
            tax = tax.add(calculateImportTax(item));
        }

        return priceService.roundToNearestFiveCent(tax);
    }

    private BigDecimal calculateCategoryTax(ItemBean item) {
        switch (item.getCategory()) {
            case BOOK:
            case FOOD:
            case MEDICAL:
                return BigDecimal.ZERO;
            case OTHER:
            default:
                return item.getPrice().multiply(OTHER_CATEGORY_TAX);
        }
    }

    private BigDecimal calculateImportTax(ItemBean item) {
        return item.getPrice().multiply(IMPORT_TAX);
    }
}
