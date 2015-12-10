package com.bea.medrec.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>Filter to handle request's character encoding.</p>
 *
 * @author Copyright (c) 2004 by BEA Systems All Rights Reserved.
 */
public class RequestEncodingFilter implements Filter{

  private String encoding;

  public void init(FilterConfig config) throws ServletException {
   encoding = "UTF-8";
   String enc = config.getInitParameter("encoding");
   if(enc !=null && enc.length() > 0){
     encoding = enc;
   }
  }

  public void destroy() {}

 /**
  * <p>Dispatching specified request changing its parameter's character
  * encoding. MedRec applications generate pages in utf-8. Then request 
  * parameter of normal PC browser is in utf-8. That's why this filter
  * handles it in utf-8. If some user agent works on different encoding,
  * character encoding can be specified in web.xml, filter element.
  * </p>
  */
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
  throws IOException, ServletException {

    request.setCharacterEncoding(encoding);
    chain.doFilter(request, response);
  }
}
