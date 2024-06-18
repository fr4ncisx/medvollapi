package com.proyecto.alura.med.voll.medvollapi.domain.medic.dto;

import com.proyecto.alura.med.voll.medvollapi.domain.medic.Medico;

public record ListadoMedicoDTO(
  Long id,
  String nombre,
  String especialidad,
  String documento,
  String email
) {
  public ListadoMedicoDTO(Medico medico) {
    this(
      medico.getId(),
      medico.getNombre(),
      String.valueOf(medico.getEspecialidad()),
      medico.getDocumento(),
      medico.getEmail()
    );
  }
}
