package by.itacademy.controller;

import by.itacademy.navigation.*;

import static by.itacademy.resources.Constants.PARAM_COMMAND;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request, response);
    }

    private void performAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentCommand = request.getParameter(PARAM_COMMAND);
        Command command = CommandFactory.getCommand(currentCommand.toUpperCase());
        String nextPage = command.execute(request, response);
        System.out.println("command  :" + currentCommand);
        if (!response.isCommitted()) {
            System.out.println("!response.isCommitted()");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
            requestDispatcher.forward(request, response);
        }
    }
}
