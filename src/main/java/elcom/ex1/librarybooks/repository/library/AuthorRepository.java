package elcom.ex1.librarybooks.repository.library;

import elcom.ex1.librarybooks.entity.library.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {}
