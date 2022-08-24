package elcom.ex1.LibraryBooks.repository;

import elcom.ex1.LibraryBooks.entity.FirstLetter;
import org.springframework.data.repository.CrudRepository;

public interface FirstLetterRepository extends CrudRepository<FirstLetter, Long> {
}
