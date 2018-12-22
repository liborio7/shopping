package com.lm.shopping.business;

import com.lm.shopping.business.bean.ItemBean;
import com.lm.shopping.business.bean.ItemBeanBuilder;
import com.lm.shopping.business.bean.SalesTaxesItemBean;
import com.lm.shopping.business.exception.InvalidItemAmountException;
import com.lm.shopping.business.exception.ItemNotFoundException;
import com.lm.shopping.common.enums.ItemCategoryEnum;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesTaxesServiceTest {

    @Mock private ItemsService itemsService;
    @Mock private PriceService priceService;
    @InjectMocks private SalesTaxesService service;

    @Before
    public void setUp() {
        when(priceService.toLong(any())).thenCallRealMethod();
        when(priceService.roundToNearestFive(any())).thenCallRealMethod();
    }

    @Test(expected = InvalidItemAmountException.class)
    public void shouldThrowInvalidItemAmountOnCalculateSalesTaxes() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        UUID id = UUID.randomUUID();
        Long amount = -RandomUtils.nextLong(1, 10);

        // when
        service.calculateSalesTaxes(id, amount);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowItemNotFoundOnCalculateSalesTaxes() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        UUID id = UUID.randomUUID();
        Long amount = RandomUtils.nextLong(1, 10);

        // when
        when(itemsService.getItem(eq(id))).thenReturn(Optional.empty());

        service.calculateSalesTaxes(id, amount);
    }

    @Test
    public void shouldCalculateSalesTaxesForItemUid() throws InvalidItemAmountException, ItemNotFoundException {
        // given
        UUID id = UUID.randomUUID();
        Long amount = RandomUtils.nextLong(1, 10);
        ItemBean itemBean = random(ItemBean.class);

        // when
        when(itemsService.getItem(eq(id))).thenReturn(Optional.of(itemBean));

        SalesTaxesItemBean result = service.calculateSalesTaxes(id, amount);

        // then
        BigDecimal expectedTax = service.calculateSalesTaxes(itemBean);
        Long expectedSaleTax = priceService.toLong(expectedTax.multiply(new BigDecimal(amount)));
        Long expectedTotal = priceService.toLong(expectedTax.add(new BigDecimal(itemBean.getPrice())).multiply(new BigDecimal(amount)));

        assertThat(result).isNotNull();
        assertThat(result.getItem()).isEqualTo(itemBean);
        assertThat(result.getAmount()).isEqualTo(amount);
        assertThat(result.getSaleTax()).isEqualTo(expectedSaleTax);
        assertThat(result.getTotal()).isEqualTo(expectedTotal);
    }

    @Test
    public void shouldCalculateSalesTaxesForExemptedCategoryAndNotImportedItem() {
        // given
        ItemBean itemBean = random(ItemBeanBuilder.class)
                .withCategory(ItemCategoryEnum.FOOD)
                .withImported(false)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        assertThat(tax).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void shouldCalculateSalesTaxesForOtherCategoryAndNotImportedItem() {
        // given
        ItemBean itemBean = random(ItemBeanBuilder.class)
                .withCategory(ItemCategoryEnum.OTHER)
                .withImported(false)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        BigDecimal expectedTax = priceService.roundToNearestFive(
                service.calculateCategoryTax(itemBean));

        assertThat(tax).isEqualTo(expectedTax);
    }

    @Test
    public void shouldCalculateSalesTaxesForExemptedCategoryAndImportedItem() {
        // given
        ItemBean itemBean = random(ItemBeanBuilder.class)
                .withCategory(ItemCategoryEnum.MEDICAL)
                .withImported(true)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        BigDecimal expectedTax = priceService.roundToNearestFive(
                service.calculateImportTax(itemBean));

        assertThat(tax).isEqualTo(expectedTax);
    }

    @Test
    public void shouldCalculateSalesTaxesForOtherCategoryAndImportedItem() {
        // given
        ItemBean itemBean = random(ItemBeanBuilder.class)
                .withCategory(ItemCategoryEnum.OTHER)
                .withImported(true)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        BigDecimal expectedTax = priceService.roundToNearestFive(
                service.calculateCategoryTax(itemBean).add(service.calculateImportTax(itemBean)));

        assertThat(tax).isEqualTo(expectedTax);
    }
}