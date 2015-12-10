package com.bea.medrec.value;

import java.io.Serializable;

/**
 * <p>Represents information about a user including
 * username and password.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class User implements Serializable {
  private String username;
  private String password;
  private String status;

  // Constructors
  public User() { }

  public User(String pUsername,
              String pPassword,
              String pStatus) {
    this.username = pUsername;
    this.password = pPassword;
    this.status   = pStatus;
  }

  public User(String pUsername) { this.username = pUsername; }

  // Getters
  public String getUsername() { return this.username; }
  public String getPassword() { return this.password;  }
  public String getStatus()   { return this.status; }

  // Setters
  public void setUsername(String pUsername) { this.username = pUsername; }
  public void setPassword(String pPassword) { this.password = pPassword; }
  public void setStatus(String pStatus)     { this.status = pStatus; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("User[Username: "+username);
    str.append(" | Password: "+printPassword());
    str.append(" | Status: "+ status);
    str.append("]");
    return str.toString();
  }

  // Utility
  private String printPassword()
  {
    String pwd = "";
    if (password != null) for (int i=0; i<password.length(); i++) pwd += "*";
    return pwd;
  }
}
