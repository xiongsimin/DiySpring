package kim.aries.utils;

import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class TransactionManager {
    private TransactionManager() {

    }

    private static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance() {
        return transactionManager;
    }

    /**
     * 关闭事务自动提交
     *
     * @throws SQLException
     */
    public void closeAutoCommit() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public void commit() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().commit();
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().rollback();
    }

    /**
     * 关闭连接
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().close();
    }

}
