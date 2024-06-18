package com.proyecto.alura.med.voll.medvollapi.domain.medic.dto;

import jakarta.validation.constraints.NotBlank;

public record DireccionDTO(
  @NotBlank String calle,
  @NotBlank String distrito,
  @NotBlank String ciudad,
  @NotBlank Long numero,
  @NotBlank String complemento
) {}
