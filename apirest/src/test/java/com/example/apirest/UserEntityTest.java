package com.example.apirest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.example.apirest.Model.User;
import com.example.apirest.Repository.UserRepository;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserEntityTest {
    @Autowired
    TestRestTemplate restTemplate;
    
    @Autowired
    UserRepository repo;

    @Test
    void shouldReturnAllUsers() {
        repo.save(new User("user1"));
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123")
        .getForEntity("/user/get/all", String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnNoUserWhenUnautorized() {
        repo.save(new User("user1"));
        ResponseEntity<String> response = restTemplate.withBasicAuth("random", "abc123").getForEntity("/user/get/all", String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void shouldReturnNoUserWhenBadPasword() {
        repo.save(new User("user1"));
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "random").getForEntity("/user/get/all", String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void shouldReturnNoUserWhenNotTeacherRole() {
        repo.save(new User("user1"));
        ResponseEntity<String> response = restTemplate.withBasicAuth("hank-REG", "qrs456").getForEntity("/user/get/all", String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void shouldCreateANewUser() {
        User newUser = new User("user1");
        ResponseEntity createResponse = restTemplate.withBasicAuth("sarah1", "abc123").postForEntity("/user/post", newUser, User.class);
        System.out.println(createResponse.toString()); 
        System.out.println(createResponse.getBody());
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldReturnASavedUser() {
        User newUser = new User("user1");
        repo.save(newUser);
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123").getForEntity("/user/get/"+newUser.getName(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    

    @Test
    void shouldNotReturnAUserWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("sarah1", "abc123").getForEntity("/user/get/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldUpdateAnExistingUser() {
        User userUpdate = new User("user1", "newmail", null,null,null,null);
        HttpEntity<User> request = new HttpEntity<>(userUpdate);
        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/user/put/"+userUpdate.getName(), HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity("/user/get/"+userUpdate.getName(), String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        System.out.println(   documentContext.jsonString());
        
        String name = documentContext.read("$.user.name");
        String mail = documentContext.read("$.user.mail");
        assertThat(name).isEqualTo("user1");
        assertThat(mail).isEqualTo("newmail");
    }


    @Test
    @DirtiesContext
    void shouldDeleteExistingdUser() {
        User userUpdate = new User("user1", "newmail", null,null,null,null);
        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/user/delete/"+userUpdate.getName(), HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity("/user/get/"+userUpdate.getName(), String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}