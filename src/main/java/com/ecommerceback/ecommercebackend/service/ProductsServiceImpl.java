package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsCategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.exceptions.ProductsException;
import com.ecommerceback.ecommercebackend.repository.CategoriesRepository;
import com.ecommerceback.ecommercebackend.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductsRepository productsRepository;
    private CategoriesRepository categoriesRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository, CategoriesRepository categoriesRepository) {
        this.productsRepository = productsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<ProductsResponse> findAll() {
        return productsRepository.findAll()
                .stream().map((product) -> new ProductsResponse(product.getId(), product.getName(),
                        product.getDescription(), product.getColor(), product.getGender(),
                        product.getRating(), product.getPrice())).collect(Collectors.toList());
    }

    @Override
    public ProductsCategoriesResponse find(long id) {
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isPresent()) {
            Products product = productsOptional.get();
            Optional<Categories> optionalCategory = categoriesRepository.findById(product.getId());
            if (optionalCategory.isPresent()) {
                Categories category = optionalCategory.get();
                return new ProductsCategoriesResponse(product.getId(), product.getName(),
                        product.getDescription(), product.getColor(), product.getGender(),
                        product.getRating(), product.getPrice(),
                        new CategoriesResponse(category.getId(), category.getName()));
            }
            return new ProductsCategoriesResponse(product.getId(), product.getName(),
                    product.getDescription(), product.getColor(), product.getGender(),
                    product.getRating(), product.getPrice(), null);
        }
        throw new ProductsException("Product with given id is not exist:" + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ProductsResponse save(Products product) {
        Products savedProduct = productsRepository.save(product);
        return new ProductsResponse(savedProduct.getId(), savedProduct.getName(),
                savedProduct.getDescription(), savedProduct.getColor(), savedProduct.getGender(),
                savedProduct.getRating(), savedProduct.getPrice());
    }

    @Override
    public ProductsResponse delete(long id) {
        ProductsCategoriesResponse productsCategoriesResponse = find(id);
        productsRepository.deleteById(productsCategoriesResponse.id());
        return new ProductsResponse(productsCategoriesResponse.id(),
                productsCategoriesResponse.name(), productsCategoriesResponse.description(),
                productsCategoriesResponse.color(), productsCategoriesResponse.gender(),
                productsCategoriesResponse.rating(), productsCategoriesResponse.price());
    }
}
