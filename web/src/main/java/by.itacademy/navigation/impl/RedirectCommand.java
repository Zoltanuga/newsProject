package by.itacademy.navigation.impl;

import by.itacademy.pojos.News;
import by.itacademy.navigation.Command;
import by.itacademy.service.NewsService;

import static by.itacademy.resources.Constants.PARAM_NEWS_LIST;
import static by.itacademy.resources.Constants.NEWS_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectCommand implements Command {

@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        NewsService newsService = NewsService.getInstance();
        List<News> newsList = newsService.obtainNewsList();
        request.setAttribute(PARAM_NEWS_LIST, newsList);
    System.out.println("redirect conmmand");
        return NEWS_PAGE;
    }
}
