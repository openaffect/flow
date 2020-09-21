package io.openaffect.flow.application.joke;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@Builder
@Getter
@EqualsAndHashCode
public class JokesQuery {

  @Builder.Default
  private boolean safeForChildren = true;

}
