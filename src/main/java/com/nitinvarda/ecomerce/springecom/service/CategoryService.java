package com.nitinvarda.ecomerce.springecom.service;

import com.nitinvarda.ecomerce.springecom.model.Category;
import com.nitinvarda.ecomerce.springecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long Id);

    Category updatedCategory(Category category,Long categoryId);

}
