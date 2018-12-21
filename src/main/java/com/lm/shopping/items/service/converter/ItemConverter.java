package com.lm.shopping.items.service.converter;

import com.lm.shopping.items.persistence.model.Item;
import com.lm.shopping.items.service.bean.ItemBean;
import com.lm.shopping.items.service.bean.ItemBeanBuilder;

import javax.inject.Singleton;

@Singleton
public class ItemConverter {

    public ItemBean convertToBean(Item item) {
        return new ItemBeanBuilder()
                .withId(item.getId())
                .withName(item.getName())
                .withCategory(item.getCategory())
                .withPrice(item.getPrice())
                .withImported(item.getImported())
                .build();
    }
}
