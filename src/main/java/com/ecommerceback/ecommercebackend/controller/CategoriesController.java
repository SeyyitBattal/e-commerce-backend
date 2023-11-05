package com.ecommerceback.ecommercebackend.controller;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.service.CategoriesService;
import com.ecommerceback.ecommercebackend.util.CategoriesEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return CategoriesEntityConverter.findResults(categoriesService.findAll());
    }

    @GetMapping("/{id}")
    public CategoriesResponse find(@PathVariable long id) {
        return CategoriesEntityConverter.findResult(categoriesService.find(id));
    }

    @PostMapping("/")
    public CategoriesResponse save(@RequestBody Categories category) {
        return CategoriesEntityConverter.findResult(categoriesService.save(category));
    }

    @PutMapping("/{id}")
    public CategoriesResponse save(@RequestBody Categories category, @PathVariable long id) {
        Categories foundCategory = categoriesService.find(id);
        if (foundCategory != null) {
            category.setId(id);
            return CategoriesEntityConverter.findResult(categoriesService.save(category));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public CategoriesResponse remove(@PathVariable long id) {
        return CategoriesEntityConverter.findResult(categoriesService.delete(id));
    }


}
