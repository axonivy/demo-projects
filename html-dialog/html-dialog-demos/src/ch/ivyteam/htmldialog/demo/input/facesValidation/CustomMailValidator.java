package ch.ivyteam.htmldialog.demo.input.facesValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import ch.ivyteam.ivy.cm.IContentManagementSystem;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator("ch.ivyteam.CustomMailValidator")
public class CustomMailValidator implements Validator {

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
      "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
      "(\\.[A-Za-z]{2,})$";
  private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    Matcher matcher = pattern.matcher(value.toString());
    if (!matcher.matches()) {
      IContentManagementSystem cms = Ivy.cms();
      FacesMessage msg = new FacesMessage(
          cms.co("/ch.ivyteam.htmldialog.demo/FormDemo/Validation/mailValidationDetail"),
          cms.co("/ch.ivyteam.htmldialog.demo/FormDemo/Validation/mailValidationSummary"));
      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
      throw new ValidatorException(msg);
    }
  }
}
