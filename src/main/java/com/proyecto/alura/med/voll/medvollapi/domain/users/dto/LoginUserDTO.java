package com.proyecto.alura.med.voll.medvollapi.domain.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(
  @NotBlank @NotNull String user,
  @NotBlank @NotNull String password
) {}
