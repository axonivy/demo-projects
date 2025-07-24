package db.demo.persistence.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.Date;
import db.demos.persistence.Person;
import db.demos.persistence.PersonRepo;

@IvyTest
class PersonRepoIvyTest {

  PersonRepo repository = Person.repository();

  @Test
  void useRepo() {
    var all = repository.findAll().toList();
    assertThat(all).isEmpty();

    var james = new Person();
    james.setFirstName("James");
    james.setLastName("Bond");
    james.setBirthDate(new Date(1968, 4, 13));

    repository.insert(james);
    assertThat(repository.findAll().toList())
        .hasSize(1);
    var persisted = repository.findAll()
        .filter(p -> "James".equals(p.getFirstName()))
        .findFirst();
    assertThat(persisted).isPresent();
    assertThat(persisted.get().getId())
        .isNotNull();

    repository.delete(persisted.get());
    assertThat(repository.findAll().toList())
        .isEmpty();
  }

  @Test
  void query_findByLastName() {
    var orville = new Person();
    orville.setFirstName("Orville");
    orville.setLastName("Wright");
    orville.setBirthDate(new Date(1871, 8, 19));

    var wilbur = new Person();
    wilbur.setFirstName("Wilbur");
    wilbur.setLastName("Wright");
    wilbur.setBirthDate(new Date(1867, 4, 16));

    var louis = new Person();
    louis.setFirstName("Louis Charles Joseph");
    louis.setLastName("Blériot");
    louis.setBirthDate(new Date(1872, 7, 1));

    List<Person> pilots = List.of(orville, wilbur, louis);
    repository.insertAll(pilots);

    List<Person> wrightBrothers = repository.findByLastName("Wright");
    assertThat(wrightBrothers)
        .extracting(Person::getFirstName)
        .containsOnly("Orville", "Wilbur");

    assertThat(repository.findByLastName("Blériot"))
        .extracting(Person::getFirstName)
        .containsOnly("Louis Charles Joseph");

    repository.deleteAll(pilots);
  }

}
