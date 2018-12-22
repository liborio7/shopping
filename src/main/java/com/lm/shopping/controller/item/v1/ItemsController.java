package com.lm.shopping.controller.item.v1;

import com.lm.shopping.controller.item.bean.ItemResponseBean;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Singleton
@Path("/v1/items")
public class ItemsController {

    @Inject private ItemsDelegate delegate;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemResponseBean> getItems() {
        return delegate.getItems();
    }

    @GET
    @Path("{uid: [0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemResponseBean getItem(@PathParam("uid") UUID uid) {
        return delegate.getItem(uid);
    }
}
