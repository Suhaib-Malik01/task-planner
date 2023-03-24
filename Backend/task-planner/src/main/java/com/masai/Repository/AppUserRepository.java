package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Module.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    
    Optional<AppUser> findByEmail(String email);
}
