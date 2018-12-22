package com.lm.shopping.business;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @InjectMocks private PriceService service;

    @Test
    public void should() {
        // given
        Long price = RandomUtils.nextLong(1, 1000);

        // when
        BigDecimal bigDecimal = service.toBigDecimal(price);

        // then
        assertThat(bigDecimal).isEqualTo(new BigDecimal((double) price / 100).setScale(2, RoundingMode.HALF_UP));
    }
}