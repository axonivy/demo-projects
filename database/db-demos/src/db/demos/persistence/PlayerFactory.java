package db.demos.persistence;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Random;

import ch.ivyteam.ivy.scripting.objects.Date;

public class PlayerFactory {
  private static final Random RANDOM = new Random();
  private static final List<String> FIRST_NAMES = List.of("Reto", "Bruno", "Christian", "Jacek", "Lukas", "Elio", "Tobias", "Fabian", "Jens", "Louis", "Alex");
  private static final List<String> LAST_NAMES = List.of("Weiss", "Bütler", "Strebel", "Lajdecki", "Lieb", "Di Puma", "Gretler", "Heuberger", "Hartman", "Müller", "Suter");
  public static final Player create() {
    var player = new Player();
    player.setFirstName(FIRST_NAMES.get(RANDOM.nextInt(FIRST_NAMES.size())));
    player.setLastName(LAST_NAMES.get(RANDOM.nextInt(LAST_NAMES.size())));
    var year = Year.now().minusYears(RANDOM.nextInt(60));
    var month = Month.of(RANDOM.nextInt(12) + 1);
    var yearMonth = YearMonth.of(year.getValue(), month);
    var day = RANDOM.nextInt(yearMonth.atEndOfMonth().getDayOfMonth()) + 1;
    player.setBirthDate(new Date(year.getValue(), month.getValue(), day));
    return player;
  }
}
