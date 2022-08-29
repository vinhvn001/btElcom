package elcom.ex1.LibraryBooks.repository;

import elcom.ex1.LibraryBooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
