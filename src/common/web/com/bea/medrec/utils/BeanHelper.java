package com.bea.medrec.utils;

import com.bea.medrec.beans.*;
import com.bea.medrec.value.Patient;
import com.bea.medrec.value.Record;
import com.bea.medrec.value.XMLImportFile;
import com.bea.medrec.value.Prescription;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 * This helper class converts collection of presentation beans to
 * collection of value objects and visa versa.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class BeanHelper
{
  private static Logger logger = Logger.getLogger(BeanHelper.class.getName());

      //   I M P O R T   C O L L E C T I O N
  /**
  * <p>Converts a collection of import value objects to
  * a collection of import presentation beans.</p>
  *
  * @param importFiles    Collection of Import value objects
  * @return Collection   Collection of Import presentation beans
  */
  public static Collection toImportBeanCollection(Collection importFiles)
  {
    Collection array = new ArrayList();
    if (importFiles != null) {
      Iterator itr = importFiles.iterator();
      while (itr.hasNext()) {
        XMLImportBean xmlImportBean =
          new XMLImportBean((XMLImportFile)itr.next());
        array.add(xmlImportBean);
      }
    }
    return array;
  }

      //   P A T I E N T
 /**
  * <p>Converts a collection of patient value objects to
  * a collection of patient presentation beans.</p>
  *
  * @param patients
  * @return Collection
  */
  public static Collection toPatientBeanCollection(Collection patients)
  {
    Collection array = new ArrayList();
    if (patients != null) {
      Iterator itr = patients.iterator();
      while (itr.hasNext()) {
        PatientBean patientBean = new PatientBean((Patient)itr.next());
        array.add(patientBean);
      }
    }
    return array;
  }

      //   P A T I E N T   A P P R O V A L
 /**
  * <p>Converts a collection of patient approval presentation beans to
  * a collection of patient approval value objects.</p>
  *
  * @param patients    Collection of Patient VOs
  * @return Collection   Collection of Patient Beans
  */
  public static Collection toPatientApprovalBeanCollection(Collection patients)
  {
    ArrayList array = new ArrayList();
    if (patients != null) {
      Iterator itr = patients.iterator();
      while (itr.hasNext()) {
        Patient patient = (Patient)itr.next();
        PatientApprovalBean approval = new PatientApprovalBean(patient.getId(),
          patient.getLastName(), patient.getFirstName());
        array.add(approval);
      }
    }
    return array;
  }

      //   P R E S C R I P T I O N
 /**
  * <p>Converts a collection of prescription value objects to
  * a collection of prescription presentation beans.</p>
  *
  * @param prescriptions
  * @return Collection
  */
  public static Collection toRxBeanCollection(Collection prescriptions)
  {
     Collection array = new ArrayList();
     if (prescriptions != null) {
       Iterator itr = prescriptions.iterator();
       while (itr.hasNext()) {
         PrescriptionBean rxBean = new PrescriptionBean((Prescription)itr.next());
         array.add(rxBean);
       }
     }
     return array;
   }

      //   R E C O R D
 /**
  * <p>Converts record value object to lite record presentation bean.</p>
  *
  * @param record
  * @return RecordBean
  */
  public static RecordBean toRecordBeanLite(Record record)
  {
    RecordBean recordBean = null;
    if (record != null) {
      String displayDate = MedRecWebAppUtils.getDisplayDate(record.getDate());
      recordBean = new RecordBean(record.getId(),
                                  record.getPhysicianName(),
                                  displayDate,
                                  record.getSymptoms(),
                                  record.getNotes());
    }
    return recordBean;
  }

 /**
  * <p>Converts a collection of record value objects to
  * a collection of record presentation beans.</p>
  *
  * @param records
  * @return Collection
  */
  public static Collection toRecordBeanCollection(Collection records)
  {
    Collection array = new ArrayList();
    if (records != null) {
      Iterator itr = records.iterator();
      while (itr.hasNext()) {
        RecordBean recordBean = toRecordBeanLite((Record)itr.next());
        array.add(recordBean);
      }
    }
    return array;
  }
}
