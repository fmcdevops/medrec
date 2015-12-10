package com.bea.medrec.webservices;

/**
 * <p>This class represents information about a address.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class AddressWS
{
  private Integer id;
  private String streetName1;
  private String streetName2;
  private String city;
  private String state;
  private String zipCode;
  private String country;

  public AddressWS() {}

  public AddressWS(Integer id,
                   String streetName1,
                   String streetName2,
                   String city,
                   String state,
                   String zipCode,
                   String country)
  {
    this.id           = id;
    this.streetName1  = streetName1;
    this.streetName2  = streetName2;
    this.city         = city;
    this.state        = state;
    this.zipCode      = zipCode;
    this.country      = country;
  }

  public Integer getId()         { return this.id; }
  public String getStreetName1() { return this.streetName1; }
  public String getStreetName2() { return this.streetName2; }
  public String getCity()        { return this.city; }
  public String getState()       { return this.state; }
  public String getZipCode()     { return this.zipCode; }
  public String getCountry()     { return this.country; }

  public void setId(Integer id)                  { this.id = id; }
  public void setStreetName1(String streetName1) { this.streetName1 = streetName1; }
  public void setStreetName2(String streetName2) { this.streetName2 = streetName2; }
  public void setCity(String city)               { this.city = city; }
  public void setState(String state)             { this.state = state; }
  public void setZipCode(String zipCode)         { this.zipCode = zipCode; }
  public void setCountry(String country)         { this.country = country; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("AddressWS[Id: "+id);
    str.append(" | StreeName1: " + streetName1);
    str.append(" | StreetName2: " + streetName2);
    str.append(" | City: " + city);
    str.append(" | State: " + state);
    str.append(" | ZipCode: " + zipCode);
    str.append(" | Country: " + country);
    str.append( "]");

    return str.toString();
  }
}
