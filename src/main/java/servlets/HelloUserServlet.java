package servlets;

import exception.DBException;
import model.User;
import service.UserService;
import util.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/helloUser")
public class HelloUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("helloUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        try {
            User user = userService.verifyUserPassword(name, password);
            if (user != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("name", user.getName());
                resp.sendRedirect("/helloUser");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.getRequestDispatcher("authorization.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
        HttpSession httpSession = req.getSession();
        System.out.println(httpSession.getAttribute("name"));
        httpSession.invalidate();
        resp.sendRedirect("/authorization");
    }
}

