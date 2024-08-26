package com.nitinvarda.ecomerce.springecom.service;

import com.nitinvarda.ecomerce.springecom.model.Category;
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
    private List<Category> categories = new ArrayList<>();
    private Long nexId = 1L;
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nexId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        categories.remove(category);
        return "Category with CategoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updatedCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory  = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return  existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
        }

    }

}
