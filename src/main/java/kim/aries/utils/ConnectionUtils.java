package kim.aries.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class ConnectionUtils {

    private ConnectionUtils() {

    }

    private static ConnectionUtils connectionUtils = new ConnectionUtils();

    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }

    //存储当前线程的连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public Connection getCurrentThreadConn() throws SQLException {
        Connection connection = threadLocal.get();
        //从当前线程取连接，如果为空，从线程池拿，并放入当前线程
        if (connection == null) {
            connection = DruidUtil.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
