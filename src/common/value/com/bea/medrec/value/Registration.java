package com.bea.medrec.value;

/**
 * <p>Represents information about a registration which is
 * made up of patient data and user (username and password) date.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class Registration extends BaseVO {

  private Patient patient = null;
  private User user = null;

  // Constuctors
  public Registration() {}

  public Registration(Patient pPatient, User pUser) {
    this.patient = pPatient;
    this.user = pUser;
  }

  // Getters
  public Patient getPatient() { return this.patient; }
  public User getUser() { return this.user; }

  // Setters
  public void setPatient(Patient pPatient) { this.patient = pPatient; }
  public void setUser(User pUser) { this.user = pUser; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Registration["+user.toString());
    str.append(" | "+patient.toStringLite());
    str.append("]");
    return str.toString();
  }
}
