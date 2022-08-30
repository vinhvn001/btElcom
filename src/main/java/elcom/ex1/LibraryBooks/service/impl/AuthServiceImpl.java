package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.auth.CustomUserDetails;
import elcom.ex1.LibraryBooks.entity.User;
import elcom.ex1.LibraryBooks.repository.UserCustomizeRepository;
import elcom.ex1.LibraryBooks.repository.UserRepository;
import elcom.ex1.LibraryBooks.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null ){
        throw new UsernameNotFoundException("User not found with id : " +id);
        }
        return new CustomUserDetails(user.get());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " +user);
        }
        return new CustomUserDetails(user);
    }
}
