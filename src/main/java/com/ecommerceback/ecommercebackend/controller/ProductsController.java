package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.ProductsCategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.exceptions.EcommerceValidation;
import com.ecommerceback.ecommercebackend.service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:9000")
@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public List<ProductsResponse> findAll() {
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ProductsCategoriesResponse find(@PathVariable long id) {
        EcommerceValidation.isIdValid(id);
        return productsService.find(id);
    }

    @PostMapping("/")
    public ProductsResponse save(@RequestBody Products product) {
        return productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public ProductsResponse remove(@PathVariable long id) {
        EcommerceValidation.isIdValid(id);
        return productsService.delete(id);
    }


}
