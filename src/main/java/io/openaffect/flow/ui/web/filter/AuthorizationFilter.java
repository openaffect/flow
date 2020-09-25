package io.openaffect.flow.ui.web.filter;

import io.openaffect.flow.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    // Access to static content (css, js, etc.) or login page should not be restricted
    if (isPublicResource(request.getRequestURI())) {
      chain.doFilter(req, resp);
      return;
    }

    CurrentUserDTO currentUser = (CurrentUserDTO)request.getSession().getAttribute("currentUser");

    // Because users need to be logged in to access other resources, we redirect the user to the login page
    if (currentUser == null) {
      String targetUrl = request.getRequestURI();
      if (request.getQueryString() != null) {
        targetUrl += "?" + request.getQueryString();
      }
      request.getSession().setAttribute("targetUrl", targetUrl);

      ((HttpServletResponse) resp).sendRedirect("/login");
      return;
    }

    // The user is logged in and wants to access a protected resource, we don't have any restriction so we proceed
    chain.doFilter(req, resp);
  }

  boolean isPublicResource(String URI) {
    if (URI.startsWith("/assets")) {
      return true;
    }
    if (URI.startsWith("/login")) {
      return true;
    }
    if (URI.startsWith("/logout")) {
      return true;
    }
    if (URI.startsWith("/register")) {
      return true;
    }
    return false;
  }

}
