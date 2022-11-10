package com.elcom.library.repository;

import com.elcom.library.model.Author;
import com.elcom.library.model.Book;
import com.elcom.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(" select b.bookName, b.bookAmount from Book b where b.authorId = ?1" )
    List<Object[]> findBookAmountByAuthorId(Author id);

    @Query("select b.bookName, b.bookAmount from Book b where b.firstLetter= ?1" )
    List<Object[]> findBookAmountByFirstLetter(String firstLetter);

    @Query("select b.bookName, b.bookAmount from Book b where b.categoryId= ?1" )
    List<Object[]> findBookAmountByCategoryId(Category id);

    @Query("select b.bookName, b.bookAmount from Book b")
    List<Object[]> findBookList();

    Book findByBookName (String bookName);
}