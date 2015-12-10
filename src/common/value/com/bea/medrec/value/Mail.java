package com.bea.medrec.value;

/**
 * <p>This class represents information in a mail message.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class Mail extends BaseVO {

  private String message = null;
  private String from = null;
  private String to = null;
  private String subject = null;

  public void Mail() { }

  public void Mail(String pMessage,
                   String pFrom,
                   String pTo,
                   String pSubject)
  {
    this.message  = pMessage;
    this.from     = pFrom;
    this.to       = pTo;
    this.subject  = pSubject;
  }

  public String getMessage()  { return this.message; }
  public String getFrom()     { return this.from; }
  public String getTo()       { return this.to; }
  public String getSubject()  { return this.subject; }
  public void setMessage(String pMessage) { this.message = pMessage; }
  public void setFrom(String pFrom)       { this.from = pFrom; }
  public void setTo(String pTo)           { this.to = pTo; }
  public void setSubject(String pSubject) { this.subject = pSubject; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Mail[To: "+to);
    str.append(" | From: "+from);
    str.append(" | Message: "+message);
    str.append(" | Subject: "+subject);
    str.append( "]");

    return str.toString();
  }
}
