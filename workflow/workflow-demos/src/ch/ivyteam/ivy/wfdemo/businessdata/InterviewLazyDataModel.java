package ch.ivyteam.ivy.wfdemo.businessdata;

import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Query;
import workflow.businesscasedata.Interview;

public class InterviewLazyDataModel extends AbstractBusinessDataLazyDataModel<Interview> {

  private static final long serialVersionUID = -501730717404259085L;
  private String wordsFilter = "";

  public InterviewLazyDataModel() {
    super(Interview.class);
  }

  @Override
  protected Filter<Interview> filter(Query<Interview> query) {
    return query.allFields().containsAllWordPatterns(wordsFilter);
  }

  public String getWordsFilter() {
    return wordsFilter;
  }

  public void setWordsFilter(String wordsFilter) {
    this.wordsFilter = wordsFilter;
  }

}
