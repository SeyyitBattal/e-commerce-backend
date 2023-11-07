package com.ecommerceback.ecommercebackend.dto;

import com.ecommerceback.ecommercebackend.entity.Color;
import com.ecommerceback.ecommercebackend.entity.Gender;

public record ProductsResponse(long id, String name, String description,
                               Color color, Gender gender,
                               double rating, double price) {
}
