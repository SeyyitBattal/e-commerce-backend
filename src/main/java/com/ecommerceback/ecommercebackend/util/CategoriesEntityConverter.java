package com.ecommerceback.ecommercebackend.util;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesEntityConverter {

    public static List<CategoriesResponse> findResults(List<Categories> categories) {
        List<CategoriesResponse> categoriesResponses = new ArrayList<>();
        for (Categories category : categories) {
            categoriesResponses.add(new CategoriesResponse(category.getId(), category.getName()));
        }
        return categoriesResponses;
    }

    public static CategoriesResponse findResult(Categories category) {
        return new CategoriesResponse(category.getId(), category.getName());
    }

}
