package db.demo.persistence.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.Date;
import db.demos.persistence.Person;
import db.demos.persistence.PersonRepo;
import db.demos.persistence._Person;
import jakarta.data.page.PageRequest;

@IvyTest
class PersonRepoIvyTest {

  PersonRepo repository = Person.repository();
  List<Person> pilots;

  @Test
  @SuppressWarnings("unchecked")
  void query_findByLastName() {
    List<Person> wrightBrothers = repository.findByLastName("Wright",
        _Person.birthDate.asc(),
        _Person.firstName.descIgnoreCase());
    assertThat(wrightBrothers)
        .as("Optional sorting parameters create ordered results")
        .extracting(Person::getFirstName)
        .containsExactly("Wilbur", "Orville");

    assertThat(repository.findByLastName("Blériot"))
        .extracting(Person::getFirstName)
        .containsOnly("Louis Charles Joseph");
  }

  @Test
  void query_findByLikePattern() {
    assertThat(repository.findByNamePart("Wri%"))
        .extracting(Person::getLastName)
        .containsOnly("Wright");
  }

  @Test
  void query_byJDQL() {
    var the1867s = repository.bornIn(1867);
    assertThat(the1867s)
        .as("JDQL query statements empower repositories to do advanced search queries")
        .extracting(Person::getFirstName)
        .containsOnly("Wilbur");
  }

  @Test
  void query_distinctNamesWithFilters() {
    var names = repository.namesOfLength(3, 10, PageRequest.ofSize(10));
    assertThat(names)
        .as("omit names longer than 10 characters")
        .containsOnly("Wilbur", "Orville")
        .doesNotContain("Louis Charles Joseph");
  }

  @BeforeEach
  void setUp() {
    pilots = AviationFactory.createPilots();
    repository.insertAll(pilots);
  }

  @AfterEach
  void tearDown() {
    repository.deleteAll(pilots);
  }

  static class AviationFactory {

    static List<Person> createPilots() {
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

      return List.of(orville, wilbur, louis);
    }

  }

}
