package io.openaffect.flow.domain.person;

import io.openaffect.flow.domain.Id;

import java.util.UUID;

public class PersonId extends Id {

  public PersonId() {
    super();
  }

  public PersonId(String id) {
    super(id);
  }

  public PersonId(UUID id) {
    super(id);
  }

}
