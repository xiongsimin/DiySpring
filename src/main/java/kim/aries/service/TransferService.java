package kim.aries.service;

import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public interface TransferService {
    void doTransfer(String from, String to, float money) throws SQLException;
}
