package by.itacademy.dao;

import by.itacademy.pojos.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    void addUser(User user) throws SQLException;

    User obtainUser(String email) throws SQLException;

    List<User> obtainUserList() throws SQLException;
}

