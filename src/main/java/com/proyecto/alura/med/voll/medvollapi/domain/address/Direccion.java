package com.proyecto.alura.med.voll.medvollapi.domain.address;

import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.DireccionDTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

  private String calle;
  private String distrito;
  private String ciudad;
  private Long numero;
  private String complemento;

  public Direccion actualizarDireccion(DireccionDTO direccion) {
    this.calle = direccion.calle();
    this.distrito = direccion.distrito();
    this.ciudad = direccion.ciudad();
    this.numero = direccion.numero();
    this.complemento = direccion.complemento();
    return this;

  }


}
