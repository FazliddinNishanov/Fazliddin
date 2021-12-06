package com.crud.demo.controller;

import com.crud.demo.entity.Category;
import com.crud.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/category/list")
    public ResponseEntity<?> getAll() {
        List<Category> categoryList = categoryService.allCategory();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getOneId(@RequestParam(name = "category_id") Integer id){
       Optional<Category> category = categoryService.selectById(id);
       return ResponseEntity.ok(category);
    }

}
