package com.elcom.data.repository.library;


import com.elcom.data.model.library.Author;
import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(" select b.bookName, b.bookAmount from Book b where b.authorId = ?1" )
    List<Object[]> findBookAmountByAuthorId(Author id);

    @Query("select b.bookName, b.bookAmount from Book b where b.firstLetter= ?1" )
    List<Object[]> findBookAmountByFirstLetter(String firstLetter);

    @Query("select sum(b.bookAmount)from Book b where b.categoryId= ?1" )
    Integer findBookAmountByCategoryId(Category id);

    @Query("select b.bookName, b.bookAmount from Book b")
    List<Object[]> findBookList();
}