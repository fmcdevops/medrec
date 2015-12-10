package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.XMLImportFile;
import java.io.Serializable;

/**
 * <p>Bean to hold data about XML files</p>.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class XMLImportBean implements Serializable
{
  // Instance Variables
  private String filename = "";
  private String path = "";
  private String date = "";
  private String size = "";

  // Constructors
  public XMLImportBean() {  }

  public XMLImportBean(XMLImportFile xmlImport)
  {
    String displayDate = MedRecWebAppUtils.getDisplayDate(xmlImport.getDate());
    this.filename = xmlImport.getFilename();
    this.path     = xmlImport.getPath();
    this.date     = displayDate;
    this.size     = xmlImport.getSize();
  }

  public XMLImportBean(String filename,
                       String path,
                       String date,
                       String size)
  {
    this.filename = filename;
    this.path     = path;
    this.date     = date;
    this.size     = String.valueOf(size);
  }

  // Getters
  public String getFilename() { return (this.filename); }
  public String getPath()     { return (this.path); }
  public String getDate()     { return (this.date); }
  public String getSize()     { return (this.size); }

  // Setters
  public void setFilename(String filename)  { this.filename = filename; }
  public void setPath(String path)          { this.path = path; }
  public void setDate(String date)          { this.date = date;  }
  public void setSize(String size)          { this.size = size;  }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("XMLImportBean [");
    str.append("Filename: "+this.filename);
    str.append(" | Path: "+this.path);
    str.append(" | Date: "+this.date);
    str.append(" | Size: "+this.size);
    str.append("]");

    return str.toString();
  }
}
