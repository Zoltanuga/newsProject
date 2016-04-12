package by.itacademy.dao.database;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**+
 * creates connection pull to database
 * singleton
 */
public class ConnectionPool {
    private ResourceBundle connectionsParam = ResourceBundle.getBundle("DatabaseResources");
    private static ConnectionPool instance;
    private BasicDataSource dataSource;

    private ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setDriverClassName(connectionsParam.getString("connectionDriverClass"));
        dataSource.setUrl(connectionsParam.getString("connectionUrl"));
        dataSource.setUsername(connectionsParam.getString("connectionUserName"));
        dataSource.setPassword(connectionsParam.getString("connectionPassword"));
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
