package com.yago.app.demo.client.java17.controller;

import com.yago.app.demo.client.java17.model.Product;
import com.yago.app.demo.client.java17.service.ProductService;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        return Response.ok().entity(productService.createProductAndListProducts(product)).build();
    }
}
