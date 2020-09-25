package io.openaffect.flow.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AuthenticateCommand {
  private String username;
  private String clearTextPassword;
}
