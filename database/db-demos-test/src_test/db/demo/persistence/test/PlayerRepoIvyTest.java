package db.demo.persistence.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.Date;
import db.demos.persistence.Player;
import db.demos.persistence.PlayerRepo;
import db.demos.persistence.PlayerRepo_;
import jakarta.data.repository.Repository;

@IvyTest
class PlayerRepoIvyTest {

  private PlayerRepo players;

  @Test
  void useRepo() {
    var all = players.findAll().toList();
    assertThat(all).isEmpty();

    var james = new Player();
    james.setFirstName("James");
    james.setLastName("Bond");
    james.setBirthDate(new Date(1968, 4, 13));

    players.insert(james);
    assertThat(players.findAll().toList())
        .hasSize(1);
    var persisted = players.findAll()
        .filter(p -> "James".equals(p.getFirstName()))
        .findFirst();
    assertThat(persisted).isPresent();
    assertThat(persisted.get().getId())
        .isNotNull();

    players.delete(persisted.get());
    assertThat(players.findAll().toList())
        .isEmpty();
  }

  @Test
  void query_findByLastName() {
    var orville = new Player();
    orville.setFirstName("Orville");
    orville.setLastName("Wright");
    orville.setBirthDate(new Date(1871, 8, 19));

    var wilbur = new Player();
    wilbur.setFirstName("Wilbur");
    wilbur.setLastName("Wright");
    wilbur.setBirthDate(new Date(1867, 4, 16));

    var louis = new Player();
    louis.setFirstName("Louis Charles Joseph");
    louis.setLastName("Blériot");
    louis.setBirthDate(new Date(1872, 7, 1));

    List<Player> pilots = List.of(orville, wilbur, louis);
    players.insertAll(pilots);

    List<Player> wrightBrothers = players.findByLastName("Wright");
    assertThat(wrightBrothers)
        .extracting(Player::getFirstName)
        .containsOnly("Orville", "Wilbur");

    assertThat(players.findByLastName("Blériot"))
        .extracting(Player::getFirstName)
        .containsOnly("Louis Charles Joseph");

    players.deleteAll(pilots);
  }

  @BeforeEach
  void setUp() {
    this.players = new PlayerRepo_(session(PlayerRepo.class));
  }

  private static StatelessSession session(Class<?> repoInterface) {
    var repository = repoInterface.getAnnotation(Repository.class);
    if (repository == null) {
      throw new IllegalArgumentException("No " + Repository.class + " annotation found on " + repoInterface);
    }
    var em = Ivy.persistence().get(repository.dataStore()).createEntityManager();
    var sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
    return sessionFactory.openStatelessSession();
  }

}
