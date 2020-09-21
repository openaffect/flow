package io.openaffect.flow.ui.web.joke;

import io.openaffect.flow.application.ServiceRegistry;
import io.openaffect.flow.application.joke.JokeFacade;
import io.openaffect.flow.application.joke.JokesDTO;
import io.openaffect.flow.application.joke.JokesQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JokesPageHandler", urlPatterns = "/jokes")
public class JokesQueryEndpoint extends HttpServlet {

  private ServiceRegistry serviceRegistry;
  private JokeFacade jokeFacade;

  @Override
  public void init() throws ServletException {
    super.init();
    serviceRegistry = ServiceRegistry.getServiceRegistry();
    jokeFacade = serviceRegistry.getJokeFacade();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    JokesDTO jokesDTO = jokeFacade.getJokes(JokesQuery.builder()
      .safeForChildren(false)
      .build());
    request.setAttribute("jokes", jokesDTO);
    request.getRequestDispatcher("/WEB-INF/views/jokes.jsp").forward(request, response);
  }
}
