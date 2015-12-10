package com.bea.medrec.security;

public class MedRecSampleAuthConstants
{
 /**
  * SQL.
  */
  public static final String GET_PASSWORD_STMT = "SELECT password "+
                                                 "FROM medrec_user "+
                                                 "WHERE username = ? "+
                                                 "AND status = 'ACTIVE'";
  public static final String GET_GROUPS_STMT = "SELECT group_name "+
                                               "FROM groups groups "+
                                               "WHERE groups.username = ?";
  public static final String GET_USERCOUNT_STMT = "SELECT COUNT(*) "+
                                                 "FROM medrec_user "+
                                                 "WHERE username = ? "+
                                                 "AND status = 'ACTIVE'";

 /**
  * Column names.
  */
  public static final String PASSWORD_COLUNN = "password";
  public static final String GROUP_NAME_COLUNN = "group_name";
}
