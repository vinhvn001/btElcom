package com.elcom.backend.controller;


import com.elcom.backend.auth.CustomUserDetails;
import com.elcom.backend.auth.LoginRequest;
import com.elcom.backend.auth.LoginResponse;
import com.elcom.backend.auth.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.elcom.backend.validation.UserValidation;

import javax.validation.Valid;
import javax.validation.ValidationException;


@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(){}

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        new UserValidation().validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
        // Check request, if not exception then info is true
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }catch (AuthenticationException e){
            LOGGER.error(e.toString());
            throw new ValidationException("Username hoặc password bị sai");
        }
        //Set authentication into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return jwt
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }


}
