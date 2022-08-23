package elcom.ex1.LibraryBooks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BOOKSRepository extends CrudRepository<BOOKSRepository, Long> {}