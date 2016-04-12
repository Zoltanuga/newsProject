package by.itacademy.navigation.impl;

import by.itacademy.pojos.News;
import by.itacademy.navigation.Command;

import static by.itacademy.resources.Constants.*;

import by.itacademy.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
      /*  UserService userService = UserService.getInstance();
        List<User> users = userService.obtainUserList();
        boolean authorizedUser = false;
        for (User user : users) {
            boolean isRegisteredEmail = user.getEmail().equals(request.getParameter(PARAM_EMAIL_INPUT));
            boolean isRegisteredPassword = user.getPassword().equals(request.getParameter(PARAM_PASSWORD_INPUT));
            if (isRegisteredEmail && isRegisteredPassword) {
                authorizedUser = true;
                break;
            }
        }
        if (authorizedUser) {*/
        NewsService newsService = NewsService.getInstance();
        List<News> newsList = newsService.obtainNewsList();
        request.setAttribute(PARAM_NEWS_LIST, newsList);
        HttpSession session = request.getSession();
        session.setAttribute(PARAM_EMAIL_INPUT, request.getParameter(PARAM_EMAIL_INPUT));
        session.setAttribute(PARAM_PASSWORD_INPUT, request.getParameter(PARAM_PASSWORD_INPUT));
        System.out.println("main command");
        return NEWS_PAGE;
        //  } else return MAIN_PAGE;
    }
}
