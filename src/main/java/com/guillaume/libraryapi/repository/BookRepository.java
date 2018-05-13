package com.guillaume.libraryapi.repository;

import com.guillaume.libraryapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Page<Book> findAllByCategoryId(Long categoryId, Pageable pageable);

    @Query("select b from Book b where b.title LIKE %?1%")
    Page<Book> searchByTitle(String title, Pageable pageable);

    @Query("select b from Book b where b.author.id = ?1 and b.title LIKE ?2"+'%')
    List<Book> getAllByAuthorIdAndTitle(Long id, String name, Pageable pageable);


    @Query("SELECT b from Book b inner join b.author a where a.firstName like ?1% or a.lastName like ?1%")
    Page<Book> searchByAuthorName(String n1, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2% or a.lastName like ?2%)")
    Page<Book> searchByAuthorName(String n1, String n2, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2 or a.lastName like ?2)" +
            " and (a.firstName like ?3% or a.lastName like ?3%)")
    Page<Book> searchByAuthorName(String n1, String n2, String n3, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where (a.firstName like ?1% or a.lastName like ?1%) " +
            " and b.title like %?2%")
    Page<Book> searchByAuthorNameAndTitle(String n1, String title, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2% or a.lastName like ?2%) " +
            " and b.title like %?3%")
    Page<Book> searchByAuthorNameAndTitle(String n1, String n2, String title, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2 or a.lastName like ?2)" +
            " and (a.firstName like ?3% or a.lastName like ?3%) " +
            " and b.title like %?4%")
    Page<Book> searchByAuthorNameAndTitle( String n1, String n2, String n3, String title, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?3 and (a.firstName like ?1% or a.lastName like ?1%) " +
            " and b.title like %?2%")
    Page<Book> searchByAuthorNameAndTitleAndCategoryId(String name, String title, Long categoryId, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?4 and (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2% or a.lastName like ?2%) " +
            " and b.title like %?3%")
    Page<Book> searchByAuthorNameAndTitleAndCategoryId(String n1, String n2, String title, Long categoryId, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?5 and (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2 or a.lastName like ?2)" +
            " and (a.firstName like ?3% or a.lastName like ?3%) " +
            " and b.title like %?4%")
    Page<Book> searchByAuthorNameAndTitleAndCategoryId(String n1, String n2, String n3, String title, Long categoryId, Pageable pageable);

    @Query("select b from Book b where b.category.id = ?2 and b.title like %?1%")
    Page<Book> searchByTitleAndCategoryId(String title, Long categoryId, Pageable pageable);


    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?2 and (a.firstName like ?1% or a.lastName like ?1%)")
    Page<Book> searchByAuthorNameAndCategory(String name, Long categoryId, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?3 and (a.firstName like ?1 or a.lastName like ?1) and (a.firstName like ?2% or a.lastName like ?2%)")
    Page<Book> searchByAuthorNameAndCategory(String name, String name1,  Long categoryId, Pageable pageable);

    @Query("SELECT b from Book b inner join b.author a where b.category.id = ?4 " +
            " and (a.firstName like ?1 or a.lastName like ?1)" +
            " and (a.firstName like ?2 or a.lastName like ?2)" +
            " and (a.firstName like ?3% or a.lastName like ?3%)")
    Page<Book> searchByAuthorNameAndCategory(String name, String name1, String name2, Long categoryId, Pageable pageable);
}
