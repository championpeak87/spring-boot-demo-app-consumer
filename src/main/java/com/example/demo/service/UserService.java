package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.gateway.webclient.ProviderWebClient;
import com.example.demo.model.User;

@Service
public class UserService {

    @Autowired
    private ProviderWebClient providerWebClient;

    public User getUserById(Integer id) throws Exception {
        return providerWebClient.getUserById(id);
    }

    public void deleteUserById(Integer id) throws Exception {
        providerWebClient.deleteUserById(id);
    }

    public User createUser(User user) {
        return providerWebClient.createUser(user);
    }

    public List<User> getUsers() {
        return Arrays.asList(providerWebClient.getUsers());
    }
}
