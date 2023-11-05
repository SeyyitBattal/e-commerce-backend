package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.service.CategoriesService;
import com.ecommerceback.ecommercebackend.service.ProductsService;
import com.ecommerceback.ecommercebackend.util.ProductsEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ProductsEntityConverter.findResults(productsService.findAll());
    }

    @GetMapping("/{id}")
    public ProductsResponse find(@PathVariable long id) {
        return ProductsEntityConverter.findResult(productsService.find(id));
    }

    @PostMapping("/")
    public ProductsResponse save(@RequestBody Products product) {
        return ProductsEntityConverter.findResult(productsService.save(product));
    }

    @PutMapping("/{id}")
    public ProductsResponse save(@RequestBody Products product, @PathVariable long id) {
        Products foundProduct = productsService.find(id);
        if (foundProduct != null) {
            product.setId(id);
            return ProductsEntityConverter.findResult(productsService.save(product));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ProductsResponse remove(@PathVariable long id) {
        return ProductsEntityConverter.findResult(productsService.delete(id));
    }


}
