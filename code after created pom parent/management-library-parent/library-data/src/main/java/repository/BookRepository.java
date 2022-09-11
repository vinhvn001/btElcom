package repository;

import entity.Author;
import entity.Book;
import entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(" select sum(b.bookAmount) from Book b where b.authorId = ?1" )
    Integer findBookAmountByAuthorId(Author id);

    @Query("select sum(b.bookAmount)from Book b where b.firstLetter= ?1" )
    Integer findBookAmountByFirstLetter(String firstLetter);

    @Query("select sum(b.bookAmount)from Book b where b.categoryId= ?1" )
    Integer findBookAmountByCategoryId(Category id);
}