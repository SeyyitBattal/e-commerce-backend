package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<CategoriesResponse> findAll();

    CategoriesResponse find(long id);

    CategoriesResponse save(Categories categories);

    CategoriesResponse delete(long id);
}
