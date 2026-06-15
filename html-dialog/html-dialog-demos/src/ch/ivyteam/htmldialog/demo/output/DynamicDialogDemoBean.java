package ch.ivyteam.htmldialog.demo.output;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import org.primefaces.model.DialogFrameworkOptions;

import ch.ivyteam.ivy.jsf.primefaces.dialog.IvyDynamicDialog;

@Named
@ViewScoped
public class DynamicDialogDemoBean implements Serializable {

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
