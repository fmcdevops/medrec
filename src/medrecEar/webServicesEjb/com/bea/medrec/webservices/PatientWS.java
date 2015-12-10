package com.bea.medrec.webservices;

/**
 * Represents information about a record.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class PatientWS
{
  private Integer id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String dob;
  private String gender;
  private String ssn;
  private String phone;
  private String email;
  private AddressWS address;

  public PatientWS() {}

  public PatientWS(Integer id,
                   String firstName,
                   String middleName,
                   String lastName,
                   String dob,
                   String gender,
                   String ssn,
                   String phone,
                   String email,
                   AddressWS address)
  {
    this.id      = id;
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

  public Integer getId()        { return this.id; }
  public String getFirstName()  { return this.firstName; }
  public String getMiddleName() { return this.middleName; }
  public String getLastName()   { return this.lastName; }
  public String getDateOfBirth()  { return this.dob; }
  public String getGender()     { return this.gender; }
  public String getSsn()        { return this.ssn; }
  public String getPhone()      { return this.phone; }
  public String getEmail()      { return this.email; }
  public AddressWS getAddressWS() { return this.address; }

  public void setId(Integer id)           { this.id = id; }
  public void setFirstName(String firstName)  { this.firstName = firstName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  public void setLastName(String lastName)   { this.lastName = lastName; }
  public void setDateOfBirth(String dob)  { this.dob = dob; }
  public void setGender(String gender)    { this.gender = gender; }
  public void setSsn(String ssn)          { this.ssn = ssn; }
  public void setPhone(String phone)      { this.phone = phone; }
  public void setEmail(String email)      { this.email = email; }
  public void setAddressWS(AddressWS address) { this.address = address; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Patient[Id: "+id);
    str.append(" | Name: "+this.firstName);
    str.append(" | "+this.middleName);
    str.append(" | "+this.lastName);
    str.append(" | DOB: "+this.dob);
    str.append(" | Gender: "+this.gender);
    str.append(" | SSN: "+this.ssn);
    str.append(" | Phone: "+this.phone);
    str.append(" | Email: "+this.email);
    str.append(" | ");
    str.append(((this.address == null) ? "AddressWS: null" : this.address.toString()));
    str.append("]");

    return str.toString();
  }
}
