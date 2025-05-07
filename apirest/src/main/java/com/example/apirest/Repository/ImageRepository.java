package com.example.apirest.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apirest.Model.Image;

@Repository()
public interface ImageRepository extends CrudRepository<Image, Integer> {
}