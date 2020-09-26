package kim.aries.dao;

import kim.aries.pojo.User;
import kim.aries.utils.ConnectionUtils;
import kim.aries.utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class UserDao {
    int addUser(User user) throws SQLException {
        Connection con = ConnectionUtils.getInstance().getCurrentThreadConn();

        String sql = "insert into user (id,name)values(" + user.getId() + ",'" + user.getName() + "')";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        return preparedStatement.executeUpdate();
    }
}
