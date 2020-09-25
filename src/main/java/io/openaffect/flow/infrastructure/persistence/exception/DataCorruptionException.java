package io.openaffect.flow.infrastructure.persistence.exception;

import lombok.Value;

@Value
public class DataCorruptionException extends RuntimeException {
  private String message;
}
