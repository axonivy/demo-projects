package ch.ivyteam.htmldialog.demo.component;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
    return Ivy.security().users().findById(arg2);
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
    if (arg2 instanceof IUser) {
      return ((IUser) arg2).getSecurityMemberId();
    }
    return null;
  }
}
