package com.guillaume.libraryapi.service.impl;

import com.guillaume.libraryapi.entity.Author;
import com.guillaume.libraryapi.repository.AuthorRepository;
import com.guillaume.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    public AuthorRepository authorRepository;

    @Override
    public Author create(String firstname, String lastname) {
        Author author = new Author(firstname, lastname);
        return authorRepository.save(author);
    }

    @Override
    public Author find(long id) {

      return authorRepository.findById(id).orElse(null);

    }

    @Override
    public List<Author> searchByName(String name) {

        System.out.println("recherche d'auteurs pas le nom "+name);
        String in[] = name.split(" ");

        List<Author> authors = new ArrayList<>();
        if(in.length == 1){
            authors =  this.searchByNameBeginWith(in[0]);
        }else{
            authors = this.searchByNameExact(in[0]);
            List<Author> authorList = this.searchByNameBeginWith(in[1]);
            System.out.println("authorList : ");
            authorList.forEach(System.out::println);
            authors = authors.stream().filter(authorList::contains).collect(Collectors.toList());
        }
        return authors;
    }

    @Override
    public List<Author> searchByNameExact(String name) {

        return authorRepository.searchByExactName(name);
    }

    @Override
    public List<Author> searchByNameBeginWith(String name) {
        //TODO
        return authorRepository.searchByNameBeginWith(name);
    }



    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

}
