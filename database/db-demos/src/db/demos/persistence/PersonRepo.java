package db.demos.persistence;

import java.util.List;

import jakarta.data.Sort;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

@Repository(dataStore = "league")
public interface PersonRepo extends CrudRepository<Person, Integer> {

  @Find
  List<Person> findByLastName(String lastName, @SuppressWarnings("unchecked") Sort<? super Person>... sorts);

}
