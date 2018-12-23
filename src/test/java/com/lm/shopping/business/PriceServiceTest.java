package com.lm.shopping.business;

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

    //============ to big decimal

    @Test
    public void shouldConvertFromLongToBigDecimal1() {
        // given
        Long price = 100L;

        // when
        BigDecimal result = service.toBigDecimal(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldConvertFromLongToBigDecimal2() {
        // given
        Long price = 101L;

        // when
        BigDecimal result = service.toBigDecimal(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(1.01).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldConvertFromLongToBigDecimal3() {
        // given
        Long price = 199L;

        // when
        BigDecimal result = service.toBigDecimal(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(1.99).setScale(2, RoundingMode.HALF_UP));
    }

    //============ to long

    @Test
    public void shouldConvertFromBigDecimalToLong1() {
        // given
        BigDecimal price = new BigDecimal(1.00);

        // when
        Long result = service.toLong(price);

        // then
        assertThat(result).isEqualTo(100L);
    }

    @Test
    public void shouldConvertFromBigDecimalToLong2() {
        // given
        BigDecimal price = new BigDecimal(1.11111);

        // when
        Long result = service.toLong(price);

        // then
        assertThat(result).isEqualTo(111L);
    }

    @Test
    public void shouldConvertFromBigDecimalToLong3() {
        // given
        BigDecimal price = new BigDecimal(1.9966);

        // when
        Long result = service.toLong(price);

        // then
        assertThat(result).isEqualTo(200L);
    }

    //============ round to nearest five

    @Test
    public void shouldRoundToNearestFive1() {
        // given
        BigDecimal price = new BigDecimal(2.02);

        // when
        BigDecimal result = service.roundToNearestFiveCent(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldRoundToNearestFive2() {
        // given
        BigDecimal price = new BigDecimal(2.04);

        // when
        BigDecimal result = service.roundToNearestFiveCent(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldRoundToNearestFive3() {
        // given
        BigDecimal price = new BigDecimal(2.05);

        // when
        BigDecimal result = service.roundToNearestFiveCent(price);

        // then
        assertThat(result).isEqualTo(new BigDecimal(2.05).setScale(2, RoundingMode.HALF_UP));
    }
}