package com.bea.medrec.beans;

import java.io.Serializable;

/**
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class ErrorBean implements Serializable
{
  // Instance Variables
  private String errMessage = "";
  private String link       = "";

  // Constuctors
  public ErrorBean() {  }

  public ErrorBean(String errMessage,
                   String link)
  {
    this.errMessage = errMessage;
    this.link       = link;
  }

  // Getters
  public String getErrMessage() { return this.errMessage; }
  public String getLink()       { return this.link; }

  // Setters
  public void setErrMessage(String errMessage){ this.errMessage = errMessage; }
  public void setLink(String link)            { this.link       = link; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Error [");
    str.append(" ErrMsg: "+errMessage);
    str.append(" | Link: "+link);
    str.append("]");

    return str.toString();
  }

}
