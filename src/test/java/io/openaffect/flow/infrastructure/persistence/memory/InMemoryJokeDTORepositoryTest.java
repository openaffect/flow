package io.openaffect.flow.infrastructure.persistence.memory;

import io.openaffect.flow.domain.joke.Joke;
import io.openaffect.flow.domain.joke.JokeId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryJokeDTORepositoryTest {

  InMemoryJokeRepository inMemoryJokeRepository;

  @BeforeEach
  void createEmptyRepository() {
    inMemoryJokeRepository = new InMemoryJokeRepository();
  }

  @Test
  void findByIdReturnsACopyOfPreviouslySavedJoke() {
    Joke newJoke = Joke.builder().build();
    inMemoryJokeRepository.save(newJoke);
    Joke retrievedJoke = inMemoryJokeRepository.findById(newJoke.getId()).orElseThrow(RuntimeException::new);
    assertEquals(newJoke, retrievedJoke);
    assertNotSame(newJoke, retrievedJoke);
  }

  @Test
  void findAllReturnsCopiesOfPreviouslySavedJokes() {
    Joke[] jokes = new Joke[10];
    for (int i=0; i<jokes.length; i++) {
      jokes[i] = Joke.builder().build();
      inMemoryJokeRepository.save(jokes[i]);
    }
    Collection<Joke> retrievedJokes = inMemoryJokeRepository.findAll();

    assertEquals(jokes.length, retrievedJokes.size());
    assertTrue(retrievedJokes.containsAll(Arrays.asList(jokes)));

    retrievedJokes.forEach(joke -> {
      boolean copyFound = false;
      for (int i=0; i<jokes.length; i++) {
        copyFound = copyFound || joke.equals(jokes[i]);
        assertNotSame(joke, jokes[i]);
      }
      assertTrue(copyFound);
    });
  }

  @Test
  void findWithNonExistingIdReturnsEmpty() {
    JokeId unusedJokeId = new JokeId();
    Optional<Joke> retrievedJoke = inMemoryJokeRepository.findById(unusedJokeId);
    assertTrue(retrievedJoke.isEmpty());
  }

  @Test
  void removeAnExistingJoke() {
    Joke joke = Joke.builder().build();
    inMemoryJokeRepository.save(joke);
    assertEquals(1, inMemoryJokeRepository.findAll().size());
    inMemoryJokeRepository.remove(joke.getId());
    assertEquals(0, inMemoryJokeRepository.findAll().size());
  }

  @Test
  void removeANonExistingJokeDoesNotThrowException() {
    inMemoryJokeRepository.remove(new JokeId());
  }

}