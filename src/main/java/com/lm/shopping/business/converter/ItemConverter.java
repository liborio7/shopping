package com.lm.shopping.business.converter;

import com.lm.shopping.business.PriceService;
import com.lm.shopping.persistence.model.Item;
import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.business.bean.ItemBeanBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemConverter {

    @Inject private PriceService priceService;

    public ItemBean convertToBean(Item item) {
        return new ItemBeanBuilder()
                .withId(item.getId())
                .withName(item.getName())
                .withCategory(item.getCategory())
                .withPrice(priceService.toBigDecimal(item.getPrice()))
                .withImported(item.getImported())
                .build();
    }
}
