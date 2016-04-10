package by.itacademy.filters;


import by.itacademy.pojos.News;
import by.itacademy.pojos.User;
import by.itacademy.service.NewsService;
import by.itacademy.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.itacademy.resources.Constants.*;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = UserService.getInstance();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        List<User> users = userService.obtainUserList();
        // boolean isAuthorizedUser = false;
        HttpSession session = req.getSession();
        //session.setAttribute("isAuthorizedUser", false);

        System.out.println("authfilter");
        if (session.getAttribute("isAuthorizedUser") == null) {
            for (User user : users) {
                boolean isRegisteredEmail = user.getEmail().equals(req.getParameter(PARAM_EMAIL_INPUT));
                boolean isRegisteredPassword = user.getPassword().equals(req.getParameter(PARAM_PASSWORD_INPUT));
                if (isRegisteredEmail && isRegisteredPassword) {
                    session.setAttribute("isAuthorizedUser", true);
                    break;
                } else {
                    session.setAttribute("isAuthorizedUser", false);
                }
            }
        }
        if ((Boolean) session.getAttribute("isAuthorizedUser")) {
            filterChain.doFilter(req, res);
            System.out.println("Athorized");
        } else {
            res.sendRedirect("/news");
            System.out.println("unAthorized");
        }
    }

    @Override
    public void destroy() {

    }
}
