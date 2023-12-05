package com.example.cosmetic.service.main;

import com.example.cosmetic.model.product.Category;
import com.example.cosmetic.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService  implements ICategoryService{
    @Autowired
    ICategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
