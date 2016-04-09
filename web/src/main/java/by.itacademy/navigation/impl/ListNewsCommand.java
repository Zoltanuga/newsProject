package by.itacademy.navigation.impl;

import by.itacademy.navigation.Command;

import static by.itacademy.resources.Constants.ADD_NEWS_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListNewsCommand implements Command {

@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ADD_NEWS_PAGE;
    }
}
