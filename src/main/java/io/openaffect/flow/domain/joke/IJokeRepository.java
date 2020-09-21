package io.openaffect.flow.domain.joke;

import io.openaffect.flow.application.joke.JokesQuery;
import io.openaffect.flow.domain.IRepository;

import java.util.Collection;

public interface IJokeRepository extends IRepository<Joke, JokeId> {

  public Collection<Joke> find(JokesQuery query);

}
