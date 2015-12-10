package com.bea.medrec.value;

import java.util.Calendar;

/**
 * <p>Represents information about an XML import file.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class XMLImportFile extends BaseVO
{
  // Instance Variables
  private String filename = null;
  private String path = null;
  private Calendar date = null;
  private String size = null;

  // Constructors
  public XMLImportFile() {  }

  public XMLImportFile(String filename,
                       String path,
                       Calendar date,
                       long size)
  {
    this.filename = filename;
    this.path     = path;
    this.date     = date;
    this.size     = String.valueOf(size);
  }

  // Getters
  public String getFilename() { return (this.filename); }
  public String getPath()     { return (this.path); }
  public Calendar getDate()   { return (this.date); }
  public String getSize()     { return (this.size); }

  // Setters
  public void setFilename(String filename)  { this.filename = filename; }
  public void setPath(String path)          { this.path = path; }
  public void setDate(Calendar date)        { this.date = date;  }
  public void setSize(String size)          { this.size = size;  }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("XMLImportFile [");
    str.append("Filename: "+this.filename);
    str.append(" | Path: "+this.path);
    str.append(" | Date: "+super.getDisplayDate(this.date));
    str.append(" | Size: "+this.size);
    str.append("]");

    return str.toString();
  }
}
