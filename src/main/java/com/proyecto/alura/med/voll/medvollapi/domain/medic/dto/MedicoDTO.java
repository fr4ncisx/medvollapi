package com.proyecto.alura.med.voll.medvollapi.domain.medic.dto;

import com.proyecto.alura.med.voll.medvollapi.domain.address.Direccion;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.Especialidad;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(
  @NotBlank String nombre,
  @NotBlank @Email String email,
  @NotBlank String telefono,
  @NotBlank @Pattern(regexp = "\\d{4,8}") String documento,
  @NotNull Especialidad especialidad,
  @NotNull @Valid Direccion direccion
) {}
