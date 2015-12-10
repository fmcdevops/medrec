package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecMessageProperties;
import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.Prescription;
import com.bea.medrec.value.Record;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Form bean for the user record pages.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>patientId</b> - Hidden patientId value
 * <li><b>physicianId</b> - Hidden firstName value
 * <li><b>symptoms</b> - Entered symptoms value
 * <li><b>diagnosis</b> - Entered diagnosis value
 * <li><b>notes</b> - Entered notes value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class RecordBean extends BaseBean {
  private static Logger logger = Logger.getLogger(RecordBean.class.getName());

  // Instance Variables
  private String patientId = "";
  private String physicianId = "";
  private String physicianName = "";
  private String date = "";
  private String symptoms = "";
  private String diagnosis = "";
  private String notes = "";
  private VitalSignsBean vitalSignsBean = new VitalSignsBean();
  private Collection prescriptionBeans = new ArrayList();

  // Constuctors
  public RecordBean() {
  }

  public RecordBean(Record record) {
    String displayDate = MedRecWebAppUtils.getDisplayDate(record.getDate());
    super.setId(record.getId());
    this.patientId = toStr(record.getPatientId());
    this.physicianId = toStr(record.getPhysicianId());
    this.physicianName = record.getPhysicianName();
    this.date = displayDate;
    this.symptoms = record.getSymptoms();
    this.diagnosis = record.getDiagnosis();
    this.notes = record.getNotes();
    this.vitalSignsBean = new VitalSignsBean(record.getVitalSigns());
    this.prescriptionBeans = toRxBeanCollection(record.getPrescriptions());
  }

  public RecordBean(Integer id,
                    String patientId,
                    String physicianId,
                    String physicianName,
                    String date,
                    String symptoms,
                    String diagnosis,
                    String notes,
                    VitalSignsBean vitalSignsBean,
                    Collection prescriptionBeans) {
    super.setId(id);
    this.patientId = patientId;
    this.physicianId = physicianId;
    this.physicianName = physicianName;
    this.date = date;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
    this.notes = notes;
    this.vitalSignsBean = vitalSignsBean;
    this.prescriptionBeans = prescriptionBeans;
  }

  public RecordBean(Integer id,
                    Integer patientId,
                    Integer physicianId,
                    String physicianName,
                    String date,
                    String symptoms,
                    String diagnosis,
                    String notes,
                    VitalSignsBean vitalSignsBean,
                    Collection prescriptionBeans) {
    super.setId(id);
    this.patientId = toStr(patientId);
    this.physicianId = toStr(physicianId);
    this.physicianName = physicianName;
    this.date = date;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
    this.notes = notes;
    this.vitalSignsBean = vitalSignsBean;
    this.prescriptionBeans = prescriptionBeans;
  }

  public RecordBean(String id,
                    String physicianName,
                    String date,
                    String symptoms,
                    String diagnosis) {
    super.setId(id);
    this.physicianName = physicianName;
    this.date = date;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
  }

  public RecordBean(Integer id,
                    String physicianName,
                    String date,
                    String symptoms,
                    String diagnosis) {
    super.setId(id);
    this.physicianName = physicianName;
    this.date = date;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
  }

  // Getters
  public String getPatientId() {
    return this.patientId;
  }

  public String getPhysicianId() {
    return this.physicianId;
  }

  public String getPhysicianName() {
    return this.physicianName;
  }

  public String getDate() {
    return this.date;
  }

  public String getSymptoms() {
    return this.symptoms;
  }

  public String getDiagnosis() {
    return this.diagnosis;
  }

  public String getNotes() {
    return this.notes;
  }

  public VitalSignsBean getVitalSignsBean() {
    if (vitalSignsBean == null) vitalSignsBean = new VitalSignsBean();
    return vitalSignsBean;
  }

  public Collection getPrescriptionBeans() {
    return this.prescriptionBeans;
  }

  // Setters
  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public void setPhysicianId(String physicianId) {
    this.physicianId = physicianId;
  }

  public void setPhysicianName(String physicianName) {
    this.physicianName = physicianName;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public void setVitalSignsBean(VitalSignsBean vitalSignsBean) {
    this.vitalSignsBean = vitalSignsBean;
  }

  public void setPrescriptions(Collection rXs) {
    this.prescriptionBeans = rXs;
  }

  // Update method
  public void update(RecordBean recordBean) {
    this.symptoms = recordBean.getSymptoms();
    this.diagnosis = recordBean.getDiagnosis();
    this.notes = recordBean.getNotes();
    this.vitalSignsBean.update(recordBean.getVitalSignsBean());
  }

  /**
   * <p>Validate record.</p>
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
    if ("Save".equals(request.getParameter("action"))) {
      errors = super.validate(mapping, request);
    }
    return errors;
  }

  /**
   * <p>Converts record presentation bean to record value object.</p>
   *
   * @return RecordWS
   */
  public Record toRecord() {
    return new Record(getPatientId(),
        getPhysicianId(),
        MedRecWebAppUtils.str2Calendar(getDate()),
        getSymptoms(),
        getDiagnosis(),
        getNotes(),
        getVitalSignsBean().toVitalSigns(),
        toRxCollection(getPrescriptionBeans()));
  }

  /**
   * <p>Converts a collection of prescription presentation beans to
   * a collection of prescription value objects.</p>
   *
   * @param prescriptions
   * @return Collection
   */
  public static Collection toRxCollection(Collection prescriptions) {
    Collection array = new ArrayList();
    if (prescriptions != null) {
      Iterator itr = prescriptions.iterator();
      while (itr.hasNext()) {
        Prescription rx = ((PrescriptionBean) itr.next()).toPrescription();
        array.add(rx);
      }
    }
    return array;
  }

  /**
   * <p>Converts a collection of prescription value objects to
   * a collection of prescription presentation beans.</p>
   *
   * @param prescriptions
   * @return Collection
   */
  public static Collection toRxBeanCollection(Collection prescriptions) {
    Collection array = new ArrayList();
    if (prescriptions != null) {
      Iterator itr = prescriptions.iterator();
      while (itr.hasNext()) {
        PrescriptionBean rxBean = new PrescriptionBean((Prescription) itr.next());
        array.add(rxBean);
      }
    }
    return array;
  }

  // Utility methods
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("Record [");
    str.append("PatId: " + patientId);
    str.append(" | PhysId: " + physicianId);
    str.append(" | PhysName: " + physicianName);
    str.append(" | DOB: " + date);
    str.append(" | Syms: " + symptoms);
    str.append(" | Diag: " + diagnosis);
    str.append(" | Notes: " + notes);
    str.append(" | Vitals: " + (vitalSignsBean == null ? "null" : vitalSignsBean.toString()));
    str.append(" | " + MedRecWebAppUtils.col2Str(prescriptionBeans, "Rxs"));
    str.append("]");

    return str.toString();
  }

}
