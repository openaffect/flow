package io.openaffect.flow.domain.person;

import io.openaffect.flow.domain.IRepository;

import java.util.Optional;

public interface IPersonRepository extends IRepository<Person, PersonId> {

  public Optional<Person> findByUsername(String username);

}
