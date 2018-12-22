package com.lm.shopping.business;

import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.business.converter.ItemConverter;
import com.lm.shopping.persistence.dao.ItemDao;
import com.lm.shopping.persistence.model.Item;
import com.lm.shopping.persistence.model.ItemBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {

    @Mock private ItemDao itemDao;
    @Mock private ItemConverter itemConverter;
    @InjectMocks private ItemsService service;

    @Before
    public void setUp() {
        when(itemConverter.convertToBean(any())).thenCallRealMethod();
    }

    @Test
    public void shouldGetItem() {
        // given
        UUID id = UUID.randomUUID();
        Item item = random(ItemBuilder.class)
                .withId(id)
                .build();

        // when
        when(itemDao.loadById(any())).thenReturn(item);

        Optional<ItemBean> result = service.getItem(id);

        // then
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(value ->
                assertThat(value.getId().equals(item.getId()) &&
                        value.getName().equals(item.getName()) &&
                        value.getCategory().equals(item.getCategory()) &&
                        value.getPrice().equals(item.getPrice()) &&
                        value.getImported().equals(item.getImported())
                ).isTrue());
    }

    @Test
    public void shouldGetEmptyItem() {
        // given
        UUID id = UUID.randomUUID();
        Item item = random(ItemBuilder.class)
                .withId(id)
                .build();

        // when
        when(itemDao.loadById(any())).thenReturn(null);

        Optional<ItemBean> result = service.getItem(id);

        // then
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void shouldGetItems() {
        // given
        List<Item> items = randomListOf(10, Item.class);

        // when
        when(itemDao.loadAll()).thenReturn(items);

        // when
        List<ItemBean> result = service.getItems();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSameSizeAs(items);
        assertThat(result.stream().allMatch(bean ->
                items.stream().anyMatch(item -> item.getId().equals(bean.getId()) &&
                        item.getName().equals(bean.getName()) &&
                        item.getCategory().equals(bean.getCategory()) &&
                        item.getPrice().equals(bean.getPrice()) &&
                        item.getImported().equals(bean.getImported()))
        ));
    }

    @Test
    public void shouldGetEmptyItems() {
        // when
        when(itemDao.loadAll()).thenReturn(Collections.emptyList());

        // when
        List<ItemBean> result = service.getItems();

        // then
        assertThat(result).isEmpty();
    }
}