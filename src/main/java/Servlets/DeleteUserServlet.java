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


@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = userService.getUserById(id);
            req.setAttribute("id", id);
            req.setAttribute("name", user.getName());
            req.setAttribute("mail", user.getEmail());
            req.setAttribute("age", user.getAge());
            req.getRequestDispatcher("deleteUser.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            if (userService.deleteUser(id)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/allUsers");
            }

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}

