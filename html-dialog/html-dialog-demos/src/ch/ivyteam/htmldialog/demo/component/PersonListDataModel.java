package ch.ivyteam.htmldialog.demo.component;

import java.util.List;

import jakarta.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ch.ivyteam.htmldialog.demo.Person;

public class PersonListDataModel extends ListDataModel<Person> implements SelectableDataModel<Person> {

  public PersonListDataModel() {}

  public PersonListDataModel(List<Person> persons) {
    super(persons);
  }

  @Override
  public Person getRowData(String rowKey) {
    if (rowKey == null) {
      return null;
    }
    @SuppressWarnings("unchecked")
    List<Person> persons = (List<Person>) getWrappedData();
    for (Person person : persons) {
      if (rowKey.equals(person.getName())) {
        return person;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(Person person) {
    return person.getName();
  }
}
