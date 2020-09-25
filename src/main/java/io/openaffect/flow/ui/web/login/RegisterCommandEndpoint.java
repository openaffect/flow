package io.openaffect.flow.ui.web.login;

import io.openaffect.flow.application.ServiceRegistry;
import io.openaffect.flow.application.identitymgmt.IdentityManagementFacade;
import io.openaffect.flow.application.identitymgmt.login.RegisterCommand;
import io.openaffect.flow.application.identitymgmt.login.RegistrationFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

  private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
  private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getSession().removeAttribute("errors");

    RegisterCommand registerCommand = RegisterCommand.builder()
      .username(request.getParameter("username"))
      .firstName(request.getParameter("firstName"))
      .lastName(request.getParameter("lastName"))
      .email(request.getParameter("email"))
      .clearTextPassword(request.getParameter("password"))
      .build();
    try {
      identityManagementFacade.register(registerCommand);
      request.getRequestDispatcher("/login.do").forward(request, response);
      return;
    } catch (RegistrationFailedException e) {
      request.getSession().setAttribute("errors", List.of(e.getMessage()));
      response.sendRedirect("/login");
      return;
    }
  }

}
