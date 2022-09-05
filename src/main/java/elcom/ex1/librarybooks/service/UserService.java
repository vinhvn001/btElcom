package elcom.ex1.librarybooks.service;

import elcom.ex1.librarybooks.entity.User;

public interface UserService {

    Iterable<User> findAll();

    User findById(Long id);


    void save(User user);

    void remove(User user);
}
