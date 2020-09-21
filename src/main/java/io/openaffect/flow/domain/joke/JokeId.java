package io.openaffect.flow.domain.joke;

import io.openaffect.flow.domain.Id;

import java.util.UUID;

public class JokeId extends Id {

  public JokeId() {
    super();
  }

  public JokeId(String id) {
    super(id);
  }

  public JokeId(UUID id) {
    super(id);
  }

}
