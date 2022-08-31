package elcom.ex1.librarybooks.repository;

import elcom.ex1.librarybooks.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {}
