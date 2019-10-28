package filter;

import util.DBHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(urlPatterns = "/admin/*")
public class WebFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role") == null) {
            resp.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
            resp.getWriter().println("Сначала авторизуйтесь!");
            try {
                System.out.println("Ждём 5 сек");
                Thread.currentThread().wait(5000L);
                req.getRequestDispatcher("authorization.jsp").forward(req, resp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
        String currentRole = session.getAttribute("role").toString();
            switch (currentRole) {
                case ("admin"): {
                    chain.doFilter(req, resp);
                }
                case ("user"): {
                    req.getRequestDispatcher("/helloUser").forward(req, resp);
                }
                default: {
                    req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
                    resp.getWriter().println("Ваш ранг не очевиден!");
                }
            }
        }
    }


    @Override
    public void destroy() {

    }
}
