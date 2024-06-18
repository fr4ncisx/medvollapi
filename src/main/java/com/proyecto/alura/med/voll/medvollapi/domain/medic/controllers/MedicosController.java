package com.proyecto.alura.med.voll.medvollapi.domain.medic.controllers;

import com.proyecto.alura.med.voll.medvollapi.domain.medic.Medico;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.MedicoRepository;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.ActualizarMedicoDTO;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.DatosMedicoResponseDTO;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.ListadoMedicoDTO;
import com.proyecto.alura.med.voll.medvollapi.domain.medic.dto.MedicoDTO;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

  @Autowired
  private MedicoRepository repository;

  @PostMapping
  public ResponseEntity<DatosMedicoResponseDTO> registrarDatosMedico
  (@RequestBody @Valid MedicoDTO datosMedico,UriComponentsBuilder uri) {
    Medico medico = repository.save(new Medico(datosMedico));
    URI url = uri.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
    DatosMedicoResponseDTO response = new DatosMedicoResponseDTO(medico);
    return ResponseEntity.created(url).body(response);
  }

  @GetMapping
  public Page<ListadoMedicoDTO> medicos(
    @PageableDefault(size = 5, sort = "nombre") Pageable pg
  ) {
    return repository.findByActivoTrue(pg).map(ListadoMedicoDTO::new);
  }
  @GetMapping("/{id}")
  public ResponseEntity<DatosMedicoResponseDTO> medicoRegistradoMostrar(
    @PathVariable Long id
  ) {
    Medico medico = repository.getReferenceById(id);
    DatosMedicoResponseDTO response = new DatosMedicoResponseDTO(medico);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/update")
  @Transactional
  public ResponseEntity<DatosMedicoResponseDTO> updateDataWithResponse(
    @RequestBody @Valid ActualizarMedicoDTO actualizarMedico
  ) {
    Medico medico = repository.getReferenceById(actualizarMedico.id());
    DatosMedicoResponseDTO response = new DatosMedicoResponseDTO(medico);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public void updateInactiveMedic(@PathVariable Long id) {
    Medico medico = repository.getReferenceById(id);
    medico.medicoInactivo();
  }

  @DeleteMapping("/db/delete/{id}")
  @Transactional
  public void deleteMedic(@PathVariable Long id) {
    Medico medico = repository.getReferenceById(id);
    repository.delete(medico);
  }
}
