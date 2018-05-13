package com.guillaume.libraryapi.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    @Column(columnDefinition = "TEXT")
    private String resume;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @ManyToOne
    private Author author;

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @JsonProperty
    private String imagePath;


    public Book() {
    }

    public Book(String name, String resume) {
        this.title = name;
        this.resume = resume;
    }

    public Book(String name, String resume, Author author) {
        this.title = name;
        this.resume = resume;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean equals(Book obj) {
        return this.getId().equals(obj.getId());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
