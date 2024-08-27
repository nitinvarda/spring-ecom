package com.nitinvarda.ecomerce.springecom.service;

import com.nitinvarda.ecomerce.springecom.model.Category;
import com.nitinvarda.ecomerce.springecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// explicity telling spring boot with this annotation to inject this class as a
// dependency wherever there is a need for CategoryService
@Service
public class CategoryServiceImpl implements CategoryService{
//    private List<Category> categories = new ArrayList<>();
    private Long nexId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nexId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource category not found"));

        categoryRepository.delete(category);
        return "Category with CategoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updatedCategory(Category category, Long categoryId) {


        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource category not found"));
        category.setCategoryId(categoryId);
       savedCategory = categoryRepository.save(category);
       return savedCategory;

    }

}
