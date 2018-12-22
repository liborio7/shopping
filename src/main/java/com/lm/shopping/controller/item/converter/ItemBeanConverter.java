package com.lm.shopping.controller.item.converter;

import com.lm.shopping.business.PriceService;
import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.controller.item.bean.ItemResponseBean;
import com.lm.shopping.controller.item.bean.ItemResponseBeanBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemBeanConverter {

    @Inject private PriceService priceService;

    public ItemResponseBean convertToResponseBean(ItemBean item) {
        return new ItemResponseBeanBuilder()
                .withId(item.getId())
                .withName(item.getName())
                .withCategory(item.getCategory())
                .withPrice(priceService.toBigDecimal(item.getPrice()))
                .withImported(item.getImported())
                .build();
    }
}
