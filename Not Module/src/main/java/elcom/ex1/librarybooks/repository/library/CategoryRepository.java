package elcom.ex1.librarybooks.repository.library;

import elcom.ex1.librarybooks.entity.library.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
