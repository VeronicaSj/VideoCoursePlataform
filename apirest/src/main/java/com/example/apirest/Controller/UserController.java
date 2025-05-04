package com.example.apirest.Controller;

import java.net.URI;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.apirest.Model.User;
import com.example.apirest.Service.Endpoints;
import com.example.apirest.Service.UserService;

@RestController
@RequestMapping(Endpoints.EP_USER_ROOT)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(Endpoints.EP_USER_GET_ID)
    public ResponseEntity<Model> getById(@PathVariable String name, Model model, Principal principal) {
        ResponseEntity<Model> res = ResponseEntity.notFound().build();
        User foundUser = userService.getUserById(name);
        if (foundUser != null) {
            model.addAttribute("user", foundUser);
            res= ResponseEntity.ok(model);
        }
        return res;
    }

    @GetMapping(Endpoints.EP_USER_GET_ALL)
    public ResponseEntity<Model> getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return ResponseEntity.ok(model);
    }

    /*curl -X POST http://localhost:8081/user -H "Content-Type: application/json" -d "{\"name\":\"John Doe\", \"mail\":\"john.doe@example.com\"}" */
    @PostMapping(path=Endpoints.EP_USER_POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
        User savedUser = userService.createUser(user);
        URI locationOfNewUser = ucb
                .path(Endpoints.EP_USER_GET_ID)
                .buildAndExpand(savedUser.getName())
                .toUri();
        System.out.println(locationOfNewUser);
        return ResponseEntity.created(locationOfNewUser).build();
    }

    /*curl -X PUT http://localhost:8081/user/1 -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"John Doe\",\"mail\":\"prueba2\",\"date\":null,\"img\":null,\"type\":null,\"lastLog\":null}" */
    @PutMapping(path=Endpoints.EP_USER_PUT)
    private ResponseEntity<Void> putUser(@PathVariable String name, @RequestBody User userUpdate) {
        userService.createOrUpdateUser(userUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path=Endpoints.EP_USER_DELETE)
    private ResponseEntity<Void> deleteUser(@PathVariable String name) {
        userService.deleteUser(name);
        return ResponseEntity.noContent().build();
    }
}
