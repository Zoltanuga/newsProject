package by.itacademy.filters;


import by.itacademy.pojos.User;
import by.itacademy.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.itacademy.resources.Constants.*;

public class AuthFilter implements Filter {

    public static final String IS_AUTHORIZED_USER = "isAuthorizedUser";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = UserService.getInstance();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        List<User> users = userService.obtainUserList();
        HttpSession session = req.getSession();
        boolean isFromRegisterFormEmail = request.getParameter(PARAM_EMAIL_REGISTER) != null;
        boolean isFromRegisterFormPassword = request.getParameter(PARAM_PASSWORD_REGISTER) != null;
        if (isFromRegisterFormEmail && isFromRegisterFormPassword) {
            filterChain.doFilter(req, res);
            session.setAttribute(IS_AUTHORIZED_USER, true);
        } else {
            if (session.getAttribute(IS_AUTHORIZED_USER) == null) {
                for (User user : users) {
                    boolean isRegisteredEmail = user.getEmail().equals(req.getParameter(PARAM_EMAIL_INPUT));
                    boolean isRegisteredPassword = user.getPassword().equals(req.getParameter(PARAM_PASSWORD_INPUT));
                    if (isRegisteredEmail && isRegisteredPassword) {
                        session.setAttribute(IS_AUTHORIZED_USER, true);
                        break;
                    } else {
                        session.setAttribute(IS_AUTHORIZED_USER, false);
                    }
                }
            }
            if ((Boolean) session.getAttribute(IS_AUTHORIZED_USER) /*|| isRegistered*/) {
                filterChain.doFilter(req, res);
            } else {
                res.sendRedirect("/news");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
