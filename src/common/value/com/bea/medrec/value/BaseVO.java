package com.bea.medrec.value;

import java.io.Serializable;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * <p>Base class for all value objects.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class BaseVO implements Serializable {

  // Attributes
  private Integer id;

  // Constructors
  public BaseVO() {}
  public BaseVO(Integer id) { this.id = id; }
  public BaseVO(int id) { this.id = new Integer(id); }

  // Getters
  public Integer getId() { return this.id; }

  // Setters
  public void setId(Integer id) { this.id = id; }
  public void setId(int id)     { this.id = new Integer(id); }
  public void setId(String id)  { this.id = str2Integer(id); }

  // Utility
  protected String getDisplayDate(java.util.Calendar pCalendar)
  {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    if (pCalendar != null) return format.format(pCalendar.getTime());
    else return "";
  }

  protected String toStr(Object obj)
  {
    if (obj != null)
      return obj.toString();
    else
      return null;
  }

  protected Integer str2Integer(String str)
  {
    Integer tempInt = null;
    try {
      if (str != null)
        tempInt = Integer.valueOf(str);
    } catch(NumberFormatException e) { }
    return tempInt;
  }
}
