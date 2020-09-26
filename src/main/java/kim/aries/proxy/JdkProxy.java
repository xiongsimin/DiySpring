package kim.aries.proxy;

import kim.aries.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class JdkProxy implements InvocationHandler {
    TransactionManager transactionManager;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            transactionManager.closeAutoCommit();
            result = method.invoke(proxy, args);
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
        }
        return result;
    }
}
