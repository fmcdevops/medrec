package com.bea.medrec.value;

/**
 * <p>This class represents a patient's vital signs.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class VitalSigns extends BaseVO {

  // Instance variables
  private String temperature;     // F
  private String bloodPressure;   // systolic/diastolic
  private String pulse;           // bmp
  private Integer weight;         // lbs
  private Integer height;         // inches

  // Constructors
  public VitalSigns() {}

  public VitalSigns(String temperature,
                    String bloodPressure,
                    String pulse,
                    Integer weight,
                    Integer height)
  {
    this.temperature   = temperature;
    this.bloodPressure = bloodPressure;
    this.pulse         = pulse;
    this.weight        = weight;
    this.height        = height;
  }

  public VitalSigns(String temperature,
                    String bloodPressure,
                    String pulse,
                    String weight,
                    String height)
  {
    this.temperature   = temperature;
    this.bloodPressure = bloodPressure;
    this.pulse         = pulse;
    this.weight        = str2Integer(weight);
    this.height        = str2Integer(height);
  }

  // Getters
  public String getTemperature()   { return this.temperature; }
  public String getBloodPressure() { return this.bloodPressure; }
  public String getPulse()         { return this.pulse; }
  public Integer getWeight()       { return this.weight; }
  public Integer getHeight()       { return this.height; }

  // Setters
  public void setTemperature(String temperature)
    { this.temperature = temperature; }
  public void setBloodPressure(String bloodPressure)
    { this.bloodPressure = bloodPressure; }
  public void setPulse(String pulse)                 { this.pulse = pulse; }
  public void setWeight(Integer weight)              { this.weight = weight; }
  public void setHeight(Integer height)              { this.height = height; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("VitalSigns [Id: "+super.getId());
    str.append(" | Temp: "+temperature);
    str.append(" | BP: "+bloodPressure);
    str.append(" | Pulse: "+pulse);
    str.append(" | Weight: "+weight);
    str.append(" | Height: "+height);
    str.append("]");

    return str.toString();
  }

}
