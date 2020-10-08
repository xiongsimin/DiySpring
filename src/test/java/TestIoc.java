import kim.aries.pojo.User;
import kim.aries.utils.ClassPathApplicationContext;
import org.dom4j.DocumentException;
import org.junit.Test;

/**
 * @Author aries
 * @Data 2020-10-08
 */
public class TestIoc {

    @Test
    public void test() throws ClassNotFoundException, DocumentException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ClassPathApplicationContext context = new ClassPathApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
