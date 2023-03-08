package com.example.demo;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    


    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username should contain only alphanumeric characters")
    private String username;



    @Column(nullable = false)
    @Size(min = 1, max = 50, message = "should be within charector limit")
    private String password;


    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 1, max = 50, message = "should be within charector limit")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Country should contain only alphanumeric characters")
    private String country;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // getters and setters
}