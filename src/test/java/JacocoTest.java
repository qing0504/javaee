import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.annotation.CheckForNull;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * VM options:-javaagent:/Users/martin/install/jar/org.jacoco.agent-0.8.1-runtime.jar=output=tcpserver,append=true,address=127.0.0.1,port=6300,includes=*
 * @author wanchongyang
 * @date 2018/10/26 1:46 PM
 */
public class JacocoTest {
    @Test
    public void test() {
        Field[] declaredFields = Student.class.getDeclaredFields();
        Arrays.stream(declaredFields).filter(Field::isSynthetic).forEach(f -> System.out.println("field: " + f.getName()));

        Method[] declaredMethods = Student.class.getDeclaredMethods();
        Arrays.stream(declaredMethods).filter(Method::isSynthetic).forEach(m -> System.out.println("method: " + m.getName()));

        Student student = new Student();
        student.setId(1);
        Map<String, Object> paramMap = objectToMap(student);
        System.out.println(JSON.toJSONString(paramMap));
    }

    private static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                if (getter != null) {
                    CheckForNull annotation = getter.getAnnotation(CheckForNull.class);
                    Object value = getter.invoke(obj);
                    if (annotation != null && value == null) {
                        throw new IllegalArgumentException("Property[" + key + "] is required");
                    }

                    if (value != null) {
                        map.put(key, value);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("convert object to map failed");
            e.printStackTrace();
        }

        return map;
    }

    class Student {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
