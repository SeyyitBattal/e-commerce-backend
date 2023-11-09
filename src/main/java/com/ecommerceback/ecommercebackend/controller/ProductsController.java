package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.ProductsCategoriesResponse;
import com.ecommerceback.ecommercebackend.dto.ProductsResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.entity.Products;
import com.ecommerceback.ecommercebackend.exceptions.CategoriesException;
import com.ecommerceback.ecommercebackend.exceptions.EcommerceValidation;
import com.ecommerceback.ecommercebackend.service.CategoriesService;
import com.ecommerceback.ecommercebackend.service.ProductsService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// TODO: CROSS ORIGIN YERINE S20G4 TE SECURITY CONFIG DOSYASINDAKILER EKLENECEK
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;
    private CategoriesService categoriesService;
    private RestTemplateBuilder restTemplateBuilder;

    private static final String GET_ALL_PRODUCTS = "https://workintech-fe-ecommerce.onrender.com";

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService, RestTemplateBuilder restTemplateBuilder) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
        this.restTemplateBuilder = restTemplateBuilder;
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

    @PostMapping("/all")
    public String saveAll() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> productResponses =
                restTemplate.getForEntity(GET_ALL_PRODUCTS, JsonNode.class);

        List<Products> products = new ArrayList<>();
        for (JsonNode node : productResponses.getBody()) {
            Products product = new Products();
            product.setName(node.get("code").asText());
            product.setDescription(node.get("title").asText());
            product.setColor(product.getColor());
            product.setGender(product.getGender());
            product.setRating(node.get("rating").asDouble());
            product.setPrice(product.getPrice());
            products.add(product);
        }
        productsService.saveAll(products);
        return "Completed";
    }


    @DeleteMapping("/{id}")
    public ProductsResponse remove(@PathVariable long id) {
        EcommerceValidation.isIdValid(id);
        return productsService.delete(id);
    }


}
