package elcom.ex1.LibraryBooks.repository;

import elcom.ex1.LibraryBooks.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
