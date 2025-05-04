package com.example.apirest.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Course")
public class Course {

    public enum Coin {
        EURO, DOLLAR;
    }

    Integer id;
    String name;
    String description;
    Integer price;
    Coin coin;
    Boolean punctuation;
    
    Date creationDate;
    Date lastUpdate;
    Date deletionDate;
    Boolean isPublic;
    Boolean isHolded;

    //TODO atributes
    String img;

    //relationship atributes
    Category category;
    Profesor profesor;
    List<User> alumnList;
}
