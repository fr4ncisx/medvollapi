package com.proyecto.alura.med.voll.medvollapi.domain.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.alura.med.voll.medvollapi.domain.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public UserDetails findByUser(String username);
}
