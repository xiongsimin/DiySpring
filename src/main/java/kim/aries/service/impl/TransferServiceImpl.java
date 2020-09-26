package kim.aries.service.impl;

import kim.aries.dao.TransferDao;
import kim.aries.service.TransferService;

import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class TransferServiceImpl implements TransferService {

    public void doTransfer(String from, String to, float money) throws SQLException {
        TransferDao transferDao = new TransferDao();
        //form-money
        transferDao.decreaseMoney(from, money);
        //error
        int a=1/0;
        //to+money
        transferDao.increaseMoney(to, money);
    }
}
