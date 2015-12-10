package com.bea.medrec.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * <p>Controls Log4j logging level.  Implemented as a servlet so
 * that logging levels can be adjusted by redeploying the webapp.
 * Log file is determined by "log4j.config" set as a system
 * property in the server startup file.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems. All Rights Reserved.
 */
public class AdminLog4jInit extends HttpServlet {

  public void init() {

    int debug = 0;
    String value;
    String logFile;

    logFile = System.getProperty("log4j.config");
    value = getServletConfig().getInitParameter("debug");

    try {
	    debug = Integer.parseInt(value);
    } catch (Throwable t) {
        debug = 0;
    }

    if (debug >= 1) {
      SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy H:mm:ss a z");
      Date today = new Date();
      String output = "<"+formatter.format(today)+"> <Debug> <MedRec>";
      System.out.println(output+" Admin app log4j prop file: "+logFile);
    }

    // If the log4j-init-file is not set, then no point in trying
    if(logFile != null) {
      if (logFile.toString().toLowerCase().endsWith(".xml")) {
          DOMConfigurator.configure(logFile);
      } else {
        PropertyConfigurator.configure(logFile);
      }
    }
  }
}
