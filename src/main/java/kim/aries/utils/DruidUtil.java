package kim.aries.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class DruidUtil {
    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1/diyiocandaop?serverTimezone=PRC");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    private DruidUtil() {

    }

    public static DruidDataSource getInstance() {
        return dataSource;
    }


}
