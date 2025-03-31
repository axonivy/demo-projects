package ch.ivyteam.htmldialog.demo.component;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import ch.ivyteam.htmldialog.demo.Person;
import ch.ivyteam.htmldialog.demo.data.DataSource;

@SuppressWarnings("serial")
public class PersonLazyDataModel extends LazyDataModel<Person> {

  private final DataSource source;

  public PersonLazyDataModel(DataSource source) {
    this.source = source;
  }

  @Override
  public Person getRowData(String rowKey) {
    return source.getPerson(rowKey);
  }

  @Override
  public String getRowKey(Person person) {
    return person.getId().toString();
  }

  @Override
  public List<Person> load(int first, int pageSize, Map<String, SortMeta> sortBy,
      Map<String, FilterMeta> filterBy) {
    this.setRowCount(source.count(filterBy));
    return source.query(filterBy, sortBy, pageSize, first);
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }
}
