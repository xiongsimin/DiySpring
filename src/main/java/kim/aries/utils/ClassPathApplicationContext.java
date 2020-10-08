package kim.aries.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author aries
 * @Data 2020-10-08
 */
public class ClassPathApplicationContext {
    private String xmlPath;

    private static ConcurrentHashMap defaultBeanMap = new ConcurrentHashMap();

    public ClassPathApplicationContext(String xmlPath) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        this.xmlPath = xmlPath;
        SAXReader saxReader = new SAXReader();
        Document resourceDoc = saxReader.read(this.getClass().getClassLoader().getResource(xmlPath));
        Element rootElement = resourceDoc.getRootElement();
        List<Element> elements = rootElement.elements("bean");
        for (Element e : elements) {
            Attribute beanId = e.attribute("id");
            Attribute clazz = e.attribute("class");
            Class<?> forName = Class.forName(clazz.getValue());
            //注：当类中没有无参构造器时，此处会报错
            Object newInstance = forName.newInstance();
            //初始化对象属性
            List<Element> properties = e.elements("property");
            for (Element property : properties) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                Field declaredField = forName.getDeclaredField(name);
                declaredField.setAccessible(true);
                Object processedVal = new Object();
                //todo 考虑更多种情况
                if (Integer.class.equals(declaredField.getType())) {
                    processedVal = Integer.parseInt(value);
                } else if (float.class.equals(declaredField.getType())) {
                    processedVal = Float.parseFloat(value);
                } else {
                    processedVal = value;
                }
                declaredField.set(newInstance, processedVal);
            }
            defaultBeanMap.put(beanId.getValue(), newInstance);
        }
    }

    public static Object getBean(String beanId) {
        return defaultBeanMap.get(beanId);
    }
}
