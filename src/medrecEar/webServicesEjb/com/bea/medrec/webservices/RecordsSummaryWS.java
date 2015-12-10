package com.bea.medrec.webservices;

/**
 * Encapsulates patient's medical record summary.  
 * Includes collection of abbreviated records and 
 * collection of current and recent prescriptions.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class RecordsSummaryWS
{
  private RecordWS[] records;
  private PrescriptionWS[] prescriptions;

  public RecordsSummaryWS() { }

  public RecordsSummaryWS(RecordWS[] records, PrescriptionWS[] prescriptions)
  {
    this.records = records;
    this.prescriptions = prescriptions;
  }

  public RecordWS[] getRecords() { return this.records; }
  public PrescriptionWS[] getPrescriptions() { return this.prescriptions; }
  
  public void setRecords(RecordWS[] records)
  {
    this.records = records;
  }

  public void setPrescriptions(PrescriptionWS[] prescriptions)
  {
    this.prescriptions = prescriptions;
  }

  // Utility methods
  public int recordsLen() { return records.length; }
  public int rxsLen()     { return prescriptions.length; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("RecordsSummaryWS [");
    str.append("Num of Records: "+(records == null ? "0" : String.valueOf(recordsLen())));
    str.append(" | ");
    str.append("Num of Rxs: "+(prescriptions == null ? "0" : String.valueOf(rxsLen())));
    str.append("]");

    return str.toString();
  }
}
