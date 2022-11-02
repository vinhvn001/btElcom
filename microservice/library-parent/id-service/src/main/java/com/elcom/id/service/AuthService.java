package com.elcom.id.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails loadUserById(Long id);
}
