package com.elcom.backend.controller;



import com.elcom.backend.utils.JSONConverter;
import com.elcom.backend.utils.StringUtil;
import com.elcom.backend.validation.UserValidation;
import com.elcom.data.model.library.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.elcom.data.service.UserService;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER =  LoggerFactory.getLogger(UserController.class);
    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        List<User> userList = userService.findAll();
        if (userList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getById(@PathVariable Long id) {
        LOGGER.info("id[{}]", id);

        if (id == null || id.equals(0L))
            throw new ValidationException("id không được để trống");

        User user = userService.findById(id);

        if ( user == null)
            throw new ValidationException("id không hợp lệ");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder builder) {

        LOGGER.info("{}", JSONConverter.toJSON(user));

        new UserValidation().validateUpsertUser(user, "INSERT");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        LOGGER.info("id[{}] - {}", id, JSONConverter.toJSON(user));

        if (id == null || id.equals(0L))
            throw new ValidationException("id không được để trống");

        new UserValidation().validateUpsertUser(user, "UPDATE");

        User currentUser = userService.findById(id);

        if ( currentUser==null )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        //
        currentUser.setFullName(user.getFullName());
        if( !StringUtil.isNullOrEmpty(user.getPassword()) )
            currentUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userService.save(currentUser);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        LOGGER.info("delete() --> id[{}]", id);

        if (id == null || id.equals(0L))
            throw new ValidationException("id không được để trống");

        User user = userService.findById(id);

        if ( user==null )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.remove(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
