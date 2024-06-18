package com.proyecto.alura.med.voll.medvollapi.domain.medic.dto;

import com.proyecto.alura.med.voll.medvollapi.domain.medic.Medico;

public record DatosMedicoResponseDTO(
  Long id,
  String nombre,
  String email,
  String telefono,
  String documento,
  DireccionDTO direccion
) {
  public DatosMedicoResponseDTO(Medico medico) {
    this(
      medico.getId(),
      medico.getNombre(),
      medico.getEmail(),
      medico.getTelefono(),
      medico.getDocumento(),
      new DireccionDTO(
        medico.getDireccion().getCalle(),
        medico.getDireccion().getDistrito(),
        medico.getDireccion().getCiudad(),
        medico.getDireccion().getNumero(),
        medico.getDireccion().getComplemento()
      )
    );
  }
}
