package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.auth.CustomUserDetails;
import elcom.ex1.LibraryBooks.entity.User;
import elcom.ex1.LibraryBooks.repository.UserCustomizeRepository;
import elcom.ex1.LibraryBooks.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserCustomizeRepository userRepository;
    @Override
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id);
        if(user == null ){
        throw new UsernameNotFoundException("User not found with id : " +id);
        }
        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " +user);
        }
        return new CustomUserDetails(user);
    }
}
