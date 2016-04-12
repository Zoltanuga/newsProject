package by.itacademy.service;


import by.itacademy.dao.impl.NewsDao;
import by.itacademy.pojos.News;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

/**+
 * service layer for news entities
 * singleton
 */
public class NewsService {
    private NewsDao newsDAO = NewsDao.getInstance();
    private static NewsService instance;

    public static synchronized NewsService getInstance() {
        if (instance == null) {
            instance = new NewsService();
        }
        return instance;
    }

    private NewsService() {
    }

    /**+
     * add news to DB
     * time and date parameters automatically generated at the moment of perform this method
     * @param header is for news
     * @param text   is body of news
     */
    public void addNews(String header, String text) {

        Calendar calendar = Calendar.getInstance();
        Time time = new Time(calendar.getTime().getTime());
        Date date = new Date(calendar.getTime().getTime());
        if (!(header.isEmpty() && text.isEmpty())) {
            News news = new News(header, date, time, text);
            newsDAO.addNews(news);
        }

    }

    /**+
     *
     * @param id int
     * @return news by ID
     */
    public News obtainNews(int id) {

        return newsDAO.obtainNews(id);
    }

    /**
     * +
     *
     * @return list of news
     */
    public List<News> obtainNewsList() {

        return newsDAO.obtainListNews();
    }
}
