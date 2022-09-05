package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.User;
import elcom.ex1.librarybooks.repository.UserCustomizeRepository;
import elcom.ex1.librarybooks.repository.UserRepository;
import elcom.ex1.librarybooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User findById(Long id) {
        return userCustomizeRepository.findById(id);
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
