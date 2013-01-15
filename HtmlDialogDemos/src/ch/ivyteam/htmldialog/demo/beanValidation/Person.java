package ch.ivyteam.htmldialog.demo.beanValidation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Person 
{
	@Size(min=3, max=10, message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/between3And10Characters\")%>")
	@NotNull(message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/notnull\")%>")
	private String name;
	
	@Pattern(regexp="[1-9][0-9]{2}\\.[0-9]{2}\\.[1-8]([0-8][0-9]|9[012])\\.[0-9]{3}", message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/socialSecurityNumber\")%>")
	@NotNull(message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/notnull\")%>")
	private String socialSecurityNumber;
	
	@Max(value=2100, message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/smallerOrEqualThan2100\")%>")
	@Min (value=1900, message="<%=ivy.cms.co(\"/ch.ivyteam.htmldialog.demo/BeanValidationDemo/greaterOrEqualThan1900\")%>")
	private int yearOfBirth;

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getSocialSecurityNumber()
	{
		return socialSecurityNumber;
	}
	
	public void setSocialSecurityNumber(String socialSecurityNumber)
	{
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public int getYearOfBirth() 
	{
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) 
	{
		this.yearOfBirth = yearOfBirth;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder(1024);
		builder.append("Person [");
		builder.append("name=");
		builder.append(name);
		builder.append(", socialSecurityNumber=");
		builder.append(socialSecurityNumber);
		builder.append(", yearOfBirth=");
		builder.append(yearOfBirth);
		builder.append("]");
		return builder.toString();
		
	}
	
}
