package com.lm.shopping.business;


import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Singleton
public class PriceService {

    public BigDecimal toBigDecimal(Long price) {
        return new BigDecimal((double) price / 100).setScale(2, RoundingMode.HALF_UP);
    }

}