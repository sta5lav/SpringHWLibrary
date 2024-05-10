package com.example.springhwlibrary.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "book")
@Data
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToOne
    private Author author;

}
