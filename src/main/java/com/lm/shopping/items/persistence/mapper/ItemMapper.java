package com.lm.shopping.items.persistence.mapper;

import com.lm.shopping.items.persistence.model.Item;

import java.util.List;

public interface ItemMapper {

    Item loadById(Item entity);

    List<Item> loadAll();
}
