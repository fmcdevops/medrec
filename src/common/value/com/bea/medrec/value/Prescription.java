package com.bea.medrec.value;

import java.util.Calendar;

/**
 * <p>This class represents information about a prescription.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class Prescription extends BaseVO
{
  // Attributes
  private Integer patientId;
  private Integer physicianId;
  private Integer recordId;
  private Calendar datePrescribed;
  private String drug;
  private String dosage;
  private String frequency;
  private Integer refillsRemaining;
  private String instructions;

  // Constructors
  public Prescription() {}

  public Prescription(Integer patientId,
                      Integer physicianId,
                      Integer recordId,
                      Calendar datePrescribed,
                      String drug,
                      String dosage,
                      String frequency,
                      Integer refillsRemaining,
                      String instructions)
  {
    this.patientId        = patientId;
    this.physicianId      = physicianId;
    this.recordId         = recordId;
    this.datePrescribed   = datePrescribed;
    this.drug             = drug;
    this.dosage           = dosage;
    this.frequency        = frequency;
    this.refillsRemaining = refillsRemaining;
    this.instructions     = instructions;
  }

  public Prescription(String patientId,
                      String physicianId,
                      Calendar datePrescribed,
                      String drug,
                      String dosage,
                      String frequency,
                      Integer refillsRemaining,
                      String instructions)
  {
    this.patientId        = str2Integer(patientId);
    this.physicianId      = str2Integer(physicianId);
    this.datePrescribed   = datePrescribed;
    this.drug             = drug;
    this.dosage           = dosage;
    this.frequency        = frequency;
    this.refillsRemaining = refillsRemaining;
    this.instructions     = instructions;
  }

  // Getters
  public String getPatientId()        { return super.toStr(this.patientId); }
  public String getPhysicianId()      { return super.toStr(this.physicianId); }
  public String getRecordId()         { return super.toStr(this.recordId); }
  public Calendar getDatePrescribed() { return this.datePrescribed; }
  public String getDrug()             { return this.drug; }
  public String getDosage()           { return this.dosage; }
  public String getFrequency()        { return this.frequency; }
  public Integer getRefillsRemaining(){ return this.refillsRemaining; }
  public String getInstructions()     { return this.instructions; }

  // Setters
  public void setPatientId(Integer patientId) { this.patientId = patientId; }
  public void setPhysicianId(Integer physicianId)
    { this.physicianId = physicianId; }
  public void setRecordId(Integer recordId) { this.recordId = recordId; }
  public void setDatePrescribed(Calendar datePrescribed)
    { this.datePrescribed = datePrescribed; }
  public void setDrug(String drug) { this.drug = drug; }
  public void setDosage(String dosage) { this.dosage = dosage; }
  public void setFrequency(String frequency) { this.frequency = frequency; }
  public void setRefillsRemaining(Integer refillsRemaining)
    { this.refillsRemaining = refillsRemaining; }
  public void setInstructions(String instructions)
    { this.instructions = instructions; }

  // Utility
  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("Rx [Id: "+super.getId());
    str.append(" | PatId: "+getPatientId());
    str.append(" | PhysId: "+getPhysicianId());
    str.append(" | RecId: "+getRecordId());
    str.append(" | Calendar: "+(datePrescribed == null ? "null" : getDisplayDate(datePrescribed)));
    str.append(" | Drug: "+drug);
    str.append(" | Dosage: "+dosage);
    str.append(" | Freq: "+frequency);
    str.append(" | Refills: "+refillsRemaining);
    str.append(" | Instructions: "+instructions);
    str.append("]");
    return str.toString();
  }

}

