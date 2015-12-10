package com.bea.medrec.utils;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import org.apache.struts.util.MessageResources;

/**
 * <p>Provides access to MedRec messages in a properties file.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class MedRecMessageProperties implements Serializable
{
  private MessageResources messageResources = null;
  private Locale locale = null;

  // Private constructor
  private MedRecMessageProperties(Locale pLocale,
                                  MessageResources pMessageResources)
  {
    locale = pLocale;
    messageResources = pMessageResources;
  }

  /**
   * <p>Get instance.</p>
   *
   * @param locale
   * @return MedRecMessageProperties
   */
  public static MedRecMessageProperties getInstance(Locale locale,
    MessageResources pMessageResources)
  {
    return new MedRecMessageProperties(locale, pMessageResources);
  }

  /**
   * <p>Retrieves a localized message based on a String key.</p>
   *
   * @param pKey
   * @return String
   * @throws MissingResourceException
   */
  public String getMessage(String pKey)
    throws MissingResourceException
  {
    return messageResources.getMessage(locale, pKey);
  }
}

