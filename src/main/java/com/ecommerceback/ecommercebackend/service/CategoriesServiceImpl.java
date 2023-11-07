package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.dto.CategoriesResponse;
import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.exceptions.CategoriesException;
import com.ecommerceback.ecommercebackend.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<CategoriesResponse> findAll() {
        return categoriesRepository.findAll()
                .stream().map((category) -> new CategoriesResponse(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesResponse find(long id) {
        CategoriesResponse categoriesResponse = find(id);
        return categoriesResponse;
    }

    @Override
    public Categories findCategoryById(long id) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new CategoriesException("Category with given id not found: " + id, HttpStatus.NOT_FOUND);
        }
        return categoryOptional.get();
    }

    @Override
    public CategoriesResponse save(Categories category) {
        Categories savedCategory = categoriesRepository.save(category);
        return new CategoriesResponse(category.getId(), category.getName());
    }

    @Override
    public CategoriesResponse delete(long id) {
        CategoriesResponse categoriesResponse = find(id);
        categoriesRepository.deleteById(categoriesResponse.id());
        return new CategoriesResponse(categoriesResponse.id(), categoriesResponse.name());
    }
}
