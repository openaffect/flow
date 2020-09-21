package io.openaffect.flow.application.joke;

import io.openaffect.flow.domain.joke.IJokeRepository;
import io.openaffect.flow.infrastructure.persistence.memory.InMemoryJokeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JokeDTOFacadeIT {

  private JokeFacade jokeFacade;

  @BeforeEach
  public void setupJokeFacade() {
    IJokeRepository jokeRepository = new InMemoryJokeRepository();
    this.jokeFacade = new JokeFacade(jokeRepository);
  }

  @Test
  public void publishJoke() {
    ProposeJokeCommand command = ProposeJokeCommand.builder()
      .author("Olivier")
      .text("Bla bla bla")
      .build();
    jokeFacade.proposeJoke(command);

    JokesDTO view = jokeFacade.getJokes(null);
    assertNotNull(view);
    assertEquals(1, view.getJokes().size());
    assertEquals(command.getText(), view.getJokes().get(0).getText());
  }

  @Test
  public void getJokesSafeForChildren() {
    jokeFacade.proposeJoke(ProposeJokeCommand.builder()
      .text("this one is safe for children")
      .build());
    jokeFacade.proposeJoke(ProposeJokeCommand.builder()
      .text("this one is also safe for children")
      .build());
    jokeFacade.proposeJoke(ProposeJokeCommand.builder()
      .text("this one is NOT safe for children, because it talks about sex")
      .build());

    JokesDTO viewWithoutAdultJokes = jokeFacade.getJokes(JokesQuery.builder()
      .safeForChildren(true)
      .build());
    assertEquals(2, viewWithoutAdultJokes.getJokes().size());

    JokesDTO viewWithAdultJokes = jokeFacade.getJokes(JokesQuery.builder()
      .safeForChildren(false)
      .build());
    assertEquals(3, viewWithAdultJokes.getJokes().size());

  }

}