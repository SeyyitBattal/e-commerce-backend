package com.ecommerceback.ecommercebackend.repository;

import com.ecommerceback.ecommercebackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
