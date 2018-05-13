package com.guillaume.libraryapi.repository;

import com.guillaume.libraryapi.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
