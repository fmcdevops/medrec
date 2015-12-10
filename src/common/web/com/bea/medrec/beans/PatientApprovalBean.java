package com.bea.medrec.beans;

/**
 * <p>Form bean for patient approval</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class PatientApprovalBean extends BaseBean
{
  // Instance Variables
  private String name = "";

  public PatientApprovalBean() { }

  public PatientApprovalBean(Integer pId, String pLastName, String pFirstName) {
    super.setId(pId.toString());
    this.setName(pLastName, pFirstName);
  }

  // Getters
  public String getName() { return this.name; }

  // Setters
  public void setName(String pLastName, String pFirstName) {
    this.name = pLastName+", "+pFirstName;
  }
}
