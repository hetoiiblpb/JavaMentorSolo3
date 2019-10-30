package filter;

import util.DBHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(urlPatterns = "/admin/**")
public class WebFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session.getAttribute("role") == null) {
            resp.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
            resp.getWriter().println("Сначала авторизуйтесь!");
            try {
                System.out.println("Ждём 5 сек");
                Thread.sleep(5000L);
                response.sendRedirect("/authorization");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
        String currentRole = session.getAttribute("role").toString();
            switch (currentRole) {
                case ("admin"): {
                    chain.doFilter(req, resp);
                    break;
                }
                case ("user"): {
                    resp.getWriter().println("Вам сюда нельзя!!");
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("/authorization");
                    break;
                }
                default: {
                    req.setCharacterEncoding(DBHelper.getProperties().getProperty("characterEncoding"));
                    resp.getWriter().println("Ваш ранг не очевиден!");
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("/authorization");
                    break;
                }
            }
        }
    }


    @Override
    public void destroy() {

    }
}
