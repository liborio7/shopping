package com.lm.shopping.items.controller.v1;

import com.lm.shopping.items.controller.bean.ItemResponseBean;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/v1/items")
public class ItemsController {

    @Inject private ItemsDelegate delegate;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemResponseBean> getItems() {
        return delegate.getItems();
    }
}
