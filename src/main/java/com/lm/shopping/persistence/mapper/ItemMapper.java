package com.lm.shopping.persistence.mapper;

import com.lm.shopping.persistence.model.Item;

import java.util.List;

public interface ItemMapper {

    Integer insert(Item entity);

    Item loadById(Item entity);

    List<Item> loadAll();
}
