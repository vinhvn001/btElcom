package elcom.ex1.LibraryBooks.repository;

import elcom.ex1.LibraryBooks.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value=" select *from Book a where a.author_id =?1" ,nativeQuery = true)
    Book findBookAmountByAuthorId(Long ID);

    @Query(value=" select *from Book a where a.first_letter =?1" ,nativeQuery = true)
    Book findBookAmountByFirstLetter(String firstLetter);

    @Query(value=" select *from Book a where a.category_id =?1" ,nativeQuery = true)
    Book findBookAmountByCategoryId(Long ID);
}