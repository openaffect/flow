package io.openaffect.flow.application;

import io.openaffect.flow.application.joke.JokeFacade;
import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.infrastructure.persistence.memory.InMemoryJokeRepository;

import javax.enterprise.context.ApplicationScoped;

public class ServiceRegistry {

  private static ServiceRegistry singleton; // code smell... this will change in the future!

  // dependencies, now instantiated directly in the registry... will evolve towards dependency injection
  private static IJokeRepository jokeRepository;
  private static JokeFacade jokeFacade;

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
  }

  public JokeFacade getJokeFacade() {
    return jokeFacade;
  }


}
