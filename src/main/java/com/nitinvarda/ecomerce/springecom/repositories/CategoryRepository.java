package com.nitinvarda.ecomerce.springecom.repositories;

import com.nitinvarda.ecomerce.springecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}
