package com.bea.medrec.security;

import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * <p>The sample sample authenticator's persistent state (ie. database).
 *
 * This class has methods for reading reading user and
 * group information.
 *
 * The sample authenticator's runtime implementation
 * delegates much of its work to this class.</p>
 *
 * @author Copyright (c) 2003 by BEA Systems. All Rights Reserved.
 */
public final class MedRecSampleAuthDatabase
{
  private boolean debug = true;
  private Connection conn;

 /**
  * <p>Create or re-open a sample authenticator's database.</p>
  *
  * @param mbean A MedRecAuthenticatorMBean containing the
  * sample authenticator's configuration data.
  */
  public MedRecSampleAuthDatabase(MedRecSampleAuthenticatorMBean mbean)
  { /**  not implemented  */ }

 /**
  * <p>Looks up a user's password.</p>
  *
  * @throws SQLException if the user does not exist.
  * @throws Exception if the user does not exist.
  */
  public void openConnection()
    throws SQLException, Exception
  {
    DataSource datasource = null;
    InitialContext initCtx = null;

    try {
      initCtx = new InitialContext();
      datasource = (javax.sql.DataSource)initCtx.lookup("MedRecTxDataSource");
      conn = datasource.getConnection();
      conn.setAutoCommit(true);

      // Log database vendor name
      DatabaseMetaData dbmd = conn.getMetaData();
      System.out.println("Vendor product name: "+dbmd.getDatabaseProductName());
    }
    catch (SQLException sqle) {
      System.out.println("Unable to open database connection. "+sqle.getMessage());
      throw sqle;
    }
    catch (Exception e) {
      System.out.println("Unable to open database connection. "+e.getMessage());
      throw e;
    }
  }

 /**
  * <p>Looks up a user's password.</p>
  */
  public void closeConnection()
  {
    try {
      cleanup(null, null, conn);
    }
    catch (SQLException e) {
      System.out.println("Unable to close database connection.");
      // Log user in if unable to close connection.
    }
  }

/**
 * <p>Look up a user</p>
 *
 * @param username A
 */
public synchronized boolean validateUserExists(String username)
  throws SQLException, Exception {
	System.out.println("Getting exist:" + username);
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String password = null;
	int count = 0;

	try {
	  if (conn == null) openConnection();

		System.out.println("validateUserExists: "
			+MedRecSampleAuthConstants.GET_USERCOUNT_STMT);
		stmt = conn.prepareStatement(MedRecSampleAuthConstants.GET_USERCOUNT_STMT);
		stmt.setString(1, username);

		rs = stmt.executeQuery();
		if (rs.next()) count = rs.getInt(1);
	}	catch(Exception e) {
		System.out.println("Exception!: "+e.getMessage());
		throw e;
	}
	finally {
		cleanup(rs, stmt, null);
	}

	return count==1;
}


 /**
  * <p>Looks up a user's password.</p>
  *
  * @param username A String containing the user's name.
  *
  * @return A String containing the user's password.
  *
  * @throws SQLException if the user does not exist.
  * @throws Exception if the user does not exist.
  */
  public synchronized String getUserPassword(String username)
    throws SQLException, Exception
  {
    System.out.println("Getting password for User: "+username);

    PreparedStatement stmt = null;
    ResultSet rs = null;
    String password = null;

    try {
      if (conn == null) openConnection();

    System.out.println("getUserPassword: "
      +MedRecSampleAuthConstants.GET_PASSWORD_STMT);
    stmt = conn.prepareStatement(MedRecSampleAuthConstants.GET_PASSWORD_STMT);
    stmt.setString(1, username);

    rs = stmt.executeQuery();
    if (rs.next())
      password = rs.getString(MedRecSampleAuthConstants.PASSWORD_COLUNN);
    }
    catch(Exception e) {
      System.out.println("Exception!: "+e.getMessage());
      throw e;
    }
    finally {
      cleanup(rs, stmt, null);
    }

    return password;
  }

 /**
  * <p>Looks up the groups that a user is a member of recursively.
  * That is, if user1 is a member of group1 and group1 is a member
  * of group2, then group1 and group2 are returned.</p>
  *
  * @param username A String containing the user's name.
  *
  * @return An Enumeration of Strings containing the group names.
  * Returns an empty enumeration if the user doesn't exist.
  *
  * @throws SQLException if the user does not exist.
  * @throws Exception if the user does not exist.
  */
  public synchronized Enumeration getUserGroups(String username)
    throws SQLException, Exception
  {
    System.out.println("Getting groups for User: "+username);

    PreparedStatement stmt = null;
    ResultSet rs = null;
    Vector groups = new Vector();

    try {
      if (conn == null) openConnection();

      System.out.println("getUserGroups: "+MedRecSampleAuthConstants.GET_GROUPS_STMT);
      stmt = conn.prepareStatement(MedRecSampleAuthConstants.GET_GROUPS_STMT);
      stmt.setString(1, username);

      rs = stmt.executeQuery();

      while(rs.next()) {
        groups.addElement(rs.getString(MedRecSampleAuthConstants.GROUP_NAME_COLUNN));
      }
    }
    catch(Exception e) {
      System.out.println("Exception!: "+e.getMessage());
      throw e;
    }
    finally {
      cleanup(rs, stmt, null);
    }

    return groups.elements();
  }

  /**
  *  <p>Closes/Recycles/Cleans up system resources used
  *  during the database data updates, deletes, inserts,
  *  and queries.  Handles regular Statements and
  *  PreparedStatements because PreparedStatement
  *  extends Statement.<p>
  *
  * @param pResultSet
  * @param pStmt	pStmt (a Statement or a PreparedStatement)
  * @param pConn
  *
  * @exception SQLException Thrown on sql errors
  */
  private void cleanup(ResultSet pResultSet,
                      Statement pStmt,
                      Connection pConn)
    throws SQLException
  {
    try {
      if (pResultSet!=null) {
        pResultSet.close();
        pResultSet=null;
      }
    }
    catch(SQLException e) {
      System.out.println("Cleanup failed on ResultSet.");
      throw e;
    }
    finally
    {
      try {
        if (pStmt!= null) {
          pStmt.close();
          pStmt=null;
        }
      }
      catch(SQLException e) {
        System.out.println("Cleanup failed on Statement.");
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
          System.out.println("Cleanup failed on Connection.");
          throw e;
        }
      }
    }
  }

  public void log(String str) { if (debug) System.out.println(str); }
}
