package com.proyecto.alura.med.voll.medvollapi.domain.medic;

import com.proyecto.alura.med.voll.medvollapi.domain.address.Direccion;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.ActualizarMedicoDTO;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.MedicoDTO;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String email;
  private String telefono;
  private String documento;

  @Enumerated(EnumType.STRING)
  private Especialidad especialidad;

  @Embedded
  private Direccion direccion;

  private boolean activo;

  public Medico(MedicoDTO medico) {
    this.nombre = medico.nombre();
    this.email = medico.email();
    this.telefono = medico.telefono();
    this.documento = medico.documento();
    this.especialidad = medico.especialidad();
    this.direccion = medico.direccion();
    this.activo = true;
  }

  public void actualizarDatos(ActualizarMedicoDTO actualizarMedico) {
    if (actualizarMedico.nombre() != null) {
      this.nombre = actualizarMedico.nombre();
    }
    if (actualizarMedico.documento() != null) {
      this.documento = actualizarMedico.documento();
    }
    if (actualizarMedico.direccion() != null) {
      this.direccion =
        direccion.actualizarDireccion(actualizarMedico.direccion());
    }
  }

  public void medicoInactivo() {
    this.activo = false;
  }
}
