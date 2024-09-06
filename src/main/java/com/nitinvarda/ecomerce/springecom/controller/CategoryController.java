package com.nitinvarda.ecomerce.springecom.controller;


import com.nitinvarda.ecomerce.springecom.model.Category;
import com.nitinvarda.ecomerce.springecom.payload.CategoryResponse;
import com.nitinvarda.ecomerce.springecom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Created Successfully",HttpStatus.OK);

    }

    @DeleteMapping("/api/public/categories/{Id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long Id){
        String status =  categoryService.deleteCategory(Id);
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,@PathVariable Long categoryId) {
        Category savedCategory = categoryService.updatedCategory(category, categoryId);
        return new ResponseEntity<>("Category with categoryId: " + categoryId + " was Updated", HttpStatus.OK);
    }

}
