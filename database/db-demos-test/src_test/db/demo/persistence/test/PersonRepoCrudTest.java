package db.demo.persistence.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.Date;
import db.demos.persistence.Address;
import db.demos.persistence.AddressRepo;
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

  @Test
  void relations() {
    var mickey = new Person();
    mickey.setFirstName("Mickey");
    mickey.setLastName("Mouse");
    mickey.setBirthDate(new Date(1928, 11, 18));

    var disney = new Address();
    disney.setStreet("500 South Buena Vista Street");
    disney.setCity("Burbank");
    disney.setZip("91521");
    mickey.setAddress(disney);
    AddressRepo adresses = Address.repository();
    adresses.insert(disney);

    repository.insert(mickey);
    try {
      assertThat(repository.livingIn("Burbank"))
          .extracting(Person::getFirstName)
          .containsOnly("Mickey");
    } finally {
      repository.delete(mickey);
    }
  }

}
