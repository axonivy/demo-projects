package ch.ivyteam.htmldialog.demo.data;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.htmldialog.demo.Person;

public class DataGenerator {

  private static final String[] firstNames = {"Anna", "Anton", "Angela", "Alvin", "Basil", "Bettina",
      "Claudia", "Christoph", "Daniel", "Denise",
      "Emil", "Eveline", "Franziska", "Felix", "Gerda", "Gregor", "Helena", "Hans",
      "Iris", "Ingolf", "Jaqueline", "Jack", "John", "Jens", "Karin", "Karl", "Lea", "Lukas", "Margreth",
      "Michael", "Nina", "Nils", "Olivia", "Otto", "Pauline", "Pierre", "Quentin", "Quinta",
      "Raphael", "Regina", "Silvan", "Svenja", "Theresia", "Thorsten", "Ulrike", "Urs",
      "Valentina", "Viktor", "Wendy", "Walter", "Xenia", "Xaver", "Ylenia", "Yves",
      "Zora", "Zacharias"};
  private static final String[] lastNames = {"Müller", "Meier", "Huber", "Moser", "Frei", "Fink", "Meyer",
      "Berger", "Tanner",
      "Simpson", "Richter", "Dettling", "Arquint", "Frisch", "Johnson", "Thaler", "Smith",
      "Schmid", "Keller", "Miller", "Jackson", "Abeggler", "Künzi", "Held", "Jung", "Zimmermann",
      "Lustig", "Muster", "Lang"};

  public static List<Person> generatePersons(int count) {
    List<Person> persons = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Person person = new Person();
      person.setId(i + 1);
      person.setFirstname(generateFirstName());
      person.setName(generateLastName());
      person.setBirthYear(generateBirthYear());
      persons.add(person);
    }
    return persons;
  }

  private static int generateBirthYear() {
    return (int) (Math.random() * 77 + 1940);
  }

  private static String generateFirstName() {
    return firstNames[(int) (Math.random() * firstNames.length)];
  }

  private static String generateLastName() {
    if (Math.random() > 0.80) {
      return getLastName() + "-" + getLastName();
    }
    return getLastName();
  }

  private static String getLastName() {
    return lastNames[(int) (Math.random() * lastNames.length)];
  }
}
