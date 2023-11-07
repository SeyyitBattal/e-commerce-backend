package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:9000")
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/")
    public List<CategoriesResponse> findAll() {
        return categoriesService.findAll();
    }

    @GetMapping("/{id}")
    public CategoriesResponse find(@PathVariable long id) {
        return categoriesService.find(id);
    }

    @PostMapping("/")
    public CategoriesResponse save(@RequestBody Categories category) {
        return categoriesService.save(category);
    }

    @DeleteMapping("/{id}")
    public CategoriesResponse remove(@PathVariable long id) {
        return categoriesService.delete(id);
    }


}
