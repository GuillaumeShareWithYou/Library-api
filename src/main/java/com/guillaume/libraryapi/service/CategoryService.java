package com.guillaume.libraryapi.service;

import com.guillaume.libraryapi.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface CategoryService {
    List<Category> getAll();
}
