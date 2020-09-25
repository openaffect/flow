package io.openaffect.flow.infrastructure.persistence.memory;

import io.openaffect.flow.domain.person.Person;
import io.openaffect.flow.infrastructure.persistence.exception.IntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPersonRepositoryTest {

  InMemoryPersonRepository inMemoryPersonRepository;

  @BeforeEach
  void setupEmptyRepository() {
    inMemoryPersonRepository = new InMemoryPersonRepository();
  }

  @Test
  void uniqueUsernameConstraintIsEnforced() {
    Person.PersonBuilder personTemplate = Person.builder()
      .username("username")
      .firstName("first name")
      .lastName("last name")
      .email("email")
      .clearTextPassword("my password");

    Person person = personTemplate.build();
    Person personWithDuplicateUsername = personTemplate.build();

    inMemoryPersonRepository.save(person);
    Exception exception = assertThrows(RuntimeException.class, () -> {
      inMemoryPersonRepository.save(personWithDuplicateUsername);
    });
    assertEquals("Cannot save/update person. Integrity constraint violation: username must be unique", exception.getMessage());

  }

    @Test
  void uniqueUsernameConstraintIsEnforcedUnderConcurrentAccess() throws InterruptedException, ExecutionException {
    int numberOfThreads = 5;
    int numberOfOperations = 10;

    Person.PersonBuilder personTemplate = Person.builder()
      .username("username")
      .firstName("first name")
      .lastName("last name")
      .email("email")
      .clearTextPassword("my password");

    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

    AtomicInteger numberOfRejectedOperations = new AtomicInteger();
    AtomicInteger numberOfAttemptedOperations = new AtomicInteger();

    Future[] futures = new Future[numberOfOperations];

    for (int i=0; i<numberOfOperations; i++) {
      futures[i] = executorService.submit(() -> {
        Person personWithDuplicateUsername = personTemplate.build();
        try {
          numberOfAttemptedOperations.getAndIncrement();
          inMemoryPersonRepository.save(personWithDuplicateUsername);
        } catch (IntegrityConstraintViolationException e) {
          numberOfRejectedOperations.getAndIncrement();
        }
      });
    }

    for (int i=0; i<numberOfOperations; i++) {
      futures[i].get();
    }

    assertEquals(numberOfOperations, numberOfAttemptedOperations.get());
    assertEquals(numberOfOperations-1, numberOfRejectedOperations.get());
    assertEquals(1, inMemoryPersonRepository.findAll().size());

  }

}