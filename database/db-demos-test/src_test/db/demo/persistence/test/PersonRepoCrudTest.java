package db.demo.persistence.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.Date;
import db.demos.persistence.Person;
import db.demos.persistence.PersonRepo;

@IvyTest
class PersonRepoCrudTest {

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

}
