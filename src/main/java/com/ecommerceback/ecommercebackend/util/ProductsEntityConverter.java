package com.ecommerceback.ecommercebackend.util;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsEntityConverter {

    public static List<ProductsResponse> findResults(List<Products> products) {
        List<ProductsResponse> productsResponses = new ArrayList<>();
        for (Products product : products) {
            CategoriesResponse categoriesResponse = null;
            if (product.getCategories() != null) {
                categoriesResponse = new CategoriesResponse(product.getCategories().getId(), product.getCategories().getName());
            }
            productsResponses.add(new ProductsResponse(product.getId(), product.getName(),
                    product.getDescription(), product.getColor(), product.getGender(),
                    product.getRating(), product.getPrice(), categoriesResponse));
        }
        return productsResponses;
    }

    public static ProductsResponse findResult(Products product) {
        CategoriesResponse categoriesResponse = null;
        if (product.getCategories() != null) {
            categoriesResponse = new CategoriesResponse(product.getCategories().getId(), product.getCategories().getName());
        }
        return new ProductsResponse(product.getId(), product.getName(),
                product.getDescription(), product.getColor(), product.getGender(),
                product.getRating(), product.getPrice(), categoriesResponse);
    }


}
