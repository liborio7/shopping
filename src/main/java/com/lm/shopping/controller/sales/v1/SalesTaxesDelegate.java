package com.lm.shopping.controller.sales.v1;

import com.lm.shopping.business.PriceService;
import com.lm.shopping.business.SalesTaxesService;
import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.business.bean.SalesTaxesItemBean;
import com.lm.shopping.business.exception.InvalidItemAmountException;
import com.lm.shopping.business.exception.ItemNotFoundException;
import com.lm.shopping.controller.sales.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SalesTaxesDelegate {
    private final Logger logger = LoggerFactory.getLogger(SalesTaxesDelegate.class);

    @Inject private PriceService priceService;
    @Inject private SalesTaxesService salesTaxesService;

    public SalesTaxesResponseBean calculateSalesTaxes(SalesTaxesItemRequestBean[] requestBeans) {
        logger.info("calculate sales taxes for #{} items", requestBeans.length);

        try {
            List<SalesTaxesItemResponseBean> items = new ArrayList<>();
            Long salesTaxes = 0L;
            Long total = 0L;
            for (SalesTaxesItemRequestBean requestBean : requestBeans) {

                SalesTaxesItemBean salesTaxesItemBean = salesTaxesService.calculateSalesTaxes(requestBean.getItemId(), requestBean.getAmount());
                ItemBean item = salesTaxesItemBean.getItem();

                items.add(new SalesTaxesItemResponseBeanBuilder()
                        .withId(item.getId())
                        .withAmount(salesTaxesItemBean.getAmount())
                        .withName(item.getName())
                        .withCategory(item.getCategory())
                        .withPrice(priceService.toBigDecimal(salesTaxesItemBean.getTotal()))
                        .withImported(item.getImported())
                        .build());
                salesTaxes += salesTaxesItemBean.getSaleTax();
                total += salesTaxesItemBean.getTotal();
            }

            return new SalesTaxesResponseBeanBuilder()
                    .withItems(items)
                    .withSalesTaxes(priceService.toBigDecimal(salesTaxes))
                    .withTotal(priceService.toBigDecimal(total))
                    .build();

        } catch (ItemNotFoundException | InvalidItemAmountException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
