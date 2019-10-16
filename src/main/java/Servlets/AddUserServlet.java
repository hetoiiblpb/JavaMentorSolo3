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
        req.setCharacterEncoding("Utf-8");
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        try {
            Long age = Math.abs(Long.parseLong(req.getParameter("age")));
            if (userService.addUser(new User(name,mail,age))) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/allUsers");
            } else {
                resp.sendRedirect("/allUsers");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {

            req.setAttribute("name", name);
            req.setAttribute("mail", mail);
            req.setAttribute("message", "Требуется число");
            req.getRequestDispatcher("addUser.jsp").forward(req, resp);
        }
    }


    }


