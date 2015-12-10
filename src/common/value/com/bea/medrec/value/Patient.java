package com.bea.medrec.value;

import java.util.Calendar;

/**
 * <p>This class represents information about a patient.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class Patient extends BaseVO {

  // Attributes
  private String firstName;
  private String middleName;
  private String lastName;
  private Calendar dob;
  private String gender;
  private String ssn;
  private String phone;
  private String email;
  private Address address;

  // Constructors
  public Patient() {}

  public Patient(Integer id,
                 String firstName,
                 String middleName,
                 String lastName,
                 Calendar dob,
                 String gender,
                 String ssn,
                 String phone,
                 String email,
                 Address address)
  {
    super.setId(id);
    this.firstName   = firstName;
    this.middleName   = middleName;
    this.lastName   = lastName;
    this.dob     = dob;
    this.gender  = gender;
    this.ssn     = ssn;
    this.phone   = phone;
    this.email   = email;
    this.address = address;
  }

  public Patient(String id,
                 String firstName,
                 String middleName,
                 String lastName,
                 Calendar dob,
                 String gender,
                 String ssn,
                 String phone,
                 String email,
                 Address address)
  {
    super.setId(id);
    this.firstName   = firstName;
    this.middleName   = middleName;
    this.lastName   = lastName;
    this.dob     = dob;
    this.gender  = gender;
    this.ssn     = ssn;
    this.phone   = phone;
    this.email   = email;
    this.address = address;
  }

  // Getters
  public String getFirstName()      { return this.firstName; }
  public String getMiddleName()     { return this.middleName; }
  public String getLastName()       { return this.lastName; }
  public Calendar getDateOfBirth()  { return this.dob; }
  public String getDateOfBirthString()
  { return (dob == null) ? "null" : getDisplayDate(dob); }
  public String getGender()         { return this.gender; }
  public String getSsn()            { return this.ssn; }
  public String getPhone()          { return this.phone; }
  public String getEmail()          { return this.email; }
  public Address getAddress()       { return this.address; }

  // Setters
  public void setFirstName(String firstName)  { this.firstName = firstName; }
  public void setMiddleName(String middleName){ this.middleName = middleName; }
  public void setLastName(String lastName)    { this.lastName = lastName; }
  public void setDateOfBirth(Calendar dob)    { this.dob = dob; }
  public void setGender(String gender)        { this.gender = gender; }
  public void setSsn(String ssn)              { this.ssn = ssn; }
  public void setPhone(String phone)          { this.phone = phone; }
  public void setEmail(String email)          { this.email = email; }
  public void setAddress(Address address)     { this.address = address; }

  // Utility
  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Patient[Id: "+super.getId());
    str.append(" | Name: "+firstName);
    str.append(" "+middleName);
    str.append(" "+lastName);
    str.append(" | DOB: "+getDateOfBirthString());
    str.append(" | Gender: " +this.gender);
    str.append(" | SSN: "+ssn);
    str.append(" | Phone: "+phone);
    str.append(" | Email: "+email);
    str.append(" | "+((address == null) ? "Address: null" : address.toString()));
    str.append("]");
    return str.toString();
  }

  public String toStringLite()
  {
    StringBuffer str = new StringBuffer();
    str.append("Patient[Id: "+super.getId());
    str.append(" | Name: "+firstName);
    str.append(" "+middleName);
    str.append(" "+lastName);
    str.append(" | SSN: "+ssn);
    str.append("]");
    return str.toString();
  }
}
