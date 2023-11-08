package com.ecommerceback.ecommercebackend.repository;

import com.ecommerceback.ecommercebackend.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Query("SELECT r FROM Roles r WHERE r.authority=:authority")
    Optional<Roles> findByAuthority(String authority);
}
