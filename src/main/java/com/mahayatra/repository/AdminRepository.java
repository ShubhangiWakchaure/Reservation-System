package com.mahayatra.repository;

import com.mahayatra.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    // Find admin by username and password for login
    Admin findByUsernameAndPassword(String username, String password);

    // Optional: Find admin by username only
    Admin findByUsername(String username);
}
