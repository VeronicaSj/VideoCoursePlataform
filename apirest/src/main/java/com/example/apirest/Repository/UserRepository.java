package com.example.apirest.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apirest.Model.User;

@Repository()
public interface UserRepository extends CrudRepository<User, String>{

}
