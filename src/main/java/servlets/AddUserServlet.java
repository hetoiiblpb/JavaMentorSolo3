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


@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        try {
            if (userService.addUser(new User(name, password, mail, age))) {
                resp.setStatus(HttpServletResponse.SC_OK);
                req.getRequestDispatcher(req.getContextPath().concat("/").concat("allUsers.jsp")).forward(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.getRequestDispatcher(req.getContextPath().concat("/").concat("addUser.jsp")).forward(req, resp);
            }
        } catch (DBException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }
}

