package elcom.ex1.librarybooks.repository;

import elcom.ex1.librarybooks.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {}
