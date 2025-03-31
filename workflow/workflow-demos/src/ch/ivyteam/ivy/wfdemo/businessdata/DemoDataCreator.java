package ch.ivyteam.ivy.wfdemo.businessdata;

import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.scripting.objects.Date;
import workflow.businessdata.Address;
import workflow.businessdata.Dossier;
import workflow.businessdata.Person;

public class DemoDataCreator {

  public static void createDemoDataIfNotExist() {
    BusinessDataRepository repo = BusinessDataRepository.current();
    long dossierCount = countDossier(repo);

    if (dossierCount == 0) {
      createDemoDossier("HEISENBERG DE", "Werner", "Heisenberg", new Date(1901, 12, 5), "97070", "Würzburg",
          "Germany");
      createDemoDossier("PAULI AT", "Wolfgang", "Pauli", new Date(1900, 04, 25), "1010", "Wien", "Austria");
      createDemoDossier("NOETHER DE", "Emmy", "Noether", new Date(1882, 03, 23), "91052", "Erlangen",
          "Germany");
      createDemoDossier("TURING GB", "Alan", "Turing", new Date(1912, 06, 23), "10000", "London", "England");
      createDemoDossier("RAMANUJAN IN", "Srinivasa", "Ramanujan", new Date(1887, 12, 22), "30000", "Erode",
          "India");
      createDemoDossier("LOVELACE GB", "Ada", "Lovelace", new Date(1815, 12, 10), "20000", "London",
          "England");
      createDemoDossier("POINCARE FR", "Henri", "Poincaré", new Date(1854, 04, 29), "54000", "Nancy",
          "France");
      createDemoDossier("EULER CH", "Leonhard", "Euler", new Date(1707, 04, 15), "4000", "Basel",
          "Switzerland");
      createDemoDossier("VOLTA IT", "Alessandro", "Volta", new Date(1745, 02, 18), "22100", "Como", "Italy");
      createDemoDossier("NEUMANN HU", "John", "von Neumann", new Date(1903, 12, 28), "1011", "Budapest",
          "Hungary");
      waitForDossierCount(repo, 10);
    }
  }

  private static void waitForDossierCount(BusinessDataRepository repo, int count) {
    long dossierCount = countDossier(repo);
    while (dossierCount < count) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {}
      dossierCount = countDossier(repo);
    }
  }

  private static long countDossier(BusinessDataRepository repo) {
    return getDossiers(repo).count();
  }

  private static Result<Dossier> getDossiers(BusinessDataRepository repo) {
    return repo.search(Dossier.class).execute();
  }

  private static void createDemoDossier(String dossierName, String firstName, String lastName, Date birthdate,
      String zip, String city, String country) {
    Dossier dossier = new Dossier();
    dossier.setName(dossierName);

    Person person = new Person();
    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setBirthDate(birthdate);

    Address address = new Address();
    address.setZip(zip);
    address.setCity(city);
    address.setCountry(country);

    person.setAddress(address);
    dossier.setPerson(person);

    BusinessDataRepository repo = BusinessDataRepository.current();
    repo.save(dossier);
  }

  public static void clearDemoData() {
    BusinessDataRepository repo = BusinessDataRepository.current();
    for (Dossier info : getDossiers(repo).getAll()) {
      repo.delete(info);
    }
    waitForDossierCount(repo, 0);
  }
}
