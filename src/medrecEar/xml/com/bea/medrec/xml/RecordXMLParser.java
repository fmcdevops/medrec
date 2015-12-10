package com.bea.medrec.xml;

import com.bea.medrec.exceptions.MedRecException;
import com.bea.medrec.utils.ErrorConstants;
import com.bea.medrec.utils.MedRecConstants;
import com.bea.medrec.utils.MedRecUtils;
import com.bea.medrec.value.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import org.apache.log4j.Logger;
import weblogic.xml.stream.*;

/**
 * <p>Parses incoming medical record xml.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */
public class RecordXMLParser
{
  private static Logger logger =
    Logger.getLogger(RecordXMLParser.class.getName());

  // Instance variables
  Collection medRecCol = null;

 /**
  * <p>Public constructor.</p>
  */
  private RecordXMLParser() { }

  /**
  * <p>Creates a new instance of RecordXMLParser.</p>
  */
  public static RecordXMLParser getInstance() { return new RecordXMLParser(); }

 /**
  * <p>Controls the flow of the XML document's overall parsing.</p>
  *
  * @param pStream XML input stream.
  * @exception MedRecException
  */
  public void parse(XMLInputStream pStream) throws MedRecException
  {
    logger.info("Parse medical record stream.");
    try {
      parseMedicalRecords(pStream);
    }
    catch (MedRecException me) {
      logger.error(me.getLocalizedMessage());
      throw me;
    }
    catch (XMLStreamException e) {
      logger.error(e.getLocalizedMessage());
      throw new MedRecException(ErrorConstants.XML_ANOMALY
        +"\n"+e.getLocalizedMessage());
    }
    catch (Exception e) {
      logger.error(e.getLocalizedMessage());
      throw new MedRecException(e);
    }
  }

