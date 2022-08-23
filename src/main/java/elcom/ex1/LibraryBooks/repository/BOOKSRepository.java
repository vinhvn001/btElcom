package elcom.ex1.LibraryBooks.repository;

import elcom.ex1.LibraryBooks.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends CrudRepository<Books, Long> {}