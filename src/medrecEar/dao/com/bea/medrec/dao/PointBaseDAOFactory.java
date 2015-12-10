package com.bea.medrec.dao;

import org.apache.log4j.Logger;


/**
 * <p>PointBase DAO factory implementation.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class PointBaseDAOFactory extends DAOFactory {

  static Logger logger =
    Logger.getLogger(PointBaseDAOFactory.class.getName());

 /**
  * <p>Get PointBase implementation of Patient entity DAO.</p>
  *
  * @return PatientsDAO
  */
  public PatientsDAO getPatientsDAO() {
    return new PointBasePatientsDAO();
  }
}
