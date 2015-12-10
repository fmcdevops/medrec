package com.bea.medrec.dao;

import com.bea.medrec.value.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;

/**
 * <p>PointBase implementation of Patient entity data access object.
 * NOTE: This class is for reference only, and is currently not used within MedRec.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class PointBasePatientsDAO implements PatientsDAO {

  private static Logger logger =
    Logger.getLogger(PointBasePatientsDAO.class.getName());

  private static final String GET_PATIENTS_BY_STATUS = "SELECT a.username, "+
                                            "b.id, b.first_name, b.last_name "+
                                            "FROM medrec_user a, patient b "+
                                            "WHERE a.status = ? "+
                                            "AND b.email = a.username";

  public PointBasePatientsDAO() {  /* initialization */  }

 /**
  * <p>Get patients by status.</p>
  *
  * @param  pStatus
  *
  * @return Collection
  */
  public Collection getPatientsByStatus(String pStatus)
    throws SQLException
  {
    logger.info("Finding patients with status: "+pStatus);
    // Declare local variables.
    Collection patients = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PointBaseDAOFactory.getConnection();

      logger.debug("Preparing statement.");
      logger.debug(this.GET_PATIENTS_BY_STATUS);
      pstmt = conn.prepareStatement(this.GET_PATIENTS_BY_STATUS);
      pstmt.setString(1, pStatus);
      rs = pstmt.executeQuery();
      logger.debug("Executed query.");

      patients = new ArrayList();
      while (rs.next()) {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setFirstName(rs.getString("first_name"));
        patient.setLastName(rs.getString("last_name"));
        patients.add(patient);
      }
    }
    catch (SQLException sqle) {
      logger.error(sqle.getMessage());
      throw sqle;
    }
    finally {
      PointBaseDAOFactory.cleanup(rs, pstmt, conn);
    }
    return patients;
  }
}
