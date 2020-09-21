package io.openaffect.flow.domain.joke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeDTOTest {

  @Test
  public void jokeIdsShouldBeGeneratedAutomaticallyIfNotProvided() {
    Joke joke1 = Joke.builder().build();
    Joke joke2 = Joke.builder().build();
    assertNotNull(joke1.getId());
    assertNotNull(joke2.getId());
    assertNotEquals(joke1.getId(), joke2.getId());
    assertNotEquals(joke1.getId().asString(), joke2.getId().asString());
  }

  @Test
  public void jokesCanBeClassified() {
    Joke joke = Joke.builder().build();
    assertEquals(JokeType.UNCLASSIFIED, joke.getJokeType());
    joke.categorizeAs(JokeType.COVID);
    assertEquals(JokeType.COVID, joke.getJokeType());
  }

  @Test void jokesTalkingAboutSexAreClassifiedAsAdult() {
    Joke joke = Joke.builder()
      .text("let's have a joke about sex")
      .build();
    assertEquals(JokeType.ADULT, joke.getJokeType());

  }

}