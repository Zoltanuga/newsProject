package by.itacademy.dao.impl;


import by.itacademy.dao.database.ConnectionPool;
import by.itacademy.dao.IUserDao;
import by.itacademy.pojos.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDao implements IUserDao {
    private ResourceBundle queries = ResourceBundle.getBundle("DatabaseResources");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static Logger log = Logger.getLogger(UserDao.class);
    private static UserDao instance;
    public static synchronized   UserDao getInstance(){
        if (instance==null){
            instance=new UserDao();
        }
        return instance;
    }
    private UserDao() {
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlInsertUser"));
            prStatement.setString(1, user.getEmail());
            prStatement.setString(2, user.getName());
            prStatement.setString(3, user.getSurname());
            prStatement.setString(4, user.getPassword());
            prStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
                log.error(e);
            }
            e.printStackTrace();
            log.error(e);
        }finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error(e);
                }
        }
    }

    @Override
    public User obtainUser(String email) {
        Connection connection = null;
        ResultSet result;
        List<User> users = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlSelectSimpleUser"));
            prStatement.setString(1, email);
            result = prStatement.executeQuery();
            users = initUser(result);
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
                log.error(e);
            }
            e.printStackTrace();
            log.error(e);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
        return users.get(0);
    }

    @Override
    public List<User> obtainUserList() {
        Connection connection = null;
        ResultSet result;
        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(queries.getString("sqlSelectUsers"));
            users = initUser(result);
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
                log.error(e);
            }
            e.printStackTrace();
            log.error(e);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
        return users;
    }

    public List<User> initUser(ResultSet result) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            user.setEmail(result.getString("EMAIL"));
            user.setName(result.getString("NAME"));
            user.setSurname(result.getString("SURNAME"));
            user.setPassword(result.getString("PASSWORD"));
            userList.add(user);
        }
        return userList;
    }
}
