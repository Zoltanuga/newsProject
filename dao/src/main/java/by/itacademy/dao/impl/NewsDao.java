package by.itacademy.dao.impl;

import by.itacademy.dao.database.ConnectionPool;
import by.itacademy.dao.INewsDao;
import by.itacademy.pojos.News;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewsDao implements INewsDao {
    private static Logger log = Logger.getLogger(NewsDao.class);
    private ResourceBundle queries = ResourceBundle.getBundle("DatabaseResources");
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static NewsDao instance;


    public static synchronized NewsDao getInstance() {
        if (instance == null) {
            instance = new NewsDao();
        }
        return instance;
    }

    private NewsDao() {
    }

    @Override
    public void addNews(News news) {
        Connection connection = null;


        try {
            connection = connectionPool.getConnection();

            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlInsertNews"));
            prStatement.setString(1, news.getHeader());
            prStatement.setDate(2, news.getDate());
            prStatement.setTime(3, news.getTime());
            prStatement.setString(4, news.getText());
            prStatement.executeUpdate();
            //connection.commit();
            log.info("success");
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
        } finally {
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
    public News obtainNews(int id) {
        Connection connection = null;
        ResultSet result;
        List<News> newsList = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            PreparedStatement prStatement = connection.prepareStatement(queries.getString("sqlSelectSimpleNews"));
            prStatement.setInt(1, id);
            result = prStatement.executeQuery();
            newsList = initNews(result);
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
        return newsList.get(0);
    }

    @Override
    public List<News> obtainListNews() {
        Connection connection = null;
        List<News> newsList = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queries.getString("sqLSelectNews"));
            newsList = initNews(result);
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        }
        return newsList;
    }

    public List<News> initNews(ResultSet result) throws SQLException {
        List<News> newsList = new ArrayList<>();
        while (result.next()) {
            News news = new News();
            news.setId(result.getInt("ID"));
            news.setHeader(result.getString("HEADER"));
            news.setDate(result.getDate("DATE"));
            news.setTime(result.getTime("TIME"));
            news.setText(result.getString("TEXT"));
            newsList.add(news);
        }
        return newsList;
    }
}
