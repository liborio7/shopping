package com.lm.shopping.controller.item.v1;

import com.lm.shopping.business.ItemsService;
import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.controller.item.bean.ItemResponseBean;
import com.lm.shopping.controller.item.converter.ItemBeanConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsDelegateTest {

    @Mock private ItemsService itemsService;
    @Mock private ItemBeanConverter itemBeanConverter;
    @InjectMocks private ItemsDelegate delegate;

    @Test
    public void shouldGetItems() {
        // given
        List<ItemBean> itemBeanList = randomListOf(10, ItemBean.class);
        ItemResponseBean itemResponseBean = random(ItemResponseBean.class);

        // when
        when(itemsService.getItems()).thenReturn(itemBeanList);
        when(itemBeanConverter.convertToResponseBean(any())).thenReturn(itemResponseBean);

        List<ItemResponseBean> result = delegate.getItems();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.stream().allMatch(item -> item.equals(itemResponseBean)));
    }

    @Test
    public void shouldGetEmptyItems() {
        // when
        when(itemsService.getItems()).thenReturn(Collections.emptyList());

        List<ItemResponseBean> result = delegate.getItems();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldGetItemById() {
        // given
        UUID id = UUID.randomUUID();
        ItemBean itemBean = random(ItemBean.class);
        ItemResponseBean itemResponseBean = random(ItemResponseBean.class);

        // when
        when(itemsService.getItem(eq(id))).thenReturn(Optional.of(itemBean));
        when(itemBeanConverter.convertToResponseBean(any())).thenReturn(itemResponseBean);

        ItemResponseBean result = delegate.getItem(id);

        // then
        assertThat(result).isEqualTo(itemResponseBean);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundOnGetItemById() {
        // given
        UUID id = UUID.randomUUID();

        // when
        when(itemsService.getItem(eq(id))).thenReturn(Optional.empty());

        delegate.getItem(id);
    }
}