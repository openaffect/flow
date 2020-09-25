package io.openaffect.flow.infrastructure.persistence.memory;

import io.openaffect.flow.domain.person.IPersonRepository;
import io.openaffect.flow.domain.person.Person;
import io.openaffect.flow.domain.person.PersonId;
import io.openaffect.flow.infrastructure.persistence.exception.DataCorruptionException;
import io.openaffect.flow.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

  @Override
  public void save(Person entity) {
    // enforce unique username constraint
    synchronized (entity.getUsername()) {
      if (!findByUsername(entity.getUsername()).isEmpty()) {
        throw new IntegrityConstraintViolationException("Cannot save/update person. Integrity constraint violation: username must be unique");
      }
      super.save(entity);
    }
  }

  @Override
  public Optional<Person> findByUsername(String username) {
    List<Person> matchingEntities = findAll().stream()
      .filter(p -> p.getUsername().equals(username))
      .collect(Collectors.toList());

    if (matchingEntities.size() < 1) {
      return Optional.empty();
    }

    if (matchingEntities.size() > 1) {
      throw new DataCorruptionException("Your data store is corrupted. Found several Person with same " + username);
    }

    return Optional.of(matchingEntities.get(0).deepClone());
  }
}
