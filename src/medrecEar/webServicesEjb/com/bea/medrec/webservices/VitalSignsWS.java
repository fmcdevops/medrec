package com.bea.medrec.webservices;

/**
 * This class represents information about a VitalSigns.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class VitalSignsWS
{
  private Integer id;
  private String temperature;
  private String bloodPressure;
  private String pulse;
  private Integer weight;
  private Integer height;

  public VitalSignsWS() {}

  public VitalSignsWS(Integer id,
                      String temperature,
                      String bloodPressure,
                      String pulse,
                      Integer weight,
                      Integer height)
  {
    this.id            = id;
    this.temperature   = temperature;
    this.bloodPressure = bloodPressure;
    this.pulse         = pulse;
    this.weight        = weight;
    this.height        = height;
  }

  public VitalSignsWS(String temperature,
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

  public Integer getId()           { return this.id; }
  public String getTemperature()   { return this.temperature; }
  public String getBloodPressure() { return this.bloodPressure; }
  public String getPulse()         { return this.pulse; }
  public Integer getWeight()       { return this.weight; }
  public Integer getHeight()       { return this.height; }

  public void setId(Integer id)                      { this.id = id; }
  public void setTemperature(String temperature)     { this.temperature = temperature; }
  public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
  public void setPulse(String pulse)                 { this.pulse = pulse; }
  public void setWeight(Integer weight)              { this.weight = weight; }
  public void setHeight(Integer height)              { this.height = height; }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append("VitalSignsWS [Id: "+id);
    str.append(", Temp: "+temperature);
    str.append(", BP: "+bloodPressure);
    str.append(", Pulse: "+pulse);
    str.append(", Weight: "+weight);
    str.append(", Height: "+height);
    str.append("]");

    return str.toString();
  }

}
