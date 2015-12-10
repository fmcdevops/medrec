package com.bea.medrec.dao;

import com.bea.medrec.exceptions.MedRecException;
import com.bea.medrec.utils.JNDINames;
import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;


/**
 *  <p>Data access object factory.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public abstract class DAOFactory {

  static Logger logger = Logger.getLogger(DAOFactory.class.getName());

  public static Connection conn = null;
  public static DatabaseMetaData dbmd = null;
  public static DataSource datasource = null;

  public abstract PatientsDAO getPatientsDAO();

 /**
  * <p>Return appropriate factory based on database vender.</p>
  *
  * @return DAOFactory
  */
  public static DAOFactory getDAOFactory() throws MedRecException
 {
    InitialContext initCtx = null;
    String databaseProductName = null;

    try {
      initCtx = new InitialContext();
      datasource =
        (javax.sql.DataSource)initCtx.lookup(JNDINames.MEDREC_TX_DATASOURCE);
      conn = createConnection();
      dbmd = conn.getMetaData();
      databaseProductName = dbmd.getDatabaseProductName();
      logger.info("Vendor product name: "+databaseProductName);
      if(databaseProductName.indexOf("PointBase") != -1)
        return new PointBaseDAOFactory();
      else if(databaseProductName.indexOf("Oracle") != -1)
        return null; // new OracleDAOFactory();
      else return null;
    }
    catch (Exception e) {
      throw new MedRecException("Unable to obtain data access factory.", e);
    }
  }

 /**
  * <p>Creates new database connection.</p>
  *
  * @return Connection
  *
  * @exception java.sql.SQLException
  */
  public static Connection createConnection() throws SQLException
  {
    logger.info("Start creating connection.");
    return datasource.getConnection();
  }

 /**
  * <p>Return connection.</p>
  *
  * @return Connection
  */
  public static Connection getConnection() { return conn; }

  /**
   * <p>Utility function to clean up db usage.</p>
   *
   * @param pResultSet
   * @param pStmt
   * @param pConn
   *
   * @exception java.sql.SQLException
   */
   public static void cleanup(ResultSet pResultSet,
                              Statement pStmt,
                              Connection pConn)
     throws SQLException
   {
     logger.info("Start cleaning up connection.");
     try {
       if (pResultSet!=null) {
         pResultSet.close();
         pResultSet=null;
       }
       logger.info("Finished cleaning up connection.");
     }
     catch(SQLException e) {
       throw e;
     }
     finally {
       try {
         if (pStmt!= null) {
           pStmt.close();
           pStmt=null;
         }
       }
       catch(SQLException e) {
         throw e;
       }
       finally {
         try {
           if (pConn!= null) {
             pConn.close();
             pConn=null;
           }
         }
         catch(SQLException e) {
           throw e;
         }
       }
     }
   }

}
