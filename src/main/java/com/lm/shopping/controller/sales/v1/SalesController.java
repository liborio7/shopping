package com.lm.shopping.controller.sales.v1;

import com.lm.shopping.controller.sales.bean.SalesTaxesItemRequestBean;
import com.lm.shopping.controller.sales.bean.SalesTaxesResponseBean;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/sales")
public class SalesController {

    @Inject private SalesTaxesDelegate delegate;

    @POST
    @Path("/taxes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SalesTaxesResponseBean calculateSalesTaxes(SalesTaxesItemRequestBean[] items) {
        return delegate.calculateSalesTaxes(items);
    }

}
