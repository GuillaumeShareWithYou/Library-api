package com.guillaume.libraryapi.repository;

import com.guillaume.libraryapi.entity.Author;
import com.guillaume.libraryapi.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {


    @Query("SELECT a from Author a where a.lastName LIKE ?1 or a.firstName like ?1")
    public List<Author> searchByExactName(String search);


    @Query("select a from Author a where a.lastName Like ?1"+'%'+" or a.firstName like ?1"+'%')
    List<Author> searchByNameBeginWith(String name);
}
