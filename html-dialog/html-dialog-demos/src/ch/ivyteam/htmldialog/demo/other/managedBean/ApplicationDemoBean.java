package ch.ivyteam.htmldialog.demo.other.managedBean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * This simple bean is accessible in all views of the project.
 *
 * <h4>Naming</h4>
 * <p>
 * A declared ManagedBean can be bound to a view value with the uncapitalized
 * class name. So this class will be bound to the name "applicationDemoBean".
 * <br/>
 * Optionally it is possible to set an explicit ManagedBean name within the
 * annotation parameter <code>name</code>.<br/>
 * E.g. <br/>
 * <code>@java.faces.ManagedBean(name="myCustomName")</code>
 * </p>
 *
 * <h4>Scopes</h4> MangedBeans can be annotated with a scope. The scope defines
 * the lifecycle of a ManageBean. Currently three scopes for ivy project beans
 * are supported:
 * <ul>
 * <li>@ApplicationScoped: Lives as long as an ivy application. They are only
 * destroyed if the server shut's down or if the declaring project (PMV) is
 * deleted.</li>
 * <li>@SessionScoped: Lives as long as the browser session.</li>
 * <li>@RequestScoped: Lives as long as the view request. This is the default
 * Scope which is assumed if no scope annotation was declared.</li>
 * </ul>
 * The instance here will be shared over the whole ivy application as the bean
 * is annotated with @ApplicationScoped.
 */
@Named
@ApplicationScoped
public class ApplicationDemoBean {

  private String description;

  /**
   * This public getter can be bound trough an EL-expression:
   * <code>#{applicationDemoBean.description}</code>. </br>
   * E.g. within a label:
   * <code>&lt;h:outputLabel value="#{applicationDemoBean.description}" /&gt;</code>
   */
  public String getDescription() {
    return description;
  }

  /**
   * This public setter can be bound trough an EL-expression:
   * <code>#{applicationDemoBean.description}</code>. </br>
   * E.g. within an input text:
   * <code>&lt;h:inputText value="#{applicationDemoBean.description}" /&gt;</code>
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
