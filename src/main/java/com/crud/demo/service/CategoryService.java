package com.crud.demo.service;

import com.crud.demo.entity.Category;
import com.crud.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> allCategory(){
        return categoryRepository.findAll();
    }

    public Optional<Category> selectById(Integer id){
        return categoryRepository.findById(id);
    }



}
