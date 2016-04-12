package by.itacademy.navigation.impl;

import by.itacademy.navigation.Command;

import static by.itacademy.resources.Constants.*;

import by.itacademy.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        NewsService newsService = NewsService.getInstance();
        newsService.addNews(request.getParameter(PARAM_HEADER), request.getParameter(PARAM_TEXT_NEWS));
        try {
            response.sendRedirect(CONTROLLER_COMMAND_MAIN_REDIRECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NEWS_PAGE;
    }
}
