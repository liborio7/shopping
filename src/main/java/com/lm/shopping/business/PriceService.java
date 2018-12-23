package com.lm.shopping.business;


import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Singleton
public class PriceService {

    public BigDecimal toBigDecimal(Long price) {
        return new BigDecimal(price)
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    public Long toLong(BigDecimal price) {
        return price
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }

    public BigDecimal roundUpToNearestFiveCent(BigDecimal price) {
        return price
                .divide(new BigDecimal(0.05), 0, RoundingMode.CEILING)
                .multiply(new BigDecimal(0.05))
                .setScale(2, RoundingMode.HALF_UP);
    }

}