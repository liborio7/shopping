package com.lm.shopping.business.converter;

import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.persistence.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ItemConverterTest {

    @InjectMocks private ItemConverter converter;

    @Test
    public void shouldConvert() {
        // given
        Item item = random(Item.class);

        // when
        ItemBean result = converter.convertToBean(item);

        // then
        assertThat(result.getId()).isEqualTo(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
        assertThat(result.getCategory()).isEqualTo(item.getCategory());
        assertThat(result.getPrice()).isEqualTo(item.getPrice());
        assertThat(result.getImported()).isEqualTo(item.getImported());
    }
}