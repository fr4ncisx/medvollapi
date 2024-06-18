package com.proyecto.alura.med.voll.medvollapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.proyecto.alura.med.voll.medvollapi.domain.users.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${api.security.service}")
  private String secretKey;

  public String tokenGenerator(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      return JWT
        .create()
        .withIssuer("Voll Med Application")
        .withSubject(user.getUser())
        .withClaim("id", user.getId())
        .withExpiresAt(generateExpirationDate(3, zoneOffsetArgentina()))
        .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Error generating token");
    }
  }

  public String getSubject(String token) {
    DecodedJWT verifier = null;
    if(token.equals(null)){
      throw new RuntimeException("Empty token");
    }
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      verifier =
        JWT
          .require(algorithm)
          .withIssuer("Voll Med Application")
          .build()
          .verify(token);
      verifier.getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Invalid token or expired");
    }
    return verifier.getSubject();
  }

  private Instant generateExpirationDate(int hours, ZoneOffset zoneOffset) {
    return LocalDateTime.now().plusHours(hours).toInstant(zoneOffset);
  }

  private ZoneOffset zoneOffsetArgentina() {
    return ZoneOffset.of("-03:00");
  }
}
