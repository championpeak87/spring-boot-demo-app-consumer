package com.example.demo.dataapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.User;

public interface IUserApi {
    @GetMapping(value = "/user/{id}", produces = "application/json")
    ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer id) throws Exception;

    @GetMapping(value = "/user", produces = "application/json")
    ResponseEntity<List<User>> getUsers() throws Exception;

    @PostMapping(value = "/user", consumes = "application/json")
    ResponseEntity<Void> createUser(@RequestBody User user) throws Exception;

    @DeleteMapping("/user/{id}")
    ResponseEntity<Void> deleteUserById(@PathVariable(name = "id") Integer id) throws Exception;
}
