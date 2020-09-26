package kim.aries.dao;

import kim.aries.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class TransferDao {

    public int decreaseMoney(String account, float money) throws SQLException {
        //从当前线程获取数据库连接
        Connection connection = ConnectionUtils.getInstance().getCurrentThreadConn();
        String sql = "update bank set money = money - ? where account = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, money);
        preparedStatement.setString(2, account);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public int increaseMoney(String account, float money) throws SQLException {
        Connection connection = ConnectionUtils.getInstance().getCurrentThreadConn();
        String sql = "update bank set money = money + ? where account = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setFloat(1, money);
        preparedStatement.setString(2, account);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
}
