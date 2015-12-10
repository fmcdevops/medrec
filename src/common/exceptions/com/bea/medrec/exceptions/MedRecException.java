package com.bea.medrec.exceptions;

/**
 * <p>Custom MedRec general exception class.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecException extends Exception
{
  // Constructors
  public MedRecException(String errMsg) { super(errMsg); }
  public MedRecException(Throwable cause) { super(cause); }
  public MedRecException(String errMsg, Throwable cause) {
    super(errMsg, cause);
  }
}
