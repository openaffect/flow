package io.openaffect.flow.application.identitymgmt;

import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.openaffect.flow.application.identitymgmt.login.RegisterCommand;
import io.openaffect.flow.application.identitymgmt.login.RegistrationFailedException;
import io.openaffect.flow.domain.person.IPersonRepository;
import io.openaffect.flow.domain.person.Person;
import io.openaffect.flow.infrastructure.persistence.memory.InMemoryPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityManagementFacadeIT {

  IPersonRepository personRepository;
  IdentityManagementFacade identityManagementFacade;

  @BeforeEach
  void setupFacadeWithEmptyRepository() {
    personRepository = new InMemoryPersonRepository();
    identityManagementFacade = new IdentityManagementFacade(personRepository);
  }

  @Test
  void iCanRegister() throws RegistrationFailedException {
    String username = "oliechti";
    assertEquals(0, personRepository.findAll().size());
    assertTrue(personRepository.findByUsername(username).isEmpty());

    RegisterCommand registerCommand = buildRegisterCommandForOlivier(username);

    identityManagementFacade.register(registerCommand);
    assertEquals(1, personRepository.findAll().size());

    Person newlyAddedPerson = personRepository.findByUsername(username).orElse(null);
    assertEquals(newlyAddedPerson.getUsername(), username);
  }

  @Test
  void iCannotRegisterIfUsernameIsAlreadyUsed() throws RegistrationFailedException {
    String username = "oliechti";
    RegisterCommand registerCommand = buildRegisterCommandForOlivier(username);
    identityManagementFacade.register(registerCommand);

    assertThrows(RegistrationFailedException.class, () -> {
      identityManagementFacade.register(registerCommand);
    });
  }

  @Test
  void iCanLoginWithCorrectPassword() throws RegistrationFailedException, AuthenticationFailedException {
    String username = "oliechti";
    RegisterCommand registerCommand = buildRegisterCommandForOlivier(username);
    identityManagementFacade.register(registerCommand);

    AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
      .username(registerCommand.getUsername())
      .clearTextPassword(registerCommand.getClearTextPassword())
      .build();
    identityManagementFacade.authenticate(authenticateCommand);
  }

  void iCannotLoginWithWrongPassword() throws RegistrationFailedException {
    String username = "oliechti";
    RegisterCommand registerCommand = buildRegisterCommandForOlivier(username);
    identityManagementFacade.register(registerCommand);

    AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
      .username(registerCommand.getUsername())
      .clearTextPassword(registerCommand.getClearTextPassword())
      .build();
    assertThrows(AuthenticationFailedException.class, () -> {
      identityManagementFacade.authenticate(authenticateCommand);
    });

  }


  private RegisterCommand buildRegisterCommandForOlivier(String username) {
    return RegisterCommand.builder()
      .username(username)
      .firstName("Olivier")
      .lastName("Liechti")
      .email("olivier.liechti@heig-vd.ch")
      .clearTextPassword("myvoiceismypassword")
      .build();
  }

}