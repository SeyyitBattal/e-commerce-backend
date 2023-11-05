package com.ecommerceback.ecommercebackend.service;

import com.ecommerceback.ecommercebackend.entity.Categories;
import com.ecommerceback.ecommercebackend.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories find(long id) {
        Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
        if (categoriesOptional.isPresent()) {
            return categoriesOptional.get();
        }
        return null;
    }

    @Override
    public Categories save(Categories category) {
        return categoriesRepository.save(category);
    }

    @Override
    public Categories delete(long id) {
        Categories category = find(id);
        if (category != null) {
            categoriesRepository.delete(category);
            return category;
        }
        return null;
    }
}
