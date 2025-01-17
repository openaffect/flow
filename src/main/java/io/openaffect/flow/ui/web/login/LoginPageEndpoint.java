package io.openaffect.flow.ui.web.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginPageEndpoint", urlPatterns = "/login")
public class LoginPageEndpoint extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Object errors = request.getSession().getAttribute("errors");
    request.setAttribute("errors", errors);
    request.getSession().removeAttribute("errors");
    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
  }

}
