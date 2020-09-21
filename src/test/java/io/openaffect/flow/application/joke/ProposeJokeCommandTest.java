package io.openaffect.flow.application.joke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProposeJokeCommandTest {

  @Test
  public void defaultValuesAreProvided() {
    ProposeJokeCommand command = ProposeJokeCommand.builder().build();
    assertNotNull(command.getAuthor());
    assertNotNull(command.getText());
  }

}