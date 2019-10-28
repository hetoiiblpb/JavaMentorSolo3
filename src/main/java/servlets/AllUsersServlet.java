package servlets;

import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin")
public class AllUsersServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<User> userList = userService.getAllUsers();
            req.setAttribute("users", userList);
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(req.getContextPath().concat("/").concat("allUsers.jsp")).forward(req, resp);
    }
}