 /**
  * <p>Controls the flow of the XML document's overall parsing.</p>
  *
  * @param pStream XML input stream.
  * @exception Exception
  * @exception XMLStreamException
  */
  public void parseMedicalRecords(XMLInputStream pStream)
    throws XMLStreamException, MedRecException, Exception
  {
    logger.info("Parse medical record stream.");

    String srcId;
    String srcName;

    // Go to the first start element of the stream.
    pStream.skip(XMLEvent.START_ELEMENT);

    // Get the current element and move to the next element.
    XMLEvent element = pStream.next();

    // Validate the name and element type.
    if (!element.getName().getLocalName().equals(MedRecConstants.MEDICAL_RECORD)
        && !element.isStartElement()) {
      throw new MedRecException(ErrorConstants.XML_INVALID);
    }
    else {
      StartElement se = (StartElement)element;
      srcId = getAttribute(se, MedRecConstants.SRC_ID);
      srcName = getAttribute(se, MedRecConstants.SRC_NAME);
    }

    // Process the XML document looking for customer and item list elements.
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.MEDICAL_VISIT)) {
        processPatientVisit(pStream.getSubStream(), srcId, srcName);
      }
      pStream.skipElement();
    }
  }

 /**
  * <p>Controls the flow of the XML document's overall parsing.</p>
  *
  * @param pStream XML input stream.
  * @exception Exception
  * @exception XMLStreamException
  */
  public void processPatientVisit(XMLInputStream pStream,
                                  String srcId,
                                  String srcName)
    throws XMLStreamException, MedRecException, Exception
  {
    logger.info("Processing patient and visits.");

    MedicalRecord medicalRecord = new MedicalRecord(Integer.valueOf(srcId),
      srcName);
    Patient patient = null;
    User user = null;

    // Instantiate and load the customer buiness object.
    // Iterate through the name and address elements
    // extracting known attributes to the patient business object.
    pStream.skip();
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.PATIENT)) {
        patient = processPatient(pStream.getSubStream());
        user = new User(patient.getEmail());
        medicalRecord.setPatient(patient);
        medicalRecord.setUser(user);
      } else if (name.getLocalName().equals(MedRecConstants.RECORD)) {
        Record record = processRecord(pStream.getSubStream());
        medicalRecord.addRecord(record);
      }
      pStream.skip();
    }
    addMedicalRecord(medicalRecord);
  }

  /**
  * <p>Controls the flow of the customer parsing resulting
  *     in a customer business object.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private Patient processPatient(XMLInputStream pStream)
    throws Exception, XMLStreamException
  {
    logger.info("Processing patient.");

    // Instantiate and load the customer buiness object.
    // Iterate through the name and address elements
    // extracting known attributes to the patient business object.
    Patient patient = new Patient();
    pStream.skip();
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug("Current element: "+name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.SSN)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.SSN+": "+cd.getContent());
          patient.setSsn(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.FIRST_NAME)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.FIRST_NAME+": "+cd.getContent());
          patient.setFirstName(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.MIDDLE_NAME)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.MIDDLE_NAME+": "+cd.getContent());
          patient.setMiddleName(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.LAST_NAME)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.LAST_NAME+": "+cd.getContent());
          patient.setLastName(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.DOB)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DOB+": "+cd.getContent());
          Calendar cal = str2Cal(cd.getContent());
          patient.setDateOfBirth(cal);
        }
      } else if (name.getLocalName().equals(MedRecConstants.GENDER)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.GENDER+": "+cd.getContent());
          String gender = (cd.getContent().equals("male") ? "Male" : "Female");
          patient.setGender(gender);
        }
      } else if (name.getLocalName().equals(MedRecConstants.PHONE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.PHONE+": "+cd.getContent());
          String phone = cd.getContent();
          patient.setPhone(phone);
        }
      } else if (name.getLocalName().equals(MedRecConstants.EMAIL)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.EMAIL+": "+cd.getContent());
          String email = cd.getContent();
          patient.setEmail(email);
        }
      } else if (name.getLocalName().equals(MedRecConstants.ADDRESS)) {
        Address addr = processAddress(pStream.getSubStream());
        patient.setAddress(addr);
      }
      pStream.skip();
    }
    logger.debug(patient.toString());
    return patient;
  }

   /**
  * <p>Parses item data creating item business objects.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private Address processAddress(XMLInputStream pStream)
     throws Exception, XMLStreamException
  {
    logger.info("Processing address.");

    Address addr = new Address();

    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.STREET1)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.STREET1+": "+cd.getContent());
          addr.setStreetName1(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.STREET2)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.STREET2+": "+cd.getContent());
          addr.setStreetName2(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.CITY)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.CITY+": "+cd.getContent());
          addr.setCity(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.STATE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.STATE+": "+cd.getContent());
          addr.setState(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.ZIP)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.ZIP+": "+cd.getContent());
          addr.setZipCode(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.COUNTRY)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.COUNTRY+": "+cd.getContent());
          addr.setCountry(cd.getContent());
        }
      }
      pStream.skip();
    }
    return addr;
  }

  /**
  * <p>Controls the flow of items in an item list element.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private Record processRecord(XMLInputStream pStream)
    throws Exception, XMLStreamException
  {
    logger.info("Processing record.");

    Record record = new Record();

    // Look for item start elements.
    // Pass stream for item processing.
    pStream.skip();
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.DATE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DATE+": "+cd.getContent());
          Calendar cal = str2Cal(cd.getContent());
          record.setDate(cal);
        }
      } else if (name.getLocalName().equals(MedRecConstants.SYMPTOMS)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.SYMPTOMS+": "+cd.getContent());
          record.setSymptoms(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.DIAGNOSIS)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DIAGNOSIS+": "+cd.getContent());
          record.setDiagnosis(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.NOTES)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.NOTES+": "+cd.getContent());
          record.setNotes(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.VITALSIGNS)) {
        VitalSigns vitals = processVitals(pStream.getSubStream());
        record.setVitalSigns(vitals);
      } else if (name.getLocalName().equals(MedRecConstants.PRESCRIPTION)) {
        Prescription rx = processRx(pStream.getSubStream());
        record.addRx(rx);
      }
      pStream.skip();
    }
    logger.debug(record.toString());
    return record;
  }

  /**
  * <p>Parses item data creating item business objects.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private VitalSigns processVitals(XMLInputStream pStream)
    throws Exception, XMLStreamException
  {
    logger.info("Processing vitals.");

    // Instantiate new item business object.
    VitalSigns vitals = new VitalSigns();

    // Extract known character data and load to item business object.
    pStream.skip();
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.WEIGHT)) {
        Integer weight = Integer.valueOf(getAmount(pStream.getSubStream()));
        logger.debug(MedRecConstants.WEIGHT+": "+weight);
        vitals.setWeight(weight);
      } else if (name.getLocalName().equals(MedRecConstants.HEIGHT)) {
        String height = getAmount(pStream.getSubStream());
        logger.debug(MedRecConstants.HEIGHT+": "+height);
        vitals.setHeight(Integer.valueOf(height));
      } else if (name.getLocalName().equals(MedRecConstants.TEMP)) {
        String temp = getAmount(pStream.getSubStream());
        logger.debug(MedRecConstants.TEMP+": "+temp);
        vitals.setTemperature(temp);
      } else if (name.getLocalName().equals(MedRecConstants.BLDPRESS)) {
        String bloodPressure = processBloodPressure(pStream.getSubStream());
        vitals.setBloodPressure(bloodPressure);
      } else if (name.getLocalName().equals(MedRecConstants.PULSE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.PULSE+": "+cd.getContent());
          vitals.setPulse(cd.getContent());
        }
      }
      pStream.skip();
    }
    //logger.debug(vitals.toString());
    return vitals;
  }

  /**
  * <p>Parses item data creating item business objects.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private String getAmount(XMLInputStream pStream)
    throws Exception, XMLStreamException
  {
    logger.debug("Get amount.");

    String amount = "";

    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.AMOUNT)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.AMOUNT+": "+cd.getContent());
          amount = cd.getContent();
        }
      } else if (name.getLocalName().equals(MedRecConstants.UOM)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.UOM+": "+cd.getContent());
        }
        // do conversion stuff.
      }
      pStream.skip();
    }
    return amount;
  }

 /**
  * <p>Parses item data creating item business objects.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private String processBloodPressure(XMLInputStream pStream)
     throws Exception, XMLStreamException
  {
    logger.info("Processing bloodpressure.");

    String systolic = "";
    String diastolic = "";

    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.SYSTOLIC)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.SYSTOLIC+": "+cd.getContent());
          systolic = cd.getContent();
        }
      } else if (name.getLocalName().equals(MedRecConstants.DIASTOLIC)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DIASTOLIC+": "+cd.getContent());
          diastolic = cd.getContent();
        }
      }
      pStream.skip();
    }
    return systolic+"/"+diastolic;
  }

 /**
  * <p>Parses item data creating item business objects.</p>
  *
  * @param pStream XML input stream.
  * @exception XMLStreamException
  */
  private Prescription processRx(XMLInputStream pStream)
    throws Exception, XMLStreamException
  {
    logger.info("Processing rx.");

    // Instantiate new item business object.
    Prescription rx = new Prescription();

    // Extract known character data and load to item business object.
    pStream.skip();
    while(pStream.skip(XMLEvent.START_ELEMENT)) {
      XMLName name = pStream.peek().getName();
      logger.debug(name.getLocalName());
      if (name.getLocalName().equals(MedRecConstants.DATE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DATE+": "+cd.getContent());
          Calendar cal = str2Cal(cd.getContent());
          rx.setDatePrescribed(cal);
        }
      } else if (name.getLocalName().equals(MedRecConstants.DRUG)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DRUG+": "+cd.getContent());
          rx.setDrug(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.DOSAGE)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.DOSAGE+": "+cd.getContent());
          rx.setDosage(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.FREQUENCY)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.FREQUENCY+": "+cd.getContent());
          rx.setFrequency(cd.getContent());
        }
      } else if (name.getLocalName().equals(MedRecConstants.REFILLS)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.REFILLS+": "+cd.getContent());
          rx.setRefillsRemaining(Integer.valueOf(cd.getContent()));
        }
      } else if (name.getLocalName().equals(MedRecConstants.INSTRUCTIONS)) {
        pStream.skip(XMLEvent.CHARACTER_DATA);
        CharacterData cd = (CharacterData)pStream.peek();
        if (cd.hasContent()) {
          logger.debug(MedRecConstants.INSTRUCTIONS+": "+cd.getContent());
          rx.setInstructions(cd.getContent());
        }
      }
      pStream.skip();
    }
    //logger.debug(rx.toString());
    return rx;
  }

  /**
  * <p>Extract known attribute value by name from a start element.</p>
  *
  * @param pStartElement XML input stream.
  * @param pStr
  * @return String Value of attribute.
  * @exception XMLStreamException
  */
  private String getAttribute(StartElement pStartElement, String pStr)
    throws Exception
  {
    String str = null;
    Attribute attr = null;
    AttributeIterator attrs = null;

    // Get value for known attribute name.
    attrs = (AttributeIterator)pStartElement.getAttributes();
    while (attrs.hasNext()) {
      attr = (Attribute)attrs.next();
      if (attr.getName().getLocalName().equals(pStr)) {
        str = attr.getValue();
        break;
      }
    }
    logger.debug(attr.getName().getLocalName()+": "+str);
    return str;
  }

 /**
  * <p>Extract known attribute value by name from a start element.</p>
  *
  * @param medRec MedicalRecord value object.
  */
  public void addMedicalRecord(MedicalRecord medRec)
  {
    if (medRecCol == null) medRecCol = new ArrayList();
    medRecCol.add(medRec);
  }

 /**
  * <p>Extract known attribute value by name from a start element.</p>
  *
  * @return Collection Value of attribute.
  */
  public Collection getMedicalRecords() { return medRecCol; }

  // Utilities
  private Calendar str2Cal(String str)
  {
    Calendar cal = null;
    if (MedRecUtils.isNotEmpty(str)) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = sdf.parse(str);
        cal = Calendar.getInstance();
        cal.setTime(d);
      }
      catch(ParseException e) {   }
    }
    return cal;
  }

}
