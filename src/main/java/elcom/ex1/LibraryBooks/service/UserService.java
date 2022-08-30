package elcom.ex1.LibraryBooks.service;

import elcom.ex1.LibraryBooks.entity.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Long id);


    void save(User user);

    void remove(User user);
}
