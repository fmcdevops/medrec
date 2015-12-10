package com.bea.medrec.exceptions;

/**
 * <p>Duplicate account exception class.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class DuplicateAccountException extends Exception
{
  // Constructors
  public DuplicateAccountException(String errMsg) { super(errMsg); }
  public DuplicateAccountException(Throwable cause) { super(cause); }
  public DuplicateAccountException(String errMsg, Throwable cause) {
    super(errMsg, cause);
  }
}
