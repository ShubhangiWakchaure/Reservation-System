package com.mahayatra.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String username;
    private String password;
    private LocalDate date_of_birth;
    private String role="USER";
    private LocalDateTime created_at=LocalDateTime.now();

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDob() {
        return date_of_birth;
    }

    public void setDob(LocalDate dob) {
        this.date_of_birth = dob;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }
}


