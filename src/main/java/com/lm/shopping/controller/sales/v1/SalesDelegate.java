package com.lm.shopping.controller.sales.v1;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SalesDelegate {
    private final Logger logger = LoggerFactory.getLogger(SalesDelegate.class);

    @Inject private SalesTaxesService salesTaxesService;

    public SalesResponseBean insertSales(SalesItemRequestBean[] requestBeans) {
        logger.info("insert #{} items sales", requestBeans.length);

        try {
            List<SalesItemResponseBean> items = new ArrayList<>();
            BigDecimal salesTaxes = new BigDecimal(0);
            BigDecimal total = new BigDecimal(0);
            for (SalesItemRequestBean requestBean : requestBeans) {

                SalesTaxesItemBean salesTaxesItemBean = salesTaxesService.calculateSalesTaxes(requestBean.getItemId(), requestBean.getAmount());
                ItemBean item = salesTaxesItemBean.getItem();

                items.add(new SalesItemResponseBeanBuilder()
                        .withId(item.getId())
                        .withAmount(salesTaxesItemBean.getAmount())
                        .withName(item.getName())
                        .withCategory(item.getCategory())
                        .withPrice(salesTaxesItemBean.getTotal())
                        .withImported(item.getImported())
                        .build());
                salesTaxes = salesTaxes.add(salesTaxesItemBean.getSaleTax());
                total = total.add(salesTaxesItemBean.getTotal());
            }

            return new SalesResponseBeanBuilder()
                    .withItems(items)
                    .withSalesTaxes(salesTaxes)
                    .withTotal(total)
                    .build();

        } catch (ItemNotFoundException | InvalidItemAmountException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
