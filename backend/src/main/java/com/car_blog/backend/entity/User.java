package com.car_blog.backend.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@NoArgsConstructor 
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Setter 
    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Instant createdAt;
    
    private Instant updatedAt;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    // CreatedAt
    @PrePersist
    public void prePersist(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    // UpdatedAt
    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }
}
