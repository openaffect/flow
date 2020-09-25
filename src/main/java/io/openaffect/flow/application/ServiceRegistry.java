package io.openaffect.flow.application;

import io.openaffect.flow.application.identitymgmt.IdentityManagementFacade;
import io.openaffect.flow.application.joke.JokeFacade;
import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.domain.person.IPersonRepository;
import io.openaffect.flow.infrastructure.persistence.memory.InMemoryJokeRepository;
import io.openaffect.flow.infrastructure.persistence.memory.InMemoryPersonRepository;

public class ServiceRegistry {

  private static ServiceRegistry singleton; // code smell... this will change in the future!

  // dependencies, now instantiated directly in the registry... will evolve towards dependency injection
  private static IJokeRepository jokeRepository;
  private static JokeFacade jokeFacade;

  private static IPersonRepository personRepository;
  private static IdentityManagementFacade identityManagementFacade;

  public static ServiceRegistry getServiceRegistry() {
    if (singleton == null) {
      singleton = new ServiceRegistry();
    }
    return singleton;
  }

  private ServiceRegistry() {
    singleton = this;
    jokeRepository = new InMemoryJokeRepository();
    jokeFacade = new JokeFacade(jokeRepository);
    personRepository = new InMemoryPersonRepository();
    identityManagementFacade = new IdentityManagementFacade(personRepository);
  }

  public JokeFacade getJokeFacade() {
    return jokeFacade;
  }

  public IdentityManagementFacade getIdentityManagementFacade() {
    return identityManagementFacade;
  }

}
