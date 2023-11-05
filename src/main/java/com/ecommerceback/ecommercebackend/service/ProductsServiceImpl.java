package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products find(long id) {
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isPresent()) {
            return productsOptional.get();
        }
        return null;
    }

    @Override
    public Products save(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public Products delete(long id) {
        Products product = find(id);
        if (product != null) {
            productsRepository.delete(product);
            return product;
        }
        return null;
    }
}
