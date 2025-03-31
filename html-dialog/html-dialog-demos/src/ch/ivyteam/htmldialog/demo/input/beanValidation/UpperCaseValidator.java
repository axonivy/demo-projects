package ch.ivyteam.htmldialog.demo.input.beanValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCase, String> {

  @Override
  public void initialize(UpperCase constraintAnnotation) {}

  @Override
  public boolean isValid(String fieldValue,
      ConstraintValidatorContext constraintContext) {
    if (fieldValue == null) {
      return true;
    }
    return fieldValue.equals(fieldValue.toUpperCase());
  }
}
