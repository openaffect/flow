package io.openaffect.flow.ui.web.joke;

import io.openaffect.flow.application.ServiceRegistry;
import io.openaffect.flow.application.joke.JokeFacade;
import io.openaffect.flow.application.joke.ProposeJokeCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitJokeCommandHandler", urlPatterns = "/submitJoke.do")
public class ProposeJokeCommandEndpoint extends HttpServlet {

  private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
  private JokeFacade jokeFacade = serviceRegistry.getJokeFacade();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ProposeJokeCommand command = ProposeJokeCommand.builder()
      .author("anonymous")
      .text(request.getParameter("text"))
      .build();

    jokeFacade.proposeJoke(command);
    response.sendRedirect("/jokes");

  }

}
