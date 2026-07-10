package ch.ivyteam.htmldialog.demo.component;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter<IUser> {

  @Override
  public IUser getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
    return Ivy.security().users().findById(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, IUser value) throws ConverterException {
    return value.getSecurityMemberId();
  }
}
