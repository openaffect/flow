package io.openaffect.flow.domain;

public interface IEntity<ENTITY extends IEntity, ID extends Id> {

  ID getId();
  ENTITY deepClone();

}
