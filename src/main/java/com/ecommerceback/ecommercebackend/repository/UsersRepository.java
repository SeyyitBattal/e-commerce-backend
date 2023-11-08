package com.ecommerceback.ecommercebackend.repository;

import com.ecommerceback.ecommercebackend.entity.ApplicationUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<ApplicationUsers, Long> {
    @Query("SELECT u FROM ApplicationUsers u WHERE u.email=:email")
    Optional<ApplicationUsers> findUserByEmail(String email);
}
