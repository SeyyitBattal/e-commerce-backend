package com.ecommerceback.ecommercebackend.service;


import com.ecommerceback.ecommercebackend.dto.ProductsCategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Products;

import java.util.List;

public interface ProductsService {
    List<ProductsResponse> findAll();

    ProductsCategoriesResponse find(long id);

    ProductsResponse save(Products products);

    List<Products> saveAll(List<Products> products);

    ProductsResponse delete(long id);
}
