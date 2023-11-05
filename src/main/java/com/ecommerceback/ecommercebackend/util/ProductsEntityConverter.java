package com.ecommerceback.ecommercebackend.util;

import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsEntityConverter {

    public static List<ProductsResponse> findResults(List<Products> products) {
        List<ProductsResponse> productsResponses = new ArrayList<>();
        for (Products product : products) {
            productsResponses.add(new ProductsResponse(product.getId(), product.getName(),
                    product.getDescription(), product.getColor(), product.getGender(),
                    product.getRating(), product.getPrice()));
        }
        return productsResponses;
    }

    public static ProductsResponse findResult(Products product) {
        return new ProductsResponse(product.getId(), product.getName(),
                product.getDescription(), product.getColor(), product.getGender(),
                product.getRating(), product.getPrice());
    }


}
