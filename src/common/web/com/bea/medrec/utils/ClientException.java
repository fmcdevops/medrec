package com.bea.medrec.utils;

/**
 * <p>Custom client exception class.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class ClientException extends Exception
{
  // Instance variables
  private String link;

  // Constructors
  public ClientException() { }
  public ClientException(String errMsg) { super(errMsg); }
  public ClientException(Throwable cause) { super(cause); }
  public ClientException(Throwable cause, String link) {
    super(cause);
    this.link = link;
  }
  public ClientException(String errMsg, Throwable cause) {
    super(errMsg, cause);
  }
  public ClientException(String errMsg, String link) {
    super(errMsg);
    this.link = link;
  }

  // Getters
  public String getLink() { return this.link; }


  // Setters
  public void setLink(String link) { this.link = link;  }

}
