package com.example.apirest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apirest.Model.User;
import com.example.apirest.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return  (List<User>) userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User post) {
        return userRepository.save(post);
    }

    public User updateUser(User post) {
        return userRepository.save(post);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
