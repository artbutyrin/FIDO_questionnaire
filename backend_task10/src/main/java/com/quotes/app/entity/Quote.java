package com.quotes.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Author cannot be empty")
    @Size(max = 200, message = "Author cannot be longer than 200 characters")
    @Column(nullable = false, length = 200)
    private String author;

    @NotBlank(message = "Text cannot be empty")
    @Size(max = 1000, message = "Text cannot be longer than 1000 characters")
    @Column(nullable = false, length = 1000)
    private String text;

    // Constructors
    public Quote() {}

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}