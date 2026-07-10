package ch.ivyteam.htmldialog.demo.input.beanValidation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {

  @Size(min = 3, max = 10, message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/between3And10Characters\")%>")
  @NotNull(message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/notnull\")%>")
  private String name;
  @Pattern(regexp = "[1-9][0-9]{2}\\.[0-9]{2}\\.[1-8]([0-8][0-9]|9[012])\\.[0-9]{3}", message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/socialSecurityNumber\")%>")
  @NotNull(message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/notnull\")%>")
  private String socialSecurityNumber;
  @Max(value = 2100, message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/smallerOrEqualThan2100\")%>")
  @Min(value = 1900, message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/greaterOrEqualThan1900\")%>")
  @NotNull(message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/greaterOrEqualThan1900\")%>")
  private Integer yearOfBirth;
  @LicensePlate(message = "<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/licensePlate\")%>")
  private String vehicleLicense;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  public void setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
  }

  public Integer getYearOfBirth() {
    return yearOfBirth;
  }

  public void setYearOfBirth(Integer yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  public String getVehicleLicense() {
    return vehicleLicense;
  }

  public void setVehicleLicense(String vehicleLicense) {
    this.vehicleLicense = vehicleLicense;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(1024);
    builder.append("Person [");
    builder.append("name=");
    builder.append(name);
    builder.append(", socialSecurityNumber=");
    builder.append(socialSecurityNumber);
    builder.append(", yearOfBirth=");
    builder.append(yearOfBirth);
    builder.append(", Licence=");
    builder.append(vehicleLicense);
    builder.append("]");
    return builder.toString();
  }
}
