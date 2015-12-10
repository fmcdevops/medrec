package com.bea.medrec.webservices;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;

/**
 * The home interface for MedRecWebServices EJB
 */

public interface MedRecWebServicesHome extends EJBHome {
  public MedRecWebServices create() throws CreateException, RemoteException;
}
