package ch.ivyteam.htmldialog.demo.input.beanValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithValidator implements ConstraintValidator<StartsWith, String> {

  private String prefix;

  @Override
  public void initialize(StartsWith constraintAnnotation) {
    prefix = constraintAnnotation.prefix().toUpperCase();
  }

  @Override
  public boolean isValid(String fieldValue,
      ConstraintValidatorContext constraintContext) {
    if (fieldValue == null) {
      return true;
    }
    return fieldValue.toUpperCase().startsWith(prefix);
  }
}
