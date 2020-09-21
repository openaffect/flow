package io.openaffect.flow.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

// https://matthiasnoback.nl/2018/05/when-and-where-to-determine-the-id-of-an-entity/
// https://goodbackend.com/uuid-as-a-database-key/

@EqualsAndHashCode
public abstract class Id {

  private UUID id;

  public Id() {
    id = UUID.randomUUID();
  }

  public Id(String id) {
    this.id = UUID.fromString(id);
  }

  public Id(UUID id) {
    if (id == null) {
      throw new NullPointerException();
    }
    this.id = id;
  }

  public String asString() {
    return id.toString();
  }

}
