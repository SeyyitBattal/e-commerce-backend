package com.ecommerceback.ecommercebackend.service;


import com.ecommerceback.ecommercebackend.entity.Products;

import java.util.List;

public interface ProductsService {
    List<Products> findAll();
    Products find(long id);
    Products save(Products products);
    Products delete(long id);
}
