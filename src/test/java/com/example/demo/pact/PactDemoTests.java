package com.example.demo.pact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.example.demo.gateway.webclient.ProviderWebClient;
import com.example.demo.model.User;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "spring-boot-demo-app-provider", port = "8081")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PactDemoTests {

    @Autowired
    private ProviderWebClient providerWebClient;

    @Pact(consumer = "spring-boot-demo-app-consumer", provider = "spring-boot-demo-app-provider")
    public RequestResponsePact pactGetUser(PactDslWithProvider builder) {

        PactDslJsonBody body = new PactDslJsonBody()
                .integerType("id", 18)
                .integerType("age", 18)
                .stringType("email", "hello@world.com")
                .stringType("username", "loremipsum");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("there is a user with a given id")
                .uponReceiving("by request /user/{id} for spring-boot-demo-app-provider")
                .path("/user/18")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(headers)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactGetUser")
    void testPactGetUser() {
        User user = new User();
        user.setAge(18);
        user.setId(18);
        user.setEmail("hello@world.com");
        user.setUsername("loremipsum");

        User createdUser = providerWebClient.getUserById(18);

        assertEquals(user, createdUser);
    }
}
