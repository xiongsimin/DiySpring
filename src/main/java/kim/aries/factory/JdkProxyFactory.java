package kim.aries.factory;

import kim.aries.proxy.JdkProxy;
import kim.aries.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author aries
 * @Data 2020-09-26
 */
public class JdkProxyFactory {

    private JdkProxyFactory() {

    }

    private static JdkProxyFactory jdkProxyFactory = new JdkProxyFactory();

    public static JdkProxyFactory getInstance() {
        return jdkProxyFactory;
    }

    public Object getProxyObj(final Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    //开启事务（关闭事务自动提交）
                    TransactionManager.getInstance().closeAutoCommit();
                    result = method.invoke(object, args);
                    TransactionManager.getInstance().commit();
                } catch (Exception e) {
                    TransactionManager.getInstance().rollback();
                    throw e;
                }
                return result;
            }
        });
    }
}
