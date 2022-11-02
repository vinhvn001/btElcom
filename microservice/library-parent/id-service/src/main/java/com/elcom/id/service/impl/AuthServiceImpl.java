package com.elcom.id.service.impl;



import com.elcom.id.auth.CustomUserDetails;
import com.elcom.id.model.User;
import com.elcom.id.service.AuthService;
import com.elcom.id.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userService.findById(id);
        if(user == null ){
        throw new UsernameNotFoundException("User not found with id : " +id);
        }
        return new CustomUserDetails(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " +user);
        }
        return new CustomUserDetails(user);
    }
}
