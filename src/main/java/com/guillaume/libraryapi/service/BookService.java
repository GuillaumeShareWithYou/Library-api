package com.guillaume.libraryapi.service;

import com.guillaume.libraryapi.entity.Author;
import com.guillaume.libraryapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book create(String name, String resume, Author author);

    Book create(String name, String resume);

    void saveAll(List<Book> books);

    List<Book> getAll();

    Page<Book> getAll(Pageable p);

    Page<Book> getAllByCategoryId(Pageable p, Long categoryId);

    Page<Book> searchByTitleAndCategoryId(String title, Long categoryId, Pageable pageable);

    Page<Book> searchByAuthorName(String authorName, Pageable pageable);

    Page<Book> searchByTitleAndAuthorAndCategoryId(String title, String authorName, Long categoryId, Pageable pageable);

    Page<Book> search(Pageable pageable, String title, String author, Long categoryId);

    Page<Book> searchByTitle(String title, Pageable pageable);

    Book changeBookResume(Long id, String resume);

    Book getById(Long id);
}
