package com.bea.medrec.beans;

/**
 * <p>Form bean for the physician web application.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class PhysicianBean extends BaseBean
{
  // Instance Variables
  private String firstName = "";
  private String middleName = "";
  private String lastName = "";
  private String email = "";
  private String phone = "";
  private AddressBean address = new AddressBean();

  // Constructors
  public PhysicianBean() {  }

  public PhysicianBean(String id,
                       String firstName,
                       String middleName,
                       String lastName,
                       String email,
                       String phone,
                       AddressBean address)
  {
    super.setId(id);
    this.firstName  = firstName;
    this.middleName  = middleName;
    this.lastName  = lastName;
    this.email  = email;
    this.phone  = phone;
    this.address = address;
  }

  // Getters
  public String getFirstName() { return this.firstName; }
  public String getMiddleName() { return this.middleName; }
  public String getLastName() { return this.lastName; }
  public String getEmail() { return this.email; }
  public String getPhone() { return this.phone; }
  public AddressBean getAddress() { return this.address; }

  // Setters
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setAddress(AddressBean address) { this.address = address; }

}
