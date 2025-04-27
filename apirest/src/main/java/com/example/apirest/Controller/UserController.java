package com.example.apirest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apirest.Model.User;
import com.example.apirest.Service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Model> getUserById(@PathVariable Integer id, Model model) {
        
        model.addAttribute("users", userService.getAllUsers());
        return ResponseEntity.ok(model);
    }

    @PostMapping("/userh")
    public void createUser(Model model) {
        userService.createUser(new User());
    }
}
