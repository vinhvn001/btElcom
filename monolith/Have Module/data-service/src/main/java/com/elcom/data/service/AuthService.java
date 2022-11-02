package com.elcom.data.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails loadUserById(Long id);
}
