package Servlets;

import Model.User;
import Service.UserService;
import Util.ConfigReader;
import exception.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addUser")
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
        req.setCharacterEncoding(ConfigReader.getInstance().getCharacterEncoding());
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        Long age = Long.parseLong(req.getParameter("age"));
        try {
            if (userService.addUser(new User(name, mail, age))) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/allUsers");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.getRequestDispatcher("addUser.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}

