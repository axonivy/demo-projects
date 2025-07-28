package db.demos.persistence;

import java.util.List;

import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

@Repository(dataStore = "league")
public interface PersonRepo extends CrudRepository<Person, Integer> {

  @Find
  List<Person> findByLastName(String lastName, @SuppressWarnings("unchecked") Sort<? super Person>... sorts);

  @Query("WHERE lastName like :namePattern")
  List<Person> findByNamePart(String namePattern);

  @Query("WHERE extract(year from birthDate) = :year")
  List<Person> bornIn(int year);

  @Query("SELECT distinct firstName FROM Person " +
      "WHERE length(firstName) >= :min AND length(firstName) <= :max")
  Page<String> namesOfLength(@Param("min") int minLength,
      @Param("max") int maxLength,
      PageRequest pageRequest);

  @Query("WHERE address.city = :city")
  List<Person> livingIn(String city);

}
