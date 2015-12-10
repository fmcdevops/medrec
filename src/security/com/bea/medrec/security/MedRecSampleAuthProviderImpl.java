package com.bea.medrec.security;

import java.util.HashMap;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import weblogic.management.security.ProviderMBean;
import weblogic.security.provider.PrincipalValidatorImpl;
import weblogic.security.spi.AuthenticationProvider;
import weblogic.security.spi.IdentityAsserter;
import weblogic.security.spi.PrincipalValidator;
import weblogic.security.spi.SecurityServices;

/**
 * The sample authenticator's runtime implementation.
 *
 * It also provides its own login module implementation
 * (MedRecLoginModuleImpl).
 *
 * For authentation mode, it configures its login module
 * to validate that the user's password is correct
 * and that the user exists.  It has the login module put
 * principals for the user and the user's groups into
 * the subject.
 *
 * Rather than write its own principal and principal validator
 * implementations, it uses the standard weblogic ones:
 * - weblogic.security.principal.WLSGroupImpl
 * - weblogic.security.principal.WLSUserImpl
 * - weblogic.security.provider.PrincipalValidatorImpl
 *
 * It stores the user and group definitions in a properties
 * file.  The MedRecAuthenticatorDatabase class handles
 * the properties file.  This class just delegates
 * to the database class.

 * Note: The sample authenticator's mbean's ProviderClassName
 * attribute must be set the the name of this class.
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class MedRecSampleAuthProviderImpl implements AuthenticationProvider
{
  private String                      description; // a description of this provider
  private MedRecSampleAuthDatabase    database;    // manages the user and group definitions for this provider
  private LoginModuleControlFlag      controlFlag; // how this provider's login module should be used during the JAAS login
  private MedRecSampleAuthenticatorMBean myMBean;

  /**
   * <p>Initialize the sample authenticator.
   *
   * It either creates or re-opens the file containing the
   * sample authenticator's user and group definitions.</p>
   *
   * @param mbean A ProviderMBean that holds the sample authenticator's
   * configuration data.  This mbean must be an instance of the sample
   * authenticator's mbean.
   *
   * @param services The SecurityServices gives access to the auditor
   * so that the provider can to post audit events.
   * The sample authenticator doesn't use this parameter.
   */
  public void initialize(ProviderMBean mbean, SecurityServices services)
  {

    // Cast the mbean from a generic ProviderMBean to a SampleAuthenticatorMBean.
    myMBean = (MedRecSampleAuthenticatorMBean)mbean;

    // Set the description to the sample authenticator's mbean's description and version
    description = myMBean.getDescription() + "\n" + myMBean.getVersion();

    // Instantiate the helper that manages this provider's user and group definitions

    // Extract the JAAS control flag from the sample authenticator's mbean.
    // This flag controls how the sample authenticator's login module is used
    // by the JAAS login, both for authentication and for identity assertion.
    String flag = myMBean.getControlFlag();
    if (flag.equalsIgnoreCase("REQUIRED")) {
      controlFlag = LoginModuleControlFlag.REQUIRED;
    } else if (flag.equalsIgnoreCase("OPTIONAL")) {
      controlFlag = LoginModuleControlFlag.OPTIONAL;
    } else if (flag.equalsIgnoreCase("REQUISITE")) {
      controlFlag = LoginModuleControlFlag.REQUISITE;
    } else if (flag.equalsIgnoreCase("SUFFICIENT")) {
      controlFlag = LoginModuleControlFlag.SUFFICIENT;
    } else {
      throw new IllegalArgumentException("invalid flag value" + flag);
    }
  }

  /**
   * <p>Get the sample authenticator's description.</p>
   *
   * @return A String containing a brief description of the sample authenticator.
   *
   * @see weblogic.security.spi.SecurityProvider
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * <p>Shutdown the sample authenticator.
   *
   * A no-op.</p>
   *
   * @see weblogic.security.spi.SecurityProvider
   */
  public void shutdown()
  {
    System.out.println("MedRecAuthenticationProviderImpl.shutdown");
  }

  /**
   * <p>Create a JAAS AppConfigurationEntry (which tells JAAS
   * how to create the login module and how to use it).
   * This helper method is used both for authentication mode
   * and identity assertion mode.</p>
   *
   * @param options A HashMap containing the options to pass to the
   * sample authenticator's login module.  This method adds the
   * "database helper" object to the options.  This allows the
   * login module to access the users and groups.
   *
   * @return An AppConfigurationEntry that tells JAAS how to use the sample
   * authenticator's login module.
   */
  private AppConfigurationEntry getConfiguration(HashMap options)
  {
    // add the "database helper" object to the options so that the
    // login module can access the user and group definitions
    options.put("database", new MedRecSampleAuthDatabase(myMBean));

    // make sure to specify the sample authenticator's login module
    // and to use the control flag from the sample authenticator's mbean.
    return new
      AppConfigurationEntry(
        "com.bea.medrec.security.MedRecLoginModuleImpl",
        controlFlag,
        options
      );
  }

  /**
   * <p>Create a JAAS AppConfigurationEntry (which tells JAAS
   * how to create the login module and how to use it) when
   * the sample authenticator is used to authenticate (vs. to
   * complete identity assertion).</p>
   *
   * @return An AppConfigurationEntry that tells JAAS how to use the sample
   * authenticator's login module for authentication.
   */
  public AppConfigurationEntry getLoginModuleConfiguration()
  {
    // Don't pass in any special options.
    // By default, the sample authenticator's login module
    // will authenticate (by checking that the passwords match).
    HashMap options = new HashMap();
    return getConfiguration(options);
  }

  /**
   * <p>Create a JAAS AppConfigurationEntry (which tells JAAS
   * how to create the login module and how to use it) when
   * the sample authenticator is used to complete identity
   * assertion (vs. to authenticate).</p>
   *
   * @return An AppConfigurationEntry that tells JAAS how to use the sample
   * authenticator's login module for identity assertion.
   */
  public AppConfigurationEntry getAssertionModuleConfiguration()
  {
    // Pass an option indicating that we're doing identity
    // assertion (vs. authentication) therefore the login module
    // should only check that the user exists (instead of checking
    // the password)
    HashMap options = new HashMap();
    options.put("IdentityAssertion","true");
    return getConfiguration(options);
  }

  /**
   * <p>Return the principal validator that can validate the
   * principals that the authenticator's login module
   * puts into the subject.
   *
   * Since the sample authenticator uses the built in
   * WLSUserImpl and WLSGroupImpl principal classes, just
   * returns the built in PrincipalValidatorImpl that knows
   * how to handle these kinds of principals.</p>
   *
   * @return A PrincipalValidator that can validate the
   * principals that the sample authenticator's login module
   * puts in the subject.
   */
  public PrincipalValidator getPrincipalValidator()
  {
    return new PrincipalValidatorImpl();
  }

  /**
   * <p>Returns this providers identity asserter object.</p>
   *
   * @return null since the sample authenticator doesn't
   * support identity assertion (that is, mapping a token
   * to a user name).  Do not confuse this with using a
   * login module in identity assertion mode where the
   * login module shouldn't try to validate the user.
   */
  public IdentityAsserter getIdentityAsserter()
  {
    return null;
  }
}
