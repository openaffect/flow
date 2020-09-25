package io.openaffect.flow.ui.web.login;

import io.openaffect.flow.application.ServiceRegistry;
import io.openaffect.flow.application.identitymgmt.IdentityManagementFacade;
import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.openaffect.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.openaffect.flow.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AuthenticateCommandEndpoint", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {

  private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
  private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getSession().removeAttribute("errors");

    CurrentUserDTO currentUser = null;

    AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
      .username(request.getParameter("username"))
      .clearTextPassword(request.getParameter("password"))
      .build();
    try {
      currentUser = identityManagementFacade.authenticate(authenticateCommand);
      request.getSession().setAttribute("currentUser", currentUser);
      String targetUrl = (String)request.getSession().getAttribute("targetUrl");
      targetUrl = (targetUrl != null) ? targetUrl : "/jokes";
      response.sendRedirect(targetUrl);
      return;
    } catch (AuthenticationFailedException e) {
      request.getSession().setAttribute("errors", List.of(e.getMessage())); // for better security, we would not expose the specific message
      response.sendRedirect("/login");
      return;
    }
  }

}

