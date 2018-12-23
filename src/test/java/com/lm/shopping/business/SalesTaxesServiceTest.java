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
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

import static com.lm.shopping.business.SalesTaxesService.IMPORT_TAX;
import static com.lm.shopping.business.SalesTaxesService.OTHER_CATEGORY_TAX;
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
        when(priceService.roundToNearestFiveCent(any())).thenCallRealMethod();
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
        BigDecimal expectedSaleTax = expectedTax.multiply(new BigDecimal(amount));
        BigDecimal expectedTotal = expectedTax.add(itemBean.getPrice()).multiply(new BigDecimal(amount));

        assertThat(result).isNotNull();
        assertThat(result.getItem()).isEqualTo(itemBean);
        assertThat(result.getAmount()).isEqualTo(amount);
        assertThat(result.getSaleTax()).isEqualTo(expectedSaleTax);
        assertThat(result.getTotal()).isEqualTo(expectedTotal);
    }

    @Test
    public void shouldCalculateSalesTaxesForExemptedCategoryAndNotImportedItem() {
        // given
        ItemBean itemBean = new ItemBeanBuilder()
                .withPrice(new BigDecimal(1.00))
                .withCategory(ItemCategoryEnum.FOOD)
                .withImported(false)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        assertThat(tax).isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldCalculateSalesTaxesForOtherCategoryAndNotImportedItem() {
        // given
        ItemBean itemBean = new ItemBeanBuilder()
                .withPrice(new BigDecimal(1.00))
                .withCategory(ItemCategoryEnum.OTHER)
                .withImported(false)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        assertThat(tax).isEqualTo(OTHER_CATEGORY_TAX);
    }

    @Test
    public void shouldCalculateSalesTaxesForExemptedCategoryAndImportedItem() {
        // given
        ItemBean itemBean = random(ItemBeanBuilder.class)
                .withPrice(new BigDecimal(1.00))
                .withCategory(ItemCategoryEnum.MEDICAL)
                .withImported(true)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        assertThat(tax).isEqualTo(IMPORT_TAX);
    }

    @Test
    public void shouldCalculateSalesTaxesForOtherCategoryAndImportedItem() {
        // given
        ItemBean itemBean = new ItemBeanBuilder()
                .withPrice(new BigDecimal(1.00))
                .withCategory(ItemCategoryEnum.OTHER)
                .withImported(true)
                .build();

        // when
        BigDecimal tax = service.calculateSalesTaxes(itemBean);

        // then
        assertThat(tax).isEqualTo(OTHER_CATEGORY_TAX.add(IMPORT_TAX));
    }
}