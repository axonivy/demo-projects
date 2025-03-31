package rule.engine.demo.enums;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class MemberTypeBean {

  public MemberType[] getMemberTypes() {
    return MemberType.values();
  }
}
