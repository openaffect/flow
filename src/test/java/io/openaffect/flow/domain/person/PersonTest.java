package io.openaffect.flow.domain.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

  @Test
  public void createPersonWithClearTextPassword() {
    String clearTextPassword = "hello";
    String encryptedPassword = clearTextPassword.toUpperCase();
    Person person = Person.builder()
      .username("username")
      .firstName("firstName")
      .lastName("lastName")
      .email("email")
      .clearTextPassword(clearTextPassword)
      .build();
    assertEquals(encryptedPassword, person.getEncryptedPassword());
  }

  @Test
  public void authenticate() {
    String clearTextPassword = "myvoiceismypassword";
    Person person = Person.builder()
      .username("username")
      .firstName("firstName")
      .lastName("lastName")
      .email("email")
      .clearTextPassword(clearTextPassword)
      .build();
    assertTrue(person.authenticate(clearTextPassword));
    assertFalse(person.authenticate("notapassword"));
  }

  @Test
  public void copyDoesADeepCopy() {
    Person original = Person.builder()
      .username("oliechti")
      .firstName("Olivier")
      .lastName("Liechti")
      .email("olivier.liechti@heig-vd.ch")
      .clearTextPassword("password")
      .build();
    Person copy = original.deepClone();

    assertNotSame(original.getId(), copy.getId());

  }


}