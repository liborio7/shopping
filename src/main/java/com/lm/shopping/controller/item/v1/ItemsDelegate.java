package com.lm.shopping.controller.item.v1;

import com.lm.shopping.business.ItemsService;
import com.lm.shopping.controller.item.bean.ItemResponseBean;
import com.lm.shopping.controller.item.converter.ItemBeanConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class ItemsDelegate {
    private final Logger logger = LoggerFactory.getLogger(ItemsDelegate.class);

    @Inject private ItemsService itemsService;
    @Inject private ItemBeanConverter itemBeanConverter;

    public List<ItemResponseBean> getItems() {
        logger.info("get items");

        return itemsService.getItems()
                .stream()
                .map(itemBeanConverter::convertToResponseBean)
                .collect(Collectors.toList());
    }

    public ItemResponseBean getItem(UUID id) {
        logger.info("get item with id: {}", id);

        return itemsService.getItem(id)
                .map(itemBeanConverter::convertToResponseBean)
                .orElseThrow(NotFoundException::new);
    }
}
