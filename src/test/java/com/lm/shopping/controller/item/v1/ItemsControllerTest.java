package com.lm.shopping.controller.item.v1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lm.shopping.controller.BaseControllerTest;
import com.lm.shopping.controller.item.bean.ItemResponseBean;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemsControllerTest extends BaseControllerTest {

    private static final String GET_ITEMS_URL = "v1/items";
    private static final String GET_ITEM_URL = "v1/items/%s";

    @Test
    public void shouldGetItemsAndGetItem() {
        // get items
        Response responseGetAll = target(GET_ITEMS_URL).request().get();
        assertThat(responseGetAll.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        List<ItemResponseBean> getAll = objectMapper.convertValue(
                responseGetAll.readEntity(ArrayList.class),
                new TypeReference<ArrayList<ItemResponseBean>>() {
                }
        );

        // assert not empty
        assertThat(getAll).isNotNull();
        assertThat(getAll).isNotEmpty();

        // get item
        ItemResponseBean item = getAll.get(0);

        Response responseGet = target(String.format(GET_ITEM_URL, item.getId())).request().get();
        assertThat(responseGet.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        ItemResponseBean get = objectMapper.convertValue(responseGet.readEntity(Object.class), ItemResponseBean.class);

        // assert item
        assertThat(get).isNotNull();
        assertThat(get).isEqualToComparingFieldByField(item);
    }

}