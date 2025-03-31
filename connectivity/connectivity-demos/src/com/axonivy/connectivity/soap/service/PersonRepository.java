package com.axonivy.connectivity.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connectivity.Person;

/**
 * Helper class to simulate a storage for Web Service Process
 * <code>Process/soap/service/personService</code>
 *
 * @since 7.1
 */
public class PersonRepository {

  private static PersonRepository INSTANCE;

  public synchronized static PersonRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PersonRepository();
    }
    return INSTANCE;
  }

  private final List<Person> persons = new ArrayList<>();

  private PersonRepository() {
    persons.add(createPerson("Bruno", "Bütler"));
    persons.add(createPerson("Reto", "Weiss"));
    persons.add(createPerson("Renato", "Stalder"));
    persons.add(createPerson("Markus", "Binzegger"));
    persons.add(createPerson("Reguel", "Wermelinger"));
  }

  public List<Person> findAll() {
    return new ArrayList<>(persons);
  }

  public Person addPerson(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("person must not be null");
    }
    if (StringUtils.isBlank(person.getFirstname()) || StringUtils.isBlank(person.getLastname())) {
      throw new IllegalArgumentException("first and lastname is mandatory");
    }
    if (person.getId() == null) {
      person.setId(UUID.randomUUID());
    }
    persons.add(person);
    return person;
  }

  /**
   * @param person to delete
   * @return deleted person or <code>null</code> if person was not in
   *         repository.
   */
  public Person delete(Person person) {
    Iterator<Person> iterator = persons.iterator();
    while (iterator.hasNext()) {
      Person p = iterator.next();
      if (p.getFirstname().equals(person.getFirstname()) &&
          p.getLastname().equals(person.getLastname())) {
        iterator.remove();
        return p;
      }
    }
    return null;
  }

  private Person createPerson(String firstname, String lastname) {
    Person p = new Person();
    p.setId(UUID.randomUUID());
    p.setFirstname(firstname);
    p.setLastname(lastname);
    return p;
  }

}
