package io.openaffect.flow.application.identitymgmt.authenticate;

import io.openaffect.flow.application.BusinessException;
import lombok.Value;

@Value
public class AuthenticationFailedException extends BusinessException {

  public AuthenticationFailedException(String message) {
    super(message);
  }
}
