package ch.ivyteam.htmldialog.demo.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Strings;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import ch.ivyteam.htmldialog.demo.Person;
import ch.ivyteam.htmldialog.demo.component.PersonLazySorter;

public class DataSource {

  private final List<Person> allPersons;

  public DataSource(int sourceSize) {
    allPersons = DataGenerator.generatePersons(sourceSize);
  }

  private List<Person> paginate(int first, int pageSize, List<Person> filteredPersons) {
    if (filteredPersons.size() > pageSize) {
      if (first + pageSize > filteredPersons.size()) {
        return filteredPersons.subList(first, first + (filteredPersons.size() % pageSize));
      } else {
        return filteredPersons.subList(first, first + pageSize);
      }
    } else {
      return filteredPersons;
    }
  }

  private List<Person> sort(Map<String, SortMeta> sortBy, List<Person> filteredPersons) {
    if (sortBy != null) {
      Collections.sort(filteredPersons, new PersonLazySorter(sortBy));
    }
    return filteredPersons;
  }

  private List<Person> filter(Map<String, FilterMeta> filters) {
    if (filters == null) {
      return allPersons;
    }
    List<Person> filteredPersons = new ArrayList<>();
    for (Person person : allPersons) {
      if (filter(filters, person)) {
        filteredPersons.add(person);
      }
    }
    return filteredPersons;
  }

  private static boolean filter(Map<String, FilterMeta> filters, Person person) {
    if (filters.isEmpty()) {
      return true;
    }
    boolean allFiltersMatch = true;
    for (String filterProperty : filters.keySet()) {
      String filterValue = filters.get(filterProperty).getFilterValue().toString();
      allFiltersMatch = allFiltersMatch && matches(filterValue, person, filterProperty);
    }
    return allFiltersMatch;
  }

  private static boolean matches(String filterValue, Person person, String filterProperty) {
    if ("globalFilter".equals(filterProperty)) {
      if (Strings.CI.contains(person.getName(), filterValue)
          || Strings.CI.contains(person.getFirstname(), filterValue)
          || person.getBirthYear().toString().contains(filterValue)) {
        return true;
      }
      return false;
    }
    String fieldValue = getValue(person, filterProperty);
    return Strings.CI.startsWith(fieldValue, filterValue);
  }

  private static String getValue(Person person, String filterProperty) {
    switch (filterProperty) {
      case "name":
        return person.getName();
      case "firstname":
        return person.getFirstname();
      case "birthYear":
        return person.getBirthYear().toString();
      case null:
      default:
        break;
    }
    return null;
  }

  public Person getPerson(String rowKey) {
    for (Person person : allPersons) {
      if (person.getId().equals(Double.valueOf(rowKey))) {
        return person;
      }
    }
    return null;
  }

  public List<Person> query(Map<String, FilterMeta> filters, Map<String, SortMeta> sortBy, int pageSize,
      int first) {
    List<Person> persons = filter(filters);
    persons = sort(sortBy, persons);
    return paginate(first, pageSize, persons);
  }

  public int count(Map<String, FilterMeta> filters) {
    return filter(filters).size();
  }
}
