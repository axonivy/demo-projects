package db.demos.persistence;

import java.util.List;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;

@Repository(dataStore = "league")
public interface PlayerRepo extends CrudRepository<Player, Integer> {

  @Find
  List<Player> findByLastName22X1p1f922(String lastName);

}
