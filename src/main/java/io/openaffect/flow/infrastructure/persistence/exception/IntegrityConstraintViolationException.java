package io.openaffect.flow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class IntegrityConstraintViolationException extends RuntimeException {
  private String message;
}
