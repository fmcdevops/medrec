package com.bea.medrec.webservices;

/**
 * Represents information about a record.  This includes
 * the record's vital signs.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class RecordWS
{
  private Integer id;
  private Integer patientId;
  private Integer physicianId;
  private String date;
  private String symptoms;
  private String diagnosis;
  private String notes;
  private VitalSignsWS vitalSigns;
  private PrescriptionWS[] rXs;

  public RecordWS() {}

  public RecordWS(Integer id,
                  Integer patientId,
                  Integer physicianId,
                  String date,
                  String symptoms,
                  String diagnosis,
                  String notes,
                  VitalSignsWS vitalSigns,
                  PrescriptionWS[] prescriptions)
  {
    this.id           = id;
    this.patientId    = patientId;
    this.physicianId  = physicianId;
    this.date         = date;
    this.symptoms     = symptoms;
    this.diagnosis    = diagnosis;
    this.notes        = notes;
    this.vitalSigns   = vitalSigns;
    this.rXs          = prescriptions;
  }

  public RecordWS(Integer id,
                  String date,
                  String symptoms,
                  String diagnosis)
  {
    this.id           = id;
    this.date         = date;
    this.symptoms     = symptoms;
    this.diagnosis    = diagnosis;
  }

  public Integer getId()                    { return this.id; }
  public Integer getPatientId()             { return this.patientId; }
  public Integer getPhysicianId()           { return this.physicianId; }
  public String getDate()                   { return this.date; }
  public String getSymptoms()               { return this.symptoms; }
  public String getDiagnosis()              { return this.diagnosis; }
  public String getNotes()                  { return this.notes; }
  public VitalSignsWS getVitalSigns()       { return this.vitalSigns; }
  public PrescriptionWS[] getPrescriptions(){ return this.rXs; }

  public void setId(Integer id)                   { this.id = id; }
  public void setPatientId(Integer patientId)
    { this.patientId = patientId; }
  public void setPhysicianId(Integer physicianId)
    { this.physicianId = physicianId; }
  public void setDate(String date)                { this.date = date; }
  public void setSymptoms(String symptoms)        { this.symptoms = symptoms; }
  public void setDiagnosis(String diagnosis)      { this.diagnosis = diagnosis; }
  public void setNotes(String notes)              { this.notes = notes; }
  public void setVitalSigns(VitalSignsWS vitalSigns)
    { this.vitalSigns = vitalSigns; }
  public void setPrescriptions(PrescriptionWS[] prescriptions)
    { this.rXs = prescriptions; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("RecordWS [Id: "+id);
    str.append(" | PatId: "+toStr(patientId));
    str.append(" | PhysId: "+toStr(physicianId));
    str.append(" | DOB: "+date);
    str.append(" | Syms: "+symptoms);
    str.append(" | Diag: "+diagnosis);
    str.append(" | Notes: "+notes);
    str.append(" | "+(vitalSigns == null ? "Vitals: [null]" : vitalSigns.toString()));
    str.append(" | "+rXsToString());
    str.append("]");

    return str.toString();
  }

  private String rXsToString()
  {
    StringBuffer str = new StringBuffer();
    if (rXs != null && rXs.length > 0) {
      str.append(" Num of Rx: "+rXs.length+" |");
      for (int i=0; i<rXs.length; i++) {
        str.append(" "+rXs[i++].toString());
      }
    }
    else {
      str.append("Prescriptions: [null]");
    }
    return str.toString();
  }

 /*
  * <p></p>
  */
  private String toStr(Object obj)
  {
    if (obj != null)
      return obj.toString();
    else
      return null;
  }

}
