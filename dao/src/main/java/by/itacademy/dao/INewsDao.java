package by.itacademy.dao;


import by.itacademy.pojos.News;

import java.sql.SQLException;
import java.util.List;

public interface INewsDao {
    void addNews(News news) throws SQLException;

    News obtainNews(int id) throws SQLException;

    List<News> obtainListNews() throws SQLException;
}
