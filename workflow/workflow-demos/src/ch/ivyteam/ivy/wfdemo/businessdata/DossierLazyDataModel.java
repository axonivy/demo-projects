package ch.ivyteam.ivy.wfdemo.businessdata;

import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Query;
import workflow.businessdata.Dossier;

public class DossierLazyDataModel extends AbstractBusinessDataLazyDataModel<Dossier> {

  private static final long serialVersionUID = -501730717404259085L;
  private String wordsFilter = "";

  public DossierLazyDataModel() {
    super(Dossier.class);
  }

  @Override
  protected Filter<Dossier> filter(Query<Dossier> query) {
    return query.allFields().containsAllWordPatterns(wordsFilter);
  }

  public String getWordsFilter() {
    return wordsFilter;
  }

  public void setWordsFilter(String wordsFilter) {
    this.wordsFilter = wordsFilter;
  }

}
