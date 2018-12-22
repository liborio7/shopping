package com.lm.shopping.controller.sales.v1;

import com.lm.shopping.business.PriceService;
import com.lm.shopping.business.SalesTaxesService;
import com.lm.shopping.business.bean.SalesTaxesItemBean;
import com.lm.shopping.business.exception.InvalidItemAmountException;
import com.lm.shopping.business.exception.ItemNotFoundException;
import com.lm.shopping.controller.sales.bean.SalesItemRequestBean;
import com.lm.shopping.controller.sales.bean.SalesItemRequestBeanBuilder;
import com.lm.shopping.controller.sales.bean.SalesResponseBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesDelegateTest {

    @Mock private PriceService priceService;
    @Mock private SalesTaxesService salesTaxesService;
    @InjectMocks private SalesDelegate delegate;

    @Before
    public void setUp() {
        when(priceService.toBigDecimal(any())).thenCallRealMethod();
    }

    @Test
    public void shouldInsertSales() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        SalesItemRequestBean[] requestBeans = IntStream.range(0, 5)
                .mapToObj(__ -> new SalesItemRequestBeanBuilder()
                        .withAmount(1L)
                        .withItemId(UUID.randomUUID())
                        .build())
                .toArray(SalesItemRequestBean[]::new);
        SalesTaxesItemBean salesTaxesItemBean = random(SalesTaxesItemBean.class);

        // when
        when(salesTaxesService.calculateSalesTaxes(any(), any())).thenReturn(salesTaxesItemBean);

        SalesResponseBean result = delegate.insertSales(requestBeans);

        // then
        BigDecimal expectedSalesTaxes = priceService.toBigDecimal(salesTaxesItemBean.getSaleTax() * requestBeans.length);
        BigDecimal expectesTotal = priceService.toBigDecimal(salesTaxesItemBean.getTotal() * requestBeans.length);

        assertThat(result).isNotNull();
        assertThat(result.getItems()).hasSameSizeAs(requestBeans);
        assertThat(result.getSalesTaxes()).isEqualTo(expectedSalesTaxes);
        assertThat(result.getTotal()).isEqualTo(expectesTotal);
    }

    @Test(expected = BadRequestException.class)
    public void shouldBadRequestOnInsertSalesForInvalidAmount() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        SalesItemRequestBean[] requestBeans = IntStream.range(0, 5)
                .mapToObj(__ -> new SalesItemRequestBeanBuilder()
                        .withAmount(-1L)
                        .withItemId(UUID.randomUUID())
                        .build())
                .toArray(SalesItemRequestBean[]::new);

        // when
        when(salesTaxesService.calculateSalesTaxes(any(), any())).thenThrow(InvalidItemAmountException.class);

        delegate.insertSales(requestBeans);
    }

    @Test(expected = BadRequestException.class)
    public void shouldBadRequestOnInsertSalesForItemNotFound() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        SalesItemRequestBean[] requestBeans = IntStream.range(0, 5)
                .mapToObj(__ -> new SalesItemRequestBeanBuilder()
                        .withAmount(-1L)
                        .withItemId(UUID.randomUUID())
                        .build())
                .toArray(SalesItemRequestBean[]::new);

        // when
        when(salesTaxesService.calculateSalesTaxes(any(), any())).thenThrow(ItemNotFoundException.class);

        delegate.insertSales(requestBeans);
    }

}