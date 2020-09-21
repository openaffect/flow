package io.openaffect.flow.domain.joke;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JokeIdTest {

  @Test
  public void iCanUseJokeIds() {
    JokeId jokeId = new JokeId();
    JokeId jokeId2 = new JokeId(jokeId.asString());
    JokeId jokeId3 = new JokeId(UUID.fromString(jokeId.asString()));

    assertEquals(jokeId, jokeId2);
    assertEquals(jokeId, jokeId3);
  }

  @Test
  public void invalidStringPassedToConstructor() {
    assertThrows(IllegalArgumentException.class, () -> {
      JokeId joke = new JokeId("invalid string - not a UUID");
    });
  }

  @Test
  public void nullPassedToConstructor() {
    assertThrows(NullPointerException.class, () -> {
      UUID nullUUID = null;
      JokeId joke = new JokeId(nullUUID);
    });
  }

}