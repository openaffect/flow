package io.openaffect.flow.infrastructure.persistence.memory;

import io.openaffect.flow.application.joke.JokesQuery;
import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.domain.joke.Joke;
import io.openaffect.flow.domain.joke.JokeId;
import io.openaffect.flow.domain.joke.JokeType;

import java.util.Collection;
import java.util.stream.Collectors;

public class InMemoryJokeRepository extends InMemoryRepository<Joke, JokeId> implements IJokeRepository {

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
