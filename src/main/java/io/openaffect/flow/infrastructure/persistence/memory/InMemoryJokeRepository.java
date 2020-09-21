package io.openaffect.flow.infrastructure.persistence.memory;

import io.openaffect.flow.application.joke.JokesQuery;
import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.domain.joke.Joke;
import io.openaffect.flow.domain.joke.JokeId;
import io.openaffect.flow.domain.joke.JokeType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryJokeRepository implements IJokeRepository {

  private Map<JokeId, Joke> store = new ConcurrentHashMap<>();

  @Override
  public void save(Joke joke) {
    store.put(joke.getId(), joke);
  }

  @Override
  public void remove(JokeId jokeId) {
    store.remove(jokeId);
  }

  @Override
  public Optional<Joke> findById(JokeId jokeId) {
    Joke existingJoke = store.get(jokeId);
    if (existingJoke == null) {
      return Optional.empty();
    }
    Joke clonedJoke = existingJoke.toBuilder().build();
    return Optional.of(clonedJoke);
  }

  @Override
  public Collection<Joke> findAll() {
    return store.values().stream()
      .map(joke -> joke.toBuilder().build())
      .collect(Collectors.toList());
  }

  @Override
  public Collection<Joke> find(JokesQuery query) {
    if (query != null && query.isSafeForChildren()) {
      return findAll().stream()
        .filter(joke -> joke.getJokeType() != JokeType.ADULT)
        .collect(Collectors.toList());
    }
    return findAll();
  }
}
