package com.bea.medrec.webservices;

/**
 * This class represents information about a Prescription.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class PrescriptionWS
{
  private Integer id;
  private Integer patientId;
  private Integer physicianId;
  private Integer recordId;
  private String datePrescribed;
  private String drug;
  private String dosage;
  private String frequency;
  private Integer refillsRemaining;
  private String instructions;

  public PrescriptionWS() {}

  public PrescriptionWS(Integer id,
                        Integer patientId,
                        Integer physicianId,
                        Integer recordId,
                        String datePrescribed,
                        String drug,
                        String dosage,
                        String frequency,
                        Integer refillsRemaining,
                        String instructions)
  {
    this.id               = id;
    this.patientId        = patientId;
    this.physicianId      = physicianId;
    this.recordId         = recordId;
    this.datePrescribed   = datePrescribed;
    this.drug             = drug;
    this.dosage           = dosage;
    this.frequency        = frequency;
    this.refillsRemaining = refillsRemaining;
  }

  public Integer getId()              { return this.id; }
  public Integer getPatientId()       { return this.patientId; }
  public Integer getPhysicianId()     { return this.physicianId; }
  public Integer getRecordId()        { return this.recordId; }
  public String getDatePrescribed()   { return this.datePrescribed; }
  public String getDrug()             { return this.drug; }
  public String getDosage()           { return this.dosage; }
  public String getFrequency()        { return this.frequency; }
  public Integer getRefillsRemaining(){ return this.refillsRemaining; }
  public String getInstructions()     { return this.instructions; }

  public void setId(Integer id)                   { this.id = id; }
  public void setPatientId(Integer patientId)     { this.patientId = patientId; }
  public void setPhysicianId(Integer physicianId) { this.physicianId = physicianId; }
  public void setRecordId(Integer recordId)       { this.recordId = recordId; }
  public void setDatePrescribed(String datePrescribed) { this.datePrescribed = datePrescribed; }
  public void setDrug(String drug)                { this.drug = drug; }
  public void setDosage(String dosage)            { this.dosage = dosage; }
  public void setFrequency(String frequency)      { this.frequency = frequency; }
  public void setRefillsRemaining(Integer refillsRemaining) { this.refillsRemaining = refillsRemaining; }
  public void setInstructions(String instructions){ this.instructions = instructions; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("RxWS [Id: "+id);
    str.append(" | PatId: "+toStr(patientId));
    str.append(" | PhysId: "+toStr(physicianId));
    str.append(" | RecId: "+toStr(recordId));
    str.append(" | Date: "+datePrescribed);
    str.append(" | Drug: "+drug);
    str.append(" | Dosage: "+dosage);
    str.append(" | Freq: "+frequency);
    str.append(" | Refills: "+refillsRemaining);
    str.append(" | Instructions: "+instructions);
    str.append("]");
    
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
