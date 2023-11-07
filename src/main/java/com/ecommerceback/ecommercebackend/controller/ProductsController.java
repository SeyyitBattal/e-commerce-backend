package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsCategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.exceptions.CategoriesException;
import com.ecommerceback.ecommercebackend.exceptions.EcommerceValidation;
import com.ecommerceback.ecommercebackend.exceptions.ProductsException;
import com.ecommerceback.ecommercebackend.service.CategoriesService;
import com.ecommerceback.ecommercebackend.service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:9000")
@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;
    private CategoriesService categoriesService;

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
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

    @PostMapping("/{categoryId}")
    public ProductsResponse save(@RequestBody Products product, @PathVariable long categoryId) {
        Categories category = categoriesService.findCategoryById(categoryId);
        if (category != null) {
            category.getProductsList().add(product);
            product.setCategories(category);
            productsService.save(product);
        } else {
            throw new CategoriesException("Category not found", HttpStatus.BAD_REQUEST);
        }
        return new ProductsResponse(product.getId(), product.getName(), product.getDescription(),
                product.getColor(), product.getGender(), product.getRating(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public ProductsResponse remove(@PathVariable long id) {
        EcommerceValidation.isIdValid(id);
        return productsService.delete(id);
    }


}
