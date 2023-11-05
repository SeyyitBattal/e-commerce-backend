package com.ecommerceback.ecommercebackend.repository;

import com.ecommerceback.ecommercebackend.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
