package io.openaffect.flow.application;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class BusinessException extends Exception {
  private String message;
}
