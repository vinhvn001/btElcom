package elcom.ex1.librarybooks.repository.library;

import elcom.ex1.librarybooks.entity.library.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    List<Borrow> findByBorrowerId(Long borrowerId);

}
