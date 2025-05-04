package com.example.apirest.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Category")
public class Category {
    String name;
}
