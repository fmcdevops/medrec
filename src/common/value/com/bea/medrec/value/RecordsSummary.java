package com.bea.medrec.value;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>Encapsulates patient's medical record summary.
 * Includes collection of abbreviated records and 
 * collection of current and recent prescriptions.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class RecordsSummary implements Serializable {

  private Collection records;
  private Collection prescriptions;

  public RecordsSummary() { }

  public RecordsSummary(Collection records, Collection prescriptions)
  {
    this.records = records;
    this.prescriptions = prescriptions;
  }

  public Collection getRecords() { return this.records; }
  public Collection getPrescriptions() { return this.prescriptions; }
  
  public void setRecords(Collection records) { this.records = records; }
  public void setPrescriptions(Collection prescriptions)
    { this.prescriptions = prescriptions; }

  // Utility methods
  public int recordsSize() { return records.size(); }
  public int rxsSize() { return prescriptions.size(); }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("RecordsSummary [");
    str.append("Num of Records: "+(records == null ? "0" : String.valueOf(recordsSize())));
    str.append(" | Num of Prescriptions: "+(prescriptions == null ? "0" : String.valueOf(rxsSize())));
    str.append("]");

    return str.toString();
  }
}
