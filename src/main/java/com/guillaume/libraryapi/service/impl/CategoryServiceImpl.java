package com.guillaume.libraryapi.service.impl;

import com.guillaume.libraryapi.entity.Category;
import com.guillaume.libraryapi.repository.CategoryRepository;
import com.guillaume.libraryapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }

}
