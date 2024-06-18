package com.proyecto.alura.med.voll.medvollapi.infra.error;

import jakarta.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorExceptions {

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity filterNotFoundEntity() {
    return ResponseEntity.notFound().build();
  }

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity filterBadRequest(MethodArgumentNotValidException ex) {
    var getFieldError = ex
      .getFieldErrors()
      .stream()
      .map(FilterError::new)
      .toList();

    return ResponseEntity.badRequest().body(getFieldError);
  }

  @SuppressWarnings("rawtypes")
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity duplicateEntrySQL(
    SQLIntegrityConstraintViolationException ex
  ) {
      return ResponseEntity.badRequest().body("Duplicated SQL entry");
  }

  private record FilterError(String variable, String error) {
    public FilterError(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }

  }
}
