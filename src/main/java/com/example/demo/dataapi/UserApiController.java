package com.example.demo.dataapi;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserApiController implements IUserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> createUser(User user) throws Exception {
        User createdUser = userService.createUser(user);

        // create href location
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Integer id) throws Exception {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<User> getUserById(Integer id) throws Exception {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() throws Exception {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }
}
