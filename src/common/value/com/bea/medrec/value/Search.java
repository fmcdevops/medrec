package com.bea.medrec.value;



/**
 * <p>Represents search criteria.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class Search extends BaseVO
{
  // Instance Variables
  private String lastName;
  private String ssn;

  // Constuctors
  public Search() {  }

  public Search(String lastName, String ssn)
  {
    this.lastName   = lastName;
    this.ssn = ssn;
  }

  // Getters
  public String getLastName() { return this.lastName; }
  public String getSsn()      { return this.ssn; }

  // Setters
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setSsn(String ssn)  { this.ssn = ssn; };

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Search [");
    str.append("LastName: "+lastName);
    str.append(" | SSN: "+ssn);
    str.append("]");

    return str.toString();
  }
}
