package com.guillaume.libraryapi.service.impl;

import com.guillaume.libraryapi.entity.Author;
import com.guillaume.libraryapi.entity.Book;
import com.guillaume.libraryapi.repository.BookRepository;
import com.guillaume.libraryapi.service.AuthorService;
import com.guillaume.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public AuthorService authorService;

    @Override
    public Book create(String bookTitle, String resume, Author author) {
        return bookRepository.save(new Book(bookTitle, resume, author));
    }

    @Override
    public Book create(String name, String resume) {
        return bookRepository.save(new Book(name, resume));
    }

    @Override
    public void saveAll(List<Book> books) {
        books.forEach(book -> {
            this.create(book.getTitle(), book.getResume(), book.getAuthor());
        });
    }

    @Override
    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Page<Book> getAll(Pageable p) {
        return bookRepository.findAll(p);
    }


    @Override
    public Page<Book> searchByAuthorName(String authorName, Pageable pageable) {

        String names[] = authorName.split(" ");
        if (names.length == 1) {

            return bookRepository.searchByAuthorName(names[0], pageable);
        } else if (names.length == 2) {
            return bookRepository.searchByAuthorName(names[0], names[1], pageable);
        } else {
            //more than two words in name
            return bookRepository.searchByAuthorName(names[0], names[1], names[2], pageable);
        }


  /*  return authorService.searchByName(authorName).stream()
            .map(Author::getBooks)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());*/

    }

    @Override
    public Page<Book> searchByTitleAndAuthorAndCategoryId(String title, String authorName, Long categoryId, Pageable pageable) {


        String names[] = authorName.split(" ");
        if (names.length == 1) {

            return categoryId == null ? bookRepository.searchByAuthorNameAndTitle(names[0], title, pageable)
                    : bookRepository.searchByAuthorNameAndTitleAndCategoryId(names[0], title, categoryId, pageable);

        } else if (names.length == 2) {
            return categoryId == null ? bookRepository.searchByAuthorNameAndTitle(names[0], names[1], title, pageable)
                    : bookRepository.searchByAuthorNameAndTitleAndCategoryId(names[0], names[1], title, categoryId, pageable);
        } else {
            //more than two words in name
            return categoryId == null ? bookRepository.searchByAuthorNameAndTitle(names[0], names[1], names[2], title, pageable)
                    : bookRepository.searchByAuthorNameAndTitleAndCategoryId(names[0], names[1], names[2], title, categoryId, pageable);

        }
       /* return authors.stream()
                .map(Author::getBooks)
                .flatMap(Collection::stream)
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());*/
    }


    /**
     * Utilisée par le controller et redirige vers la méthode adéquate
     *
     * @param title
     * @param fullName
     * @param categoryId
     * @return
     */
    @Override
    public Page<Book> search(Pageable pageable, String title, String fullName, Long categoryId) {


        if (title == null && fullName == null) {
            return categoryId == null ? getAll(pageable) : getAllByCategoryId(pageable, categoryId);
        } else if (title == null) {
            // No title, search by Author
            return categoryId == null ? searchByAuthorName(fullName, pageable) : searchByAuthorNameAndCategoryId(fullName, categoryId, pageable);

        } else if (fullName == null) {
            //No Author, search by title
            return categoryId == null ? searchByTitle(title, pageable) : searchByTitleAndCategoryId(title, categoryId, pageable);
        }
        //Author and title
        return searchByTitleAndAuthorAndCategoryId(title, fullName, categoryId, pageable);

    }

    private Page<Book> searchByAuthorNameAndCategoryId(String fullName, Long categoryId, Pageable pageable) {
        String names[] = fullName.split(" ");
        if (names.length == 1) {

            return bookRepository.searchByAuthorNameAndCategory(names[0], categoryId,  pageable);
        } else if (names.length == 2) {
            return bookRepository.searchByAuthorNameAndCategory(names[0], names[1],categoryId, pageable);
        } else {
            //more than two words in name
            return bookRepository.searchByAuthorNameAndCategory(names[0], names[1], names[2], categoryId, pageable);
        }

    }

    @Override
    public Page<Book> searchByTitleAndCategoryId(String title, Long categoryId, Pageable pageable) {

        return bookRepository.searchByTitleAndCategoryId(title, categoryId, pageable);
    }


    @Override
    public Page<Book> searchByTitle(String title, Pageable pageable) {
        return bookRepository.searchByTitle(title, pageable);
    }

    @Override
    public Book changeBookResume(Long id, String resume) {
        Book book = this.bookRepository.findById(id).orElse(null);
        if (book == null)
            return null;
        book.setResume(resume);
        return bookRepository.save(book);

    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> getAllByCategoryId(Pageable p, Long categoryId) {
        return bookRepository.findAllByCategoryId(categoryId, p);
    }
}
