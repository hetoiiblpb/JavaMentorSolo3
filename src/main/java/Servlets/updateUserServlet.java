package Servlets;

import Model.User;
import Service.UserService;
import exception.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/updateUser")
public class updateUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("Utf-8");
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String mail = req.getParameter("mail");
            Long age = Long.parseLong(req.getParameter("age"));
            if (userService.updateUser(new User(id, name, mail, age))) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/allUsers");
            }
        } catch (DBException e) {
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
            req.getRequestDispatcher("updateUser.jsp").forward(req, resp);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
