package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.Prescription;
import java.util.Calendar;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Form bean for vital signs.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>drug</b> - Entered drug value
 * <li><b>dosage</b> - Entered dosage value
 * <li><b>frequency </b> - Entered frequency value
 * <li><b>refillsRemaining</b> - Entered refills remaining value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class PrescriptionBean extends BaseBean {

  // Attributes
  private String patientId = "";
  private String physicianId = "";
  private String recordId = "";
  private String datePrescribed = "";
  private String drug = "";
  private String dosage = "";
  private String frequency = "";
  private String refillsRemaining = "";
  private String instructions = "";

  // Constructors
  public PrescriptionBean() {
  }

  public PrescriptionBean(Prescription rx) {
    String displayDate =
        MedRecWebAppUtils.getDisplayDate(rx.getDatePrescribed());
    super.setId(rx.getId());
    this.patientId = rx.getPatientId();
    this.physicianId = rx.getPhysicianId();
    this.recordId = rx.getRecordId();
    this.datePrescribed = displayDate;
    this.drug = rx.getDrug();
    this.dosage = rx.getDosage();
    this.frequency = rx.getFrequency();
    this.refillsRemaining = toStr(rx.getRefillsRemaining());
    this.instructions = rx.getInstructions();
  }

  public PrescriptionBean(String id,
                          String patientId,
                          String physicianId,
                          String recordId,
                          String datePrescribed,
                          String drug,
                          String dosage,
                          String frequency,
                          String refillsRemaining,
                          String instructions) {
    super.setId(id);
    this.patientId = patientId;
    this.physicianId = physicianId;
    this.recordId = recordId;
    this.datePrescribed = datePrescribed;
    this.drug = drug;
    this.dosage = dosage;
    this.frequency = frequency;
    this.refillsRemaining = refillsRemaining;
    this.instructions = instructions;
  }

  public PrescriptionBean(Integer id,
                          String patientId,
                          String physicianId,
                          String recordId,
                          String datePrescribed,
                          String drug,
                          String dosage,
                          String frequency,
                          Integer refillsRemaining,
                          String instructions) {
    super.setId(id);
    this.patientId = patientId;
    this.physicianId = physicianId;
    this.recordId = recordId;
    this.datePrescribed = datePrescribed;
    this.drug = drug;
    this.dosage = dosage;
    this.frequency = frequency;
    this.refillsRemaining = toStr(refillsRemaining);
    this.instructions = instructions;
  }

  public PrescriptionBean(String patientId,
                          String physicianId,
                          String recordId,
                          String datePrescribed,
                          String drug,
                          String dosage,
                          String frequency,
                          String refillsRemaining,
                          String instructions) {
    this.patientId = patientId;
    this.physicianId = physicianId;
    this.recordId = recordId;
    this.datePrescribed = datePrescribed;
    this.drug = drug;
    this.dosage = dosage;
    this.frequency = frequency;
    this.refillsRemaining = refillsRemaining;
    this.instructions = instructions;
  }

  // Getters
  public String getPatientId() {
    return this.patientId;
  }

  public String getPhysicianId() {
    return this.physicianId;
  }

  public String getRecordId() {
    return this.recordId;
  }

  public String getDatePrescribed() {
    return this.datePrescribed;
  }

  public String getDrug() {
    return this.drug;
  }

  public String getDosage() {
    return this.dosage;
  }

  public String getFrequency() {
    return this.frequency;
  }

  public String getRefillsRemaining() {
    return this.refillsRemaining;
  }

  public String getInstructions() {
    return this.instructions;
  }

  // Setters
  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public void setPhysicianId(String physicianId) {
    this.physicianId = physicianId;
  }

  public void setRecordId(String recordId) {
    this.recordId = recordId;
  }

  public void setDatePrescribed(String datePrescribed) {
    this.datePrescribed = datePrescribed;
  }

  public void setDrug(String drug) {
    this.drug = drug;
  }

  public void setDosage(String dosage) {
    this.dosage = dosage;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public void setRefillsRemaining(String refillsRemaining) {
    this.refillsRemaining = refillsRemaining;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  // Public Methods
  public void reset() {
    this.patientId = "";
    this.physicianId = "";
    this.recordId = "";
    this.datePrescribed = "";
    this.drug = "";
    this.dosage = "";
    this.frequency = "";
    this.refillsRemaining = "";
    this.instructions = "";
  }
  /**
   * <p>Validate registration.</p>
   *
   * @param mapping
   * @param request
   *
   * @return ActionErrors
   */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    // only validate if the user has clicked "Save"
    if ("Save".equals(request.getParameter("actionRx"))) {
      errors = super.validate(mapping, request);
    }
    return errors;
  }
  /**
   * <p>Converts prescription presentation bean to prescription value object.</p>
   *
   * @return PrescriptionBean
   */
  public Prescription toPrescription() {
    Calendar cal = MedRecWebAppUtils.str2Calendar(getDatePrescribed());
    return new Prescription(getPatientId(),
        getPhysicianId(),
        cal,
        getDrug(),
        getDosage(),
        getFrequency(),
        Integer.valueOf(getRefillsRemaining()),
        getInstructions());
  }

  // Utility
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("RxBean [");
    str.append("PatId: "+patientId);
    str.append(" | PhysId: "+physicianId);
    str.append(" | Date: "+
        (datePrescribed == null ? "null" : datePrescribed.toString()));
    str.append(" | Drug: "+drug);
    str.append(" | Dosage: "+dosage);
    str.append(" | Freq: "+frequency);
    str.append(" | Refills: "+refillsRemaining);
    str.append(" | Instructions: "+instructions);
    str.append("]");

    return str.toString();
  }
}

