package io.openaffect.flow.application.identitymgmt.login;

import io.openaffect.flow.application.BusinessException;
import lombok.Value;

@Value
public class RegistrationFailedException extends BusinessException {

  public RegistrationFailedException(String message) {
    super(message);
  }

}
