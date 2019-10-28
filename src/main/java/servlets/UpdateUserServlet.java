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
import java.io.IOException;


@WebServlet("/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        try {
            if (userService.updateUser(new User(id, name, password, mail, age))) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/admin");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.getRequestDispatcher("updateUser.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            User user = userService.getUserById(id);
            req.setAttribute("id", user.getId());
            req.setAttribute("name", user.getName());
            req.setAttribute("mail", user.getEmail());
            req.setAttribute("age", user.getAge());
            req.setAttribute("password", user.getPassword());
            req.getRequestDispatcher(req.getContextPath().concat("/").concat("updateUser.jsp")).forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (DBException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
