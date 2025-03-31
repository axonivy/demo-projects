package ch.ivyteam.htmldialog.demo.output;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DialogFrameworkOptions;

import ch.ivyteam.ivy.jsf.primefaces.dialog.IvyDynamicDialog;

@ManagedBean
@ViewScoped
public class DynamicDialogDemoBean {

  public void setupAndOpen(String view) {
    DialogFrameworkOptions options = DialogFrameworkOptions
        .builder()
        .resizable(false)
        .draggable(false)
        .width("713px")
        .build();
    Map<String, List<String>> parameters = Map.of(
        "Films", List.of("Harry Potter", "Lord of the Rings", "Pirates of the Caribbean"));
    new IvyDynamicDialog().open(view, options, parameters);
  }
}
