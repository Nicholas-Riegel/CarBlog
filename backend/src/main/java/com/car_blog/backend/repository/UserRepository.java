package com.car_blog.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_blog.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    // You don't write any method bodies. Spring Data JPA creates the implementation at startup, and you get methods like save, findById, and findAll for free.
    // Repository asks db questions; Services decides what to do with the answers

    // this is a custom direct query that doesn't come automatically.
    // At startup, Spring reads the name. findBy means "search", and Username matches the field username in your User class. It writes the query itself, something like SELECT * FROM users WHERE username = ?. This is why the spelling and capitalisation must match the field exactly.
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
