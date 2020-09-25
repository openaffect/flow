package io.openaffect.flow.application.identitymgmt;

import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.openaffect.flow.application.identitymgmt.authenticate.CurrentUserDTO;
import io.openaffect.flow.application.identitymgmt.login.RegisterCommand;
import io.openaffect.flow.application.identitymgmt.login.RegistrationFailedException;
import io.openaffect.flow.domain.person.IPersonRepository;
import io.openaffect.flow.domain.person.Person;

public class IdentityManagementFacade {

  private IPersonRepository personRepository;

  public IdentityManagementFacade(IPersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public void register(RegisterCommand command) throws RegistrationFailedException {
    Person existingPersonWithSameUsername = personRepository.findByUsername(command.getUsername()).orElse(null);

    if (existingPersonWithSameUsername != null) {
      throw new RegistrationFailedException("Username is already used");
    }

    try {
      Person newPerson = Person.builder()
        .username(command.getUsername())
        .firstName(command.getFirstName())
        .lastName(command.getLastName())
        .email(command.getEmail())
        .clearTextPassword(command.getClearTextPassword())
        .build();

      personRepository.save(newPerson);
    } catch (Exception e) {
      throw new RegistrationFailedException(e.getMessage());
    }
  }

  public CurrentUserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
    Person person = personRepository.findByUsername(command.getUsername())
      .orElseThrow(() -> new AuthenticationFailedException("User not found"));

    boolean success = person.authenticate(command.getClearTextPassword());
    if (!success) {
      throw new AuthenticationFailedException("Verification of credentials failed");
    }

    CurrentUserDTO currentUser = CurrentUserDTO.builder()
      .username(person.getUsername())
      .firstName(person.getFirstName())
      .lastName(person.getLastName())
      .email(person.getEmail())
      .build();

    return currentUser;
  }
}
