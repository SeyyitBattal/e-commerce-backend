package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAll();

    Categories find(long id);

    Categories save(Categories categories);

    Categories delete(long id);
}
