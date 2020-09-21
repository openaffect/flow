package io.openaffect.flow.application.joke;

import lombok.*;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class JokesDTO {

  @Builder
  @Getter
  @EqualsAndHashCode
  public static class JokeDTO {
    private String text;
  }

  @Singular
  private List<JokeDTO> jokes;

}
