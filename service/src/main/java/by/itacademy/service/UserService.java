package by.itacademy.service;

import by.itacademy.dao.impl.UserDao;
import by.itacademy.pojos.User;

import java.util.List;

public class UserService {
    private UserDao userDAO = UserDao.getInstance();
    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User obtainUser(String email) {
        return userDAO.obtainUser(email);
    }

    public List<User> obtainUserList() {
        return userDAO.obtainUserList();
    }
}
