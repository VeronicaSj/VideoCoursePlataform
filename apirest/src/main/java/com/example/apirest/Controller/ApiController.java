package com.example.apirest.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.apirest.Model.User;

@Controller
public class ApiController {
    @GetMapping("/api")
    public ResponseEntity getUserById(Model model) {
        return ResponseEntity.ok(new User());
    }

    
}
