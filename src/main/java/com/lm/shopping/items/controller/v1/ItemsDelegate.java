package com.lm.shopping.items.controller.v1;

import com.lm.shopping.common.service.PriceService;
import com.lm.shopping.items.controller.bean.ItemResponseBean;
import com.lm.shopping.items.controller.bean.ItemResponseBeanBuilder;
import com.lm.shopping.items.business.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ItemsDelegate {
    private final Logger logger = LoggerFactory.getLogger(ItemsDelegate.class);

    @Inject private ItemsService itemsService;
    @Inject private PriceService priceService;

    public List<ItemResponseBean> getItems() {
        logger.info("get items");

        return itemsService.getItems()
                .stream()
                .map(item -> new ItemResponseBeanBuilder()
                        .withId(item.getId())
                        .withName(item.getName())
                        .withCategory(item.getCategory())
                        .withPrice(priceService.toBigDecimal(item.getPrice()))
                        .withImported(item.getImported())
                        .build())
                .collect(Collectors.toList());
    }
}
