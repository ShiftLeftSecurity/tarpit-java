package io.shiftleft.tarpit;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.logout();
    Principal principal = req.getUserPrincipal();
    if (principal != null) {
      throw new RuntimeException("Cannot log out the user");
    }
    resp.sendRedirect("/app");
  }
}
