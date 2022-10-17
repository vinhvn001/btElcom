package elcom.ex1.librarybooks.repository.log;

import elcom.ex1.librarybooks.entity.log.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {
    SchoolClass findByName(String name);
}
