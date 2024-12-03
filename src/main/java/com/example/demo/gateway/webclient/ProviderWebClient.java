package com.example.demo.gateway.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Web client to execute requests to the IPAddressPool microservice
 */
@Component
public class ProviderWebClient {
    private static final String BASEURL = "http://localhost:8081";

    @Autowired
    private ObjectMapper objectMapper;

    public User createUser(User user) {
        WebClient webClient = WebClient.create(BASEURL);
        User createdUser = webClient.post().uri("/user")
                .header("Content-Type", "application/json").retrieve()
                .bodyToMono(User.class).block();

        return createdUser;
    }

    public User getUserById(Integer id) {
        WebClient webClient = WebClient.create(BASEURL);
        User user = webClient.get().uri("/user/" + id).retrieve().bodyToMono(User.class).block();

        return user;
    }

    public User[] getUsers() {
        WebClient webClient = WebClient.create(BASEURL);
        User[] users = webClient.get().uri("/users").retrieve().bodyToMono(User[].class).block();

        return users;
    }

    public void deleteUserById(Integer id) {
        WebClient webClient = WebClient.create(BASEURL);
        webClient.delete().uri("/user/" + id).retrieve().bodyToMono(Void.class).block();
    }
}
