package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecMessageProperties;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.Address;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

/**
 * <p>Form bean for addresses.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>streetName1</b> - Entered street name 1 value
 * <li><b>streetName2</b> - Entered street name 2 value
 * <li><b>city</b> - Entered city value
 * <li><b>state</b> - Entered state value
 * <li><b>zipCode</b> - Entered zip code value
 * <li><b>country</b> - Entered country value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class AddressBean extends BaseBean {
  private static Logger logger = Logger.getLogger(AddressBean.class.getName());

  // Instance Variables
  private String streetName1 = null;
  private String streetName2 = null;
  private String city = null;
  private String state = null;
  private String zipCode = null;
  private String country = null;

  // Constructors
  public AddressBean() {
  }

  public AddressBean(Address address) {
    super.setId(address.getId());
    this.streetName1 = address.getStreetName1();
    this.streetName2 = address.getStreetName2();
    this.city = address.getCity();
    this.state = address.getState();
    this.zipCode = address.getZipCode();
    this.country = address.getCountry();
  }

  public AddressBean(String id,
                     String streetName1,
                     String streetName2,
                     String city,
                     String state,
                     String zipCode,
                     String country) {
    super.setId(id);
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
  }

  public AddressBean(Integer id,
                     String streetName1,
                     String streetName2,
                     String city,
                     String state,
                     String zipCode,
                     String country) {
    super.setId(id);
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
  }

  public String getStreetName1() {
    return this.streetName1;
  }

  public String getStreetName2() {
    return this.streetName2;
  }

  public String getCity() {
    return this.city;
  }

  public String getState() {
    return this.state;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public String getCountry() {
    return this.country;
  }

  public void setStreetName1(String streetName1) {
    this.streetName1 = MedRecWebAppUtils.cleanParam(streetName1);
  }

  public void setStreetName2(String streetName2) {
    this.streetName2 = MedRecWebAppUtils.cleanParam(streetName2);
  }

  public void setCity(String city) {
    this.city = MedRecWebAppUtils.cleanParam(city);
  }

  public void setState(String state) {
    this.state = MedRecWebAppUtils.cleanParam(state);
  }

  public void setZipCode(String zipCode) {
    this.zipCode = MedRecWebAppUtils.cleanParam(zipCode);
  }

  public void setCountry(String country) {
    this.country = MedRecWebAppUtils.cleanParam(country);
  }

  // Public Methods
  public void reset() {
    this.streetName1 = "";
    this.streetName2 = "";
    this.city = "";
    this.state = "";
    this.zipCode = "";
    this.country = "";
  }

  /**
   * <p>Converts address presentation bean to address value object.</p>
   *
   * @return Address
   */
  public Address toAddress() {
    return new Address(this.getId(),
        this.getStreetName1(),
        this.getStreetName2(),
        this.getCity(),
        this.getState(),
        this.getZipCode(),
        this.getCountry());
  }


  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("Address[Id: " + super.getId());
    str.append(" | StreeName1: " + streetName1);
    str.append(" | StreetName2: " + streetName2);
    str.append(" | City: " + city);
    str.append(" | State: " + state);
    str.append(" | ZipCode: " + zipCode);
    str.append(" | Country: " + country);
    str.append("]");

    return str.toString();
  }
}
