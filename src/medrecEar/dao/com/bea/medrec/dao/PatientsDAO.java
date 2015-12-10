package com.bea.medrec.dao;

import java.sql.SQLException;
import java.util.Collection;
import javax.naming.NamingException;

/**
 * <p>Patient entity interface.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public interface PatientsDAO {
  public Collection getPatientsByStatus(String pStatus)
    throws NamingException, SQLException;
}
