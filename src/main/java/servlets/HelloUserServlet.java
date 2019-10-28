package servlets;

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
        HttpSession httpSession = req.getSession(false);
        System.out.println(httpSession.getAttribute("role"));
        httpSession.removeAttribute("role");
        resp.sendRedirect("/authorization");
        // System.out.println(httpSession.getAttribute("role"));
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
//        HttpSession httpSession = req.getSession(false);
//        System.out.println("Не работает!");
//        httpSession.removeAttribute("role");
//        //httpSession.invalidate();
//        req.getRequestDispatcher("authorization.jsp").forward(req, resp);
//    }
}

