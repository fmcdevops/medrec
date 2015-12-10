package com.bea.medrec.beans;

import com.bea.medrec.utils.MedRecWebAppUtils;
import com.bea.medrec.value.User;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>User bean to hold user login and username/password registration.
 * This form has the following fields,
 * with default values in square brackets:
 * <ul>
 * <li><b>password</b> - Entered password value
 * <li><b>username</b> - Entered username value
 * </ul>
 * </p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public final class UserBean extends BaseBean {

  // Instance Variables
  private String username = null;
  private String password = null;

  // Constructors
  public UserBean() {
  }

  // Getters
  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  // Setters
  public void setUsername(String username) {
    this.username = MedRecWebAppUtils.cleanParam(username);
  }

  public void setPassword(String password) {
    this.password = MedRecWebAppUtils.cleanParam(password);
  }

  // Public Methods
  public void reset() {
    this.password = "";
    this.username = "";
  }

  /**
   * <p>Validate registration.</p>
   *
   * @param mapping
   * @param request
   *
   * @return ActionErrors
   */
  public ActionErrors validate(ActionMapping mapping,
                               HttpServletRequest request) {
    ActionErrors errors = new ActionErrors();
    // only validate if the user has clicked "Login"
    if ("Login".equals(request.getParameter("action"))) {
      errors = super.validate(mapping, request);
    }
    return errors;
  }
  /**
   * <p>Converts user presentation bean to user value object.</p>
   *
   * @return User
   */
  public User toUser() {
    return new User(getUsername(), getPassword(), null);
  }

  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("UserBean [");
    str.append("Username: " + username);
    str.append(" | Password: " + printPassword());
    str.append("]");

    return str.toString();
  }

  private String printPassword() {
    StringBuffer pwd = new StringBuffer();
    for (int i = 0; i < password.length(); i++) {
      pwd.append("*");
    }
    return pwd.toString();
  }
}
