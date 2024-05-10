package com.example.springhwlibrary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "author")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {

    }
}
