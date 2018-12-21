package com.lm.shopping.items.service;

import com.lm.shopping.items.persistence.dao.ItemDao;
import com.lm.shopping.items.persistence.model.ItemBuilder;
import com.lm.shopping.items.service.bean.ItemBean;
import com.lm.shopping.items.service.converter.ItemConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class ItemsService {
    private final Logger logger = LoggerFactory.getLogger(ItemsService.class);

    @Inject private ItemDao itemDao;
    @Inject private ItemConverter itemConverter;

    public Optional<ItemBean> getItem(UUID id) {
        logger.debug("get item by id {}", id);

        return Optional.ofNullable(itemDao.loadById(new ItemBuilder().withId(id).build()))
                .map(itemConverter::convertToBean);
    }

    public List<ItemBean> getItems() {
        logger.debug("get items");

        return itemDao.loadAll().stream()
                .map(itemConverter::convertToBean)
                .collect(Collectors.toList());
    }
}
