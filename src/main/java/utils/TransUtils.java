package utils;

import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author HuK
 * @version 1.0.0
 * @date 2021/3/2 14:45
 */
public class TransUtils {

    public static <T> void transToDtoObject(T t , Element element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (element != null && t != null) {
            Class<?> clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Method method;
            for (Field field:fields) {
                String fieldName = field.getName();
                Class<?> cls = field.getType();
                method = clazz.getMethod(getSetMethodByField(fieldName), cls);
                String resourceFiledValue = element.attributeValue(fieldName);
                System.out.println(fieldName + "=" + resourceFiledValue + ", type=" + field.getType().getName());
                method.invoke(t, realTypeValue(resourceFiledValue, cls));
            }
        }
    }

    private static String getSetMethodByField(String name){
        String first = name.substring(0, 1);
        String after = name.substring(1);
        return "set"+first.toUpperCase()+after;
    }

    private static String getGetMethodByField(String name){
        String first = name.substring(0, 1);
        String after = name.substring(1);
        return "get"+first.toUpperCase()+after;
    }

    private static String getIsMethodByField(String name){
        String first = name.substring(0, 1);
        String after = name.substring(1);
        return "is"+first.toUpperCase()+after;
    }

    public static Object realTypeValue(String value, Class<?> cls) {
        String type = cls.getName();
        switch (type) {
            case "int": {
                return Integer.parseInt(value);
            }
            case "java.lang.Integer": {
                return Integer.valueOf(value);
            }
            case "boolean": {
                return Boolean.parseBoolean(value);
            }
            case "java.lang.Boolean": {
                return Boolean.valueOf(value);
            }
            default: {
                return value;
            }
        }

    }
}

    