package ch.ivyteam.htmldialog.demo.input.facesValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

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
