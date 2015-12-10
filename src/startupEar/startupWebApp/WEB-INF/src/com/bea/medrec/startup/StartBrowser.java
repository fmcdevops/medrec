package com.bea.medrec.startup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.*;
import javax.naming.*;
import weblogic.management.MBeanHome;
import weblogic.management.runtime.ServerRuntimeMBean;
import weblogic.management.configuration.ApplicationMBean;

/**
 * This startup class displays a message in the server shell informing 
 * the user which URL can be used to access the Medical Records Index.jsp. 
 * On Windows, a browser will be automatically launched to the 
 * appropriate URL. 
 *
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

public class StartBrowser extends HttpServlet implements Runnable {   
  private String port;
  private String host;
  private Socket socket;
  private boolean debug   = false;
  private static int SLEEPTIME = 500;
  private static final String TARGET_APP="MedRecEAR";

  /**
   * Constructs a StartBrowser with the specified host and port values.
   *
   * @param h                 hostname
   * @param p                 listen port 
   */
  public StartBrowser(String h, String p, boolean debug) {
    this.host = h;
    this.port = p;
    this.debug = debug;
 }

  /**
   * Default constructor.
   *
   */
   public StartBrowser() {
   }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    String debugTmp = getInitParameter("debug");
    if (debugTmp != null) {
      try {debug = Boolean.valueOf(debugTmp).booleanValue(); }
      catch(Exception ignore) {}
      if (debug) System.out.println("\n\nDEBUG in StartBrowser\n\n " );
    }

    startup();
  }

  public void service(HttpServletRequest req, HttpServletResponse res) 
    throws ServletException, IOException {
    throw new IOException ("This servlet is only for initiailzing a \"startup class\"");
  }
  /**
   * Loops indefinitely trying to create a socket to host/port
   * waits sleepTime in between each try.
   * On a successful socket create, start browser.
   */
  public void run() {
    if (debug) System.out.println("\n\nStartBrowser begin run\n\n");
    boolean loop = true;
    while (loop) {
      try {
        socket = new Socket(host, new Integer(port).intValue()); 
        socket.close();

        //launch browser
        String[] cmdArray = new String[3];
        cmdArray[0] = "beaexec.exe";
        cmdArray[1] = "-target:browser";
        cmdArray[2] = "-command:\"http://"+host+":"+port+"/index.jsp\"";
        try {
          Process p = Runtime.getRuntime().exec(cmdArray);
          p.getInputStream().close();
          p.getOutputStream().close();
          p.getErrorStream().close();
        }
        catch (IOException ioe) {
          if(debug)  ioe.printStackTrace();
        }
        loop = false;
      } catch (Exception e) {
        if (debug)  e.printStackTrace();
        try {
          Thread.sleep(SLEEPTIME); // try every 500 ms
        }
        catch (InterruptedException ie) {}
        finally {
          try {
            socket.close();
          } catch (Exception se) {}
        }
      }
    }
    if (debug) System.out.println("\n\nStartBrowser end run\n\n");
  }

  /**
   * Print message in the server shell informing 
   * the user which URL can be used to access the Med Rec application. 
   * On Windows, start a new thread to launch the browser.
   */
  public void startup() 
  {

    if (!getListenAddress())
      // probably MedRecEAR is not deployed on that server
      return;

    String os = System.getProperty("os.name");
    if (os.indexOf("Windows") != -1) {
      System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println(" After the server has booted, your browser should \n" +
                         " automatically launch and point to the WebLogic Server \n" +
                         " Index running on this server. If your browser \n" +
                         " fails to launch, point your browser to the URL \n" +
                         " \"http://"+host+":"+port+"/index.jsp\"");
      System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      Thread t = new Thread(new StartBrowser(host, port, debug), "StartBrowser");
      t.start();
    } 
    else {
      System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println(" After the server has booted, point your browser \n" +
                         " to the URL \"http://"+host+":"+port+"/index.jsp\" \n" +
                         " to view the WebLogic Server Index running \n" +
                         " on this server.");
      System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    return;
  }

  private boolean getListenAddress(){
    ServerRuntimeMBean serverRuntime = null;
    Set mbeanSet = null;
    Iterator mbeanIterator = null;
    Context ctx = null;
    boolean found;

    try{
      // get local mbean home
      ctx = new InitialContext();
      MBeanHome home = (MBeanHome)ctx.lookup(MBeanHome.LOCAL_JNDI_NAME);

      // figure out if MedRecEAR is deployed this server
      mbeanSet = home.getMBeansByType("ApplicationConfig");
      mbeanIterator = mbeanSet.iterator();
      found = false;
      while (mbeanIterator.hasNext()) {
        ApplicationMBean amb = (ApplicationMBean)mbeanIterator.next();
        if (amb.getName().equals(TARGET_APP)){
          found = true;
          break;
        }
      }
      if (!found){
        if (debug)
          System.out.println("\n\nCan't find " + TARGET_APP + "\n\n");
        ctx.close();
        return false;
      }
     
      // find server runtime mbean
      mbeanSet = home.getMBeansByType("ServerRuntime");
      if (debug)
        System.out.println("\n\nSize of mbeanSet " + mbeanSet.size() + "\n\n");

      mbeanIterator = mbeanSet.iterator();
      if(!mbeanIterator.hasNext()) {
        if (debug)
          System.out.println("\n\nCan't find ServerRuntime\n\n");
        ctx.close();
        return false;
      }
      serverRuntime = (ServerRuntimeMBean)mbeanIterator.next();

      // return the server url in the result array
      host = serverRuntime.getListenAddress();
      // getListenAddress might return something like: santiago/172.17.9.220
      int i = host.indexOf('/');
      if ( i > 0 )
        host = host.substring(0, i);
      else {
        if ( i == 0 )
          host = host.substring(1);
      }
      port = Integer.toString( serverRuntime.getListenPort() ); 
      ctx.close();
      return true;
    } catch( NamingException ne ) {
      if (debug)
        System.out.println("\n\nOpps! naming exception " + ne + "\n\n");
      return false;
    }
  }
}


