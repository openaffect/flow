package io.openaffect.flow.application.joke;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Builder
@Getter
@EqualsAndHashCode
public class ProposeJokeCommand {

  @Builder.Default
  private String author = "Anonymous";

  @Builder.Default
  private String text = "No content";

}
