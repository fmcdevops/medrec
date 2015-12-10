package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecMessageProperties;
import com.bea.medrec.value.VitalSigns;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

/**
 * <p>Form bean for vital signs.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>tempature</b> - Entered temperature value
 * <li><b>pulse</b> - Entered pulse value
 * <li><b>blood pressure</b> - Entered blood pressure value
 * <li><b>weight</b> - Entered weight value
 * <li><b>height</b> - Entered height value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class VitalSignsBean extends BaseBean {
  private static Logger logger =
      Logger.getLogger(VitalSignsBean.class.getName());

  // Attributes
  private String temperature = "";
  private String bloodPressure = "";
  private String pulse = "";
  private String weight = "";
  private String height = "";

  // Constructors
  public VitalSignsBean() {
  }

  public VitalSignsBean(VitalSigns vitals) {
    super.setId(vitals.getId());
    this.temperature = vitals.getTemperature();
    this.bloodPressure = vitals.getBloodPressure();
    this.pulse = vitals.getPulse();
    this.weight = toStr(vitals.getWeight());
    this.height = toStr(vitals.getHeight());
  }

  public VitalSignsBean(Integer id,
                        String temperature,
                        String bloodPressure,
                        String pulse,
                        Integer weight,
                        Integer height) {
    super.setId(id);
    this.temperature = temperature;
    this.bloodPressure = bloodPressure;
    this.pulse = pulse;
    this.weight = toStr(weight);
    this.height = toStr(height);
  }

  // Getters
  public String getTemperature() {
    return this.temperature;
  }

  public String getBloodPressure() {
    return this.bloodPressure;
  }

  public String getPulse() {
    return this.pulse;
  }

  public String getWeight() {
    return this.weight;
  }

  public String getHeight() {
    return this.height;
  }

  // Setters
  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public void setBloodPressure(String bloodPressure) {
    this.bloodPressure = bloodPressure;
  }

  public void setPulse(String pulse) {
    this.pulse = pulse;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  // Public Methods
  public void reset() {
    this.temperature = "";
    this.bloodPressure = "";
    this.pulse = "";
    this.weight = "";
    this.height = "";
  }

  public void update(VitalSignsBean vitalSignsBean) {
    this.temperature = vitalSignsBean.getTemperature();
    this.bloodPressure = vitalSignsBean.getBloodPressure();
    this.pulse = vitalSignsBean.getPulse();
    this.weight = vitalSignsBean.getWeight();
    this.height = vitalSignsBean.getHeight();
  }

  /**
   * <p>Converts vital signs presentation bean to vital signs value object.</p>
   *
   * @return VitalSignsWS
   */
  public VitalSigns toVitalSigns() {
    return new VitalSigns(getTemperature(),
        getBloodPressure(),
        getPulse(),
        getWeight(),
        getHeight());
  }

  // Utility
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("VitalSignsBean [");
    str.append("Temp: " + temperature);
    str.append(" | BP: " + bloodPressure);
    str.append(" | Pulse: " + pulse);
    str.append(" | Weight: " + weight);
    str.append(" | Height: " + height);
    str.append("]");

    return str.toString();
  }

}
