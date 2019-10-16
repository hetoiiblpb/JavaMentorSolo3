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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/allUsers")
public class AllUsersServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getAllUsers();
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.setAttribute("users", userList);
        req.getRequestDispatcher("allUsers.jsp").forward(req, resp);
    }
}
