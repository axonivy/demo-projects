package db.demos.persistence;

import ch.ivyteam.ivy.jsf.primefaces.model.LazyRepositoryDataModel;

public class PersonDataModel extends LazyRepositoryDataModel<Person> {

  private static final long serialVersionUID = 1L;

  public PersonDataModel() {
    super(Person.repository());
  }
}
