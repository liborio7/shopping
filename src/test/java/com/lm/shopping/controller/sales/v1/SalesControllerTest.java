package com.lm.shopping.controller.sales.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lm.shopping.controller.BaseControllerTest;
import com.lm.shopping.controller.sales.bean.SalesItemRequestBean;
import com.lm.shopping.controller.sales.bean.SalesItemRequestBeanBuilder;
import com.lm.shopping.controller.sales.bean.SalesResponseBean;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesControllerTest extends BaseControllerTest {

    private static final String POST_SALES_URL = "v1/sales";

    private static final String FIRST_BOOK_ID = "67870ad6-bc7c-47cb-bcad-96b18b2366ad";
    private static final String FIRST_MUSIC_CD_ID = "1f8e66f8-07ef-4bb4-bd1d-c8ea7654818a";
    private static final String FIRST_CHOCOLATE_BAR_ID = "f1cfa5bd-abcf-4d22-904d-58675f7e1d7c";

    private static final String SECOND_IMPORTED_BOX_OF_CHOCOLATE_ID = "1aaec938-8e02-4379-b338-3b55f1275859";
    private static final String SECOND_IMPORTED_BOTTLE_OF_PERFUME_ID = "39d3f34d-1812-4b62-b2f0-cfcfa993aed0";

    private static final String THIRD_IMPORTED_BOTTLE_OF_PERFUME_ID = "f07c7b95-98d4-4621-8cf9-a6fc1c4ea02d";
    private static final String THIRD_BOTTLE_OF_PERFUME_ID = "80ed3311-5b14-43ee-80a0-59305ebda65e";
    private static final String THIRD_PACKAGE_OF_HEAHACHE_PILLS_ID = "b23cef17-ea11-4d0f-802c-956324d5ecfc";
    private static final String THIRD_BOX_OF_IMPORTED_CHOCOLATE_ID = "2eb76baa-1add-417b-bd22-c038b2df6347";

    @Test
    public void shouldInsertSales1() throws JsonProcessingException {
        // insert sales
        SalesItemRequestBean[] requestBody = new SalesItemRequestBean[]{
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(FIRST_BOOK_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(FIRST_MUSIC_CD_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(FIRST_CHOCOLATE_BAR_ID))
                        .withAmount(1L)
                        .build()
        };
        Response responsePost = target(POST_SALES_URL).request().post(Entity.json(objectMapper.writeValueAsString(requestBody)));
        assertThat(responsePost.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        // check response
        SalesResponseBean sales = objectMapper.convertValue(responsePost.readEntity(Object.class), SalesResponseBean.class);
        assertThat(sales).isNotNull();

        // check items prices
        sales.getItems().forEach(item -> {
            switch (item.getId().toString()) {
                case FIRST_BOOK_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP));
                    break;
                case FIRST_MUSIC_CD_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(16.49).setScale(2, RoundingMode.HALF_UP));
                    break;
                case FIRST_CHOCOLATE_BAR_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(0.85).setScale(2, RoundingMode.HALF_UP));
                    break;
            }
        });

        // check sales taxes and total
        assertThat(sales.getSalesTaxes().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP));
        assertThat(sales.getTotal().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldInsertSales2() throws JsonProcessingException {
        // insert sales
        SalesItemRequestBean[] requestBody = new SalesItemRequestBean[]{
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(SECOND_IMPORTED_BOX_OF_CHOCOLATE_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(SECOND_IMPORTED_BOTTLE_OF_PERFUME_ID))
                        .withAmount(1L)
                        .build()
        };
        Response responsePost = target(POST_SALES_URL).request().post(Entity.json(objectMapper.writeValueAsString(requestBody)));
        assertThat(responsePost.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        // check response
        SalesResponseBean sales = objectMapper.convertValue(responsePost.readEntity(Object.class), SalesResponseBean.class);
        assertThat(sales).isNotNull();

        // check items prices
        sales.getItems().forEach(item -> {
            switch (item.getId().toString()) {
                case SECOND_IMPORTED_BOX_OF_CHOCOLATE_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP));
                    break;
                case SECOND_IMPORTED_BOTTLE_OF_PERFUME_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(54.65).setScale(2, RoundingMode.HALF_UP));
                    break;
            }
        });

        // check sales taxes and total
        assertThat(sales.getSalesTaxes().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(7.65).setScale(2, RoundingMode.HALF_UP));
        assertThat(sales.getTotal().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(65.15).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldInsertSales3() throws JsonProcessingException {
        // insert sales
        SalesItemRequestBean[] requestBody = new SalesItemRequestBean[]{
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(THIRD_IMPORTED_BOTTLE_OF_PERFUME_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(THIRD_BOTTLE_OF_PERFUME_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(THIRD_PACKAGE_OF_HEAHACHE_PILLS_ID))
                        .withAmount(1L)
                        .build(),
                new SalesItemRequestBeanBuilder()
                        .withItemId(UUID.fromString(THIRD_BOX_OF_IMPORTED_CHOCOLATE_ID))
                        .withAmount(1L)
                        .build()

        };
        Response responsePost = target(POST_SALES_URL).request().post(Entity.json(objectMapper.writeValueAsString(requestBody)));
        assertThat(responsePost.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        // check response
        SalesResponseBean sales = objectMapper.convertValue(responsePost.readEntity(Object.class), SalesResponseBean.class);
        assertThat(sales).isNotNull();

        // check items prices
        sales.getItems().forEach(item -> {
            switch (item.getId().toString()) {
                case THIRD_IMPORTED_BOTTLE_OF_PERFUME_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP));
                    break;
                case THIRD_BOTTLE_OF_PERFUME_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(20.89).setScale(2, RoundingMode.HALF_UP));
                    break;
                case THIRD_PACKAGE_OF_HEAHACHE_PILLS_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(9.75).setScale(2, RoundingMode.HALF_UP));
                    break;
                case THIRD_BOX_OF_IMPORTED_CHOCOLATE_ID:
                    assertThat(item.getPrice().setScale(2, RoundingMode.HALF_UP))
                            .isEqualTo(new BigDecimal(11.85).setScale(2, RoundingMode.HALF_UP));
                    break;
            }
        });

        // check sales taxes and total
        assertThat(sales.getSalesTaxes().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP));
        assertThat(sales.getTotal().setScale(2, RoundingMode.HALF_UP))
                .isEqualTo(new BigDecimal(74.68).setScale(2, RoundingMode.HALF_UP));
    }
}