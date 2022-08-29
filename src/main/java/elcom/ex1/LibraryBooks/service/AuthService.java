package elcom.ex1.LibraryBooks.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails loadUserById(Long id);
}
