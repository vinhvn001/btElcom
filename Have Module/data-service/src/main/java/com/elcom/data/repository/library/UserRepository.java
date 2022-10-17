package com.elcom.data.repository.library;


import com.elcom.data.model.library.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

   // User findById(Long id);


}
