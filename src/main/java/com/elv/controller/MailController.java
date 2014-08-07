package com.elv.controller;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {

//  @Context UriInfo info;
//  @Context HttpHeaders headers;
//  @Context Request request;
//  @Context SecurityContext secCtx;
//  @Context Providers providers;
//  
//  @Context ServletConfig servletConfig;
//  @Context ServletContext servletContext;


  @RequestMapping(value="/sendmail", method = RequestMethod.GET)
  public Response sendMail(ModelMap model) {

      System.out.println("in");
      
      String context = "body working";
      
      return Response.status(200).entity(context).build();
      
  }
  
  
    
}
