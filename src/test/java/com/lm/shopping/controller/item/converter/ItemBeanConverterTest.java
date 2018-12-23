package com.lm.shopping.controller.item.converter;

import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.controller.item.bean.ItemResponseBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ItemBeanConverterTest {

    @InjectMocks private ItemBeanConverter converter;

    @Test
    public void shouldConvert() {
        // given
        ItemBean itemBean = random(ItemBean.class);

        // when
        ItemResponseBean result = converter.convertToResponseBean(itemBean);

        // then
        assertThat(result.getId()).isEqualTo(itemBean.getId());
        assertThat(result.getName()).isEqualTo(itemBean.getName());
        assertThat(result.getCategory()).isEqualTo(itemBean.getCategory());
        assertThat(result.getPrice()).isEqualTo(itemBean.getPrice());
        assertThat(result.getImported()).isEqualTo(itemBean.getImported());
    }
}