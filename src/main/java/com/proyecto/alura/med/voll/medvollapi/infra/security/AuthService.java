package com.proyecto.alura.med.voll.medvollapi.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.alura.med.voll.medvollapi.domain.users.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {

  private UserRepository repository;

  public AuthService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return repository.findByUser(username);
  }
}




