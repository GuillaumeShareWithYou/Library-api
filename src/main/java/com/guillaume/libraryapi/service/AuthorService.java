package com.guillaume.libraryapi.service;

import com.guillaume.libraryapi.entity.Author;

import java.util.List;

public interface AuthorService {

    Author create(String firstname, String lastname);

    Author find(long id);

    List<Author> searchByName(String name);

    List<Author> searchByNameExact(String name);

    List<Author> searchByNameBeginWith(String name);

    List<Author> findAll();
}
