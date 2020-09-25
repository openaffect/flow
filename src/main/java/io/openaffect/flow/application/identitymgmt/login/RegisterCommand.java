package io.openaffect.flow.application.identitymgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String clearTextPassword;
}
