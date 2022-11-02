package elcom.ex1.librarybooks.repository.library;

import elcom.ex1.librarybooks.entity.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {



}
