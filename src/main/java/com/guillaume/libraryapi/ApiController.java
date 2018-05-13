package com.guillaume.libraryapi;

import com.guillaume.libraryapi.entity.Author;
import com.guillaume.libraryapi.entity.Book;
import com.guillaume.libraryapi.entity.Category;
import com.guillaume.libraryapi.exception.ResourcesNotFoundException;
import com.guillaume.libraryapi.service.AuthorService;
import com.guillaume.libraryapi.service.BookService;
import com.guillaume.libraryapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    public AuthorService authorService;

    @Autowired
    public BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String hello() {
        return "hello from api";
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) Long categoryId,
                                  HttpServletResponse response) {

        return bookService.search(pageable, title, author, categoryId);

    }

    @GetMapping("/books/{id}")
    public Book searchBookById(@PathVariable String id, HttpServletResponse response) {

        try {
            Long bookId = Long.parseLong(id);

            Book book = bookService.getById(bookId);
            if (book == null) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }

            return book;
        } catch (NumberFormatException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            return null;
        }
    }

    @RequestMapping("/authors")
    public List<Author> search(@RequestParam(required = false) String name) {
        return name == null ? authorService.findAll() :
                authorService.searchByNameExact(name);
    }

    @PostMapping("/books/{id}")
    public Book changeBookResume(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {

        //   request.getParameterMap().forEach((k,v)-> System.out.println(k+"=>"+v));

        String resume = request.getParameter("resume");
        if (resume == null) resume = "";

        Book b = bookService.changeBookResume(id, resume);

        if (b == null) {
            response.setStatus(404);
        }
        return b;

    }

    @GetMapping("/category")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    /*@GetMapping("/category")
    public Page<Category> getCategories(@PageableDefault(page = 0, size = 3) Pageable p){


        return categoryService.getAll(p);
    }*/


    private void seed() {
      /*  List<Author> authors = new ArrayList<>();

        Author author1 = authorService.create("CS", "Lewis");
        Author author2 = authorService.create("Fabrice", "Midal");
        Author author3 = authorService.create("Romain", "Gary");
        Author author4 = authorService.create("Elfriede", "Jelinek");

        bookService.saveAll(Arrays.asList(
                new Book("les 4 amours", "...", author1),
                new Book("foutez vous la paix et commencez à vivre", "...", author2),
                new Book("La promesse de l'aube", "...", author3),
                new Book("La pianiste", "...", author4)
        ));*/

    }
}
