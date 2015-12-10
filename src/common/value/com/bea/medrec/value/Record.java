package com.bea.medrec.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>Represents information about a record.  This includes
 * the record's vital signs.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class Record extends BaseVO {

  // Attributes
  private Integer patientId;
  private Integer physicianId;
  private String physicianName = "Dr. Doug Ito"; // temp under OUTER join available.
  private Calendar date;
  private String symptoms;
  private String diagnosis;
  private String notes;
  private VitalSigns vitalSigns;
  private Collection rXs;

  // Constructors
  public Record() {}

  public Record(String patientId,
               String physicianId,
               Calendar date,
               String symptoms,
               String diagnosis,
               String notes,
               VitalSigns vitalSigns,
               Collection rXs)
  {
    this.patientId    = str2Integer(patientId);
    this.physicianId  = str2Integer(physicianId);
    this.date         = date;
    this.symptoms     = symptoms;
    this.diagnosis    = diagnosis;
    this.notes        = notes;
    this.vitalSigns   = vitalSigns;
    this.rXs          = rXs;
  }

  public Record(Integer id,
                Integer patientId,
                Integer physicianId,
                Calendar date,
                String symptoms,
                String diagnosis,
                String notes,
                VitalSigns vitalSigns,
                Collection rXs)
  {
    super.setId(id);
    this.patientId    = patientId;
    this.physicianId  = physicianId;
    this.date         = date;
    this.symptoms     = symptoms;
    this.diagnosis    = diagnosis;
    this.notes        = notes;
    this.vitalSigns   = vitalSigns;
    this.rXs          = rXs;
  }

  // Getters
  public Integer getPatientId()         { return this.patientId; }
  public Integer getPhysicianId()       { return this.physicianId; }
  public String getPhysicianName()      { return this.physicianName; }
  public Calendar getDate()             { return this.date; }
  public String getSymptoms()           { return this.symptoms; }
  public String getDiagnosis()          { return this.diagnosis; }
  public String getNotes()              { return this.notes; }
  public VitalSigns getVitalSigns()     { return this.vitalSigns; }
  public Collection getPrescriptions()  { return this.rXs; }

  // Setters
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  public void setPhysicianId(Integer physicianId)
    { this.physicianId = physicianId; }
  public void setPhysicianName(String physicianName)
    { this.physicianName = physicianName; }
  public void setDate(Calendar date)          { this.date = date; }
  public void setSymptoms(String symptoms)    { this.symptoms = symptoms; }
  public void setDiagnosis(String diagnosis)  { this.diagnosis = diagnosis; }
  public void setNotes(String notes)          { this.notes = notes; }
  public void setVitalSigns(VitalSigns vitalSigns)
    { this.vitalSigns = vitalSigns; }
  public void setPrescriptions(Collection rXs) { this.rXs = rXs; }

  public void addRx(Prescription rx)
  {
    if (rXs == null) rXs = new ArrayList();
    rXs.add(rx);
  }

  // Utitily
  public String toString()
  {
    StringBuffer str = new StringBuffer();
     str.append("Record [Id: "+super.getId());
     str.append(" | PatId: "+toStr(patientId));
     str.append(" | PhysId: "+toStr(physicianId));
     str.append(" | PhysName: "+physicianName);
     str.append(" | DOB: "+(date == null ? "null" : getDisplayDate(date)));
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
    if (this.rXs != null && !this.rXs.isEmpty()) {
      str.append(" Num of Rx: "+this.rXs.size()+" |");
      Iterator itr = rXs.iterator();
      while(itr.hasNext()) {
        str.append(" "+((Prescription)itr.next()).toString());
      }
    }
    else {
      str.append("Prescriptions: [null]");
    }
    return str.toString();
  }

}
