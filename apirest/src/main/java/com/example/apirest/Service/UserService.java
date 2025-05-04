package com.example.apirest.Service;

import java.util.List;
import java.util.Optional;

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

    public User getUserById(String name) {
        User res= null;
        Optional<User> opt = userRepository.findById(name);

        if(!opt.equals(Optional.empty())){
            res = opt.get();
        }
        return res;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User createOrUpdateUser(User user) {
        User res= null;
        if(!userRepository.existsById(user.getName())){
            res =userRepository.save(user);
        }else{
            
            res =userRepository.save(user);
        }
        return res;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
