package io.openaffect.flow.domain.person;

import io.openaffect.flow.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Person implements IEntity<Person, PersonId> {

  private PersonId id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;

  @EqualsAndHashCode.Exclude
  private String encryptedPassword;

  public boolean authenticate(String clearTextPassword) {
    return clearTextPassword.toUpperCase().equals(encryptedPassword);
  }

  @Override
  public Person deepClone() {
     return this.toBuilder()
       .id(new PersonId(id.asString()))
       .build();
  }

  public static class PersonBuilder {
    public PersonBuilder clearTextPassword(String clearTextPassword) {

      if (clearTextPassword == null || clearTextPassword.isEmpty()) {
        throw new java.lang.IllegalArgumentException("Password is mandatory");
      }

      encryptedPassword = clearTextPassword.toUpperCase();
      return this;
    }

    public Person build() {
      if (id == null) {
        id = new PersonId();
      }

      if (username == null || username.isEmpty()) {
        throw new java.lang.IllegalArgumentException("Username is mandatory");
      }
      if (encryptedPassword == null || encryptedPassword.isEmpty()) {
        throw new java.lang.IllegalArgumentException("Password is mandatory");
      }
      if (firstName == null || firstName.isEmpty()) {
        throw new java.lang.IllegalArgumentException("First name is mandatory");
      }
      if (lastName == null || lastName.isEmpty()) {
        throw new java.lang.IllegalArgumentException("Last name is mandatory");
      }
      if (email == null || email.isEmpty()) {
        throw new java.lang.IllegalArgumentException("Email is mandatory");
      }

      Person newPerson = new Person(id, username, email, firstName, lastName, encryptedPassword);
      return newPerson;
    }

  }

}
