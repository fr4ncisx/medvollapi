package com.proyecto.alura.med.voll.medvollapi.infra.security;

import com.proyecto.alura.med.voll.medvollapi.domain.users.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private TokenService service;
  private UserRepository repository;

  public SecurityFilter(TokenService service, UserRepository repository) {
    this.service = service;
    this.repository = repository;
  }

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    var authHeader = getToken(request);
    if (authHeader != null) {
      var token = authHeader.replace("Bearer ", "");
      var username = service.getSubject(token); //get username
      if (username != null) {
        // valid token
        var user = repository.findByUser(username);
        var authentication = new UsernamePasswordAuthenticationToken(
          user,
          null,
          user.getAuthorities()
        ); // force login
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }
    return null;
  }
}
