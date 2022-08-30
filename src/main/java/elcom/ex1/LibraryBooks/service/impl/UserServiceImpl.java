package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.User;
import elcom.ex1.LibraryBooks.repository.UserCustomizeRepository;
import elcom.ex1.LibraryBooks.repository.UserRepository;
import elcom.ex1.LibraryBooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCustomizeRepository userCustomizeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserCustomizeRepository userCustomizeRepository) {
        this.userRepository = userRepository;
        this.userCustomizeRepository = userCustomizeRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
}
