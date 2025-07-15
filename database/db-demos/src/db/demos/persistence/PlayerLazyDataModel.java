package db.demos.persistence;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import jakarta.data.Direction;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;

public class PlayerLazyDataModel extends LazyDataModel<Player> {
  private static final long serialVersionUID = 1L;

  private final PlayerRepo repository = Player.repository();

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return (int) repository.findAll(PageRequest.ofSize(1), Order.by()).totalElements();
  }

  @Override
  public List<Player> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    int page = first / pageSize + 1;
    var orderBy = orderBy(sortBy);
    return repository.findAll(PageRequest.ofPage(page, pageSize, false), orderBy).content();
  }

  @SuppressWarnings("unchecked")
  private Order<Player> orderBy(Map<String, SortMeta> sortBy) {
    return Order.by(sortBy.values().stream().map(PlayerLazyDataModel::toSort).toArray(Sort[]::new));
  }

  private static Sort<Player> toSort(SortMeta sort) {
    var direction = sort.getOrder() == SortOrder.ASCENDING ? Direction.ASC : Direction.DESC;
    return Sort.of(sort.getField(), direction, false);
  }
}
