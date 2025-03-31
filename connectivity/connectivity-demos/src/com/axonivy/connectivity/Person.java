package com.axonivy.connectivity;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "person")
public class Person {
  private UUID id;
  private java.lang.String firstname;
  private java.lang.String lastname;

  @XmlElement
  public UUID getId() {
    return id;
  }

  public void setId(UUID _id) {
    id = _id;
  }

  @XmlElement
  public java.lang.String getFirstname() {
    return firstname;
  }

  public void setFirstname(java.lang.String _firstname) {
    firstname = _firstname;
  }

  @XmlElement
  public java.lang.String getLastname() {
    return lastname;
  }

  public void setLastname(java.lang.String _lastname) {
    lastname = _lastname;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Person other = (Person) obj;
    return new EqualsBuilder()
        .append(id, other.id)
        .append(firstname, other.firstname)
        .append(lastname, other.lastname)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(id)
        .append(firstname)
        .append(lastname)
        .toHashCode();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
