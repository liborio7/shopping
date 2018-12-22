package com.lm.shopping.business;


import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Singleton
public class PriceService {

    public BigDecimal toBigDecimal(Long price) {
        return new BigDecimal(price)
                .divide(new BigDecimal(100D), 2, RoundingMode.HALF_UP);
    }

    public Long toLong(BigDecimal price) {
        return price
                .multiply(new BigDecimal(100D))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }

    public BigDecimal roundToNearestFive(BigDecimal price) {
        return price
                .divide(new BigDecimal(5), 0, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(5));
    }

}