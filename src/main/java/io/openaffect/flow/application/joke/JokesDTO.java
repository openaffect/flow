package io.openaffect.flow.application.joke;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

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
