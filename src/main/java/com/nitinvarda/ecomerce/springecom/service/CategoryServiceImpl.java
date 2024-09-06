package com.nitinvarda.ecomerce.springecom.service;

import com.nitinvarda.ecomerce.springecom.exceptions.APIException;
import com.nitinvarda.ecomerce.springecom.exceptions.ResourceNotFoundException;
import com.nitinvarda.ecomerce.springecom.model.Category;
import com.nitinvarda.ecomerce.springecom.payload.CategoryDTO;
import com.nitinvarda.ecomerce.springecom.payload.CategoryResponse;
import com.nitinvarda.ecomerce.springecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


// explicity telling spring boot with this annotation to inject this class as a
// dependency wherever there is a need for CategoryService
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new APIException("No category created till now");
        }
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse  = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }


    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory !=null){
            throw new APIException("Category with name " + category.getCategoryName() +" already exits !!!" );
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepository.delete(category);
        return "Category with CategoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updatedCategory(Category category, Long categoryId) {


        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryId(categoryId);
       savedCategory = categoryRepository.save(category);
       return savedCategory;

    }

}
