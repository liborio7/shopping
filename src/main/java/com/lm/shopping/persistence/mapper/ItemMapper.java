package com.lm.shopping.persistence.mapper;

import com.lm.shopping.persistence.model.Item;

import java.util.List;

public interface ItemMapper {

    Item loadById(Item entity);

    List<Item> loadAll();
}
