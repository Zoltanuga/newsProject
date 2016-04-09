package by.itacademy.pojos;

import java.sql.Date;
import java.sql.Time;

public class News {
    private int id;
    private String header;
    private Date date;
    private Time time;
    private String text;

    public News() {
    }

    public News(String header, Date date, Time time, String text) {
        this.header = header;
        this.date = date;
        this.time = time;
        this.text = text;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", text='" + text + '\'' +
                '}';
    }
}
