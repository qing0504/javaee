package com.component.aes;

import com.common.utils.AESUtil;
import com.component.aes.annotation.Decrypt;
import com.component.aes.annotation.Encrypt;
import com.component.aes.annotation.Ignore;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * JavaBean对象自动加、解密
 *
 * @author wanchongyang
 * @date 2018/5/6 下午2:20
 */
public class BeanConverter {
    private static final ConcurrentHashMap<Field, Method> FIELD_GET_METHOD_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Field, Method> FIELD_SET_METHOD_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Class<?>, Field[]> ENCRYPT_FIELDS_CASH = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Class<?>, Field[]> DECRYPT_FIELDS_CASH = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Class<?>, Field[]> FIELDS_CASH = new ConcurrentHashMap<>();

    private static final String METHOD_GET_PREFIX = "get";

    private static final String METHOD_SET_PREFIX = "set";

    private BeanConverter() {

    }

    /**
     * 源对象o转换为加密目标对象T
     * @param o 源对象
     * @param clazz 加密目标class对象
     * @param encryptPassword 加密明文密钥
     * @return T 实例对象
     */
    public static <T> T toEncrypt(Object o, Class<T> clazz, final String encryptPassword) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = getFields(clazz);
            if (fields != null && fields.length > 0) {
                if (clazz.isAnnotationPresent(Encrypt.class)) {
                    for (Field f : fields) {
                        Method getMethod = getMethod(f.getName(), o.getClass(), true);
                        Object value = getMethod.invoke(o);
                        if (value != null) {
                            if (value.getClass() == String.class && "".equals(value)) {
                                continue;
                            }

                            Method setMethod = getSetterMethod(clazz, f);
                            if (f.isAnnotationPresent(Ignore.class)) {
                                setMethod.invoke(t, value);
                            } else {
                                setMethod.invoke(t, AESUtil.encrypt(String.valueOf(value), encryptPassword));
                            }
                        }
                    }
                } else {
                    for (Field f : fields) {
                        Method getMethod = getMethod(f.getName(), o.getClass(), true);
                        Object value = getMethod.invoke(o);
                        if (value != null) {
                            if (value.getClass() == String.class && "".equals(value)) {
                                continue;
                            }

                            Method setMethod = getSetterMethod(clazz, f);
                            if (f.isAnnotationPresent(Encrypt.class)) {
                                setMethod.invoke(t, AESUtil.encrypt(String.valueOf(value), encryptPassword));
                            } else {
                                setMethod.invoke(t, value);
                            }
                        }
                    }
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 源对象o转换为解密目标对象T
     * @param o 源对象
     * @param clazz 解密目标class对象
     * @param decryptPassword 解密明文密钥
     * @return T 实例对象
     */
    public static <T> T toDecrypt(Object o, Class<T> clazz, final String decryptPassword) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = getFields(clazz);
            if (fields != null && fields.length > 0) {
                if (clazz.isAnnotationPresent(Decrypt.class)) {
                    for (Field f : fields) {
                        Method getMethod = getMethod(f.getName(), o.getClass(), true);
                        Object value = getMethod.invoke(o);
                        if (value != null) {
                            Method setMethod = getSetterMethod(clazz, f);
                            if (f.isAnnotationPresent(Ignore.class)) {
                                setMethod.invoke(t, value);
                            } else {
                                Object decryptValue = getClassTypeValue(f.getType(), AESUtil.decrypt(String.valueOf(value), decryptPassword));
                                setMethod.invoke(t, decryptValue);
                            }
                        }
                    }
                } else {
                    for (Field f : fields) {
                        Method getMethod = getMethod(f.getName(), o.getClass(), true);
                        Object value = getMethod.invoke(o);
                        if (value != null) {
                            Method setMethod = getSetterMethod(clazz, f);
                            if (f.isAnnotationPresent(Decrypt.class)) {
                                Object decryptValue = getClassTypeValue(f.getType(), AESUtil.decrypt(String.valueOf(value), decryptPassword));
                                setMethod.invoke(t, decryptValue);
                            } else {
                                setMethod.invoke(t, value);
                            }
                        }
                    }
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 通过class类型获取获取对应类型的值
     *
     * @param typeClass class类型
     * @param value     值
     * @return Object
     */
    private static Object getClassTypeValue(Class<?> typeClass, String value) {
        if (typeClass == Integer.class) {
            if (null == value) {
                return null;
            }
            return Integer.valueOf(value);
        } else if (typeClass == Short.class) {
            if (null == value) {
                return null;
            }
            return Short.valueOf(value);
        } if (typeClass == Double.class) {
            if (null == value) {
                return null;
            }
            return Double.valueOf(value);
        } else if (typeClass == Long.class) {
            if (null == value) {
                return null;
            }
            return Long.valueOf(value);
        } else if (typeClass == String.class) {
            return value;
        } else if (typeClass == BigDecimal.class) {
            if (null == value) {
                return null;
            }
            return new BigDecimal(value);
        } else {
            return typeClass.cast(value);
        }
    }

    private static String getGetterName(Field field) {
        String name = field.getName();
        if (name.length() == 1) {
            return METHOD_GET_PREFIX + name.toUpperCase();
        } else {
            return METHOD_GET_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }

    private static Method getGetterMethod(Class<?> clazz, Field field) {
        Method method = FIELD_GET_METHOD_MAP.get(field);
        if (method != null) {
            return method;
        } else {
            try {
                method = clazz.getDeclaredMethod(getGetterName(field), null);
                if (method != null) {
                    method.setAccessible(true);
                    Method m = FIELD_GET_METHOD_MAP.putIfAbsent(field, method);
                    if (m != null) {
                        return m;
                    }
                    return method;
                }
            } catch (NoSuchMethodException e) {
                System.err.println("Can not find get method for field " + field.getName()
                        + " in class " + clazz.getName());
            }
        }
        return null;
    }

    private static String getSetterName(Field field) {
        String name = field.getName();
        if (name.length() == 1) {
            return METHOD_SET_PREFIX + name.toUpperCase();
        } else {
            return METHOD_SET_PREFIX + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }

    private static Method getSetterMethod(Class<?> clazz, Field field) {
        Method method = FIELD_SET_METHOD_MAP.get(field);
        if (method != null) {
            return method;
        } else {
            try {
                method = clazz.getDeclaredMethod(getSetterName(field), new Class[]{field.getType()});
                if (method != null) {
                    method.setAccessible(true);
                    Method m = FIELD_SET_METHOD_MAP.putIfAbsent(field, method);
                    if (m != null) {
                        return m;
                    }
                    return method;
                }
            } catch (NoSuchMethodException e) {
                System.err.println("Can not find set method for field " + field.getName()
                        + " in class " + clazz.getName());
            }
        }
        return null;
    }

    private static Field[] getEncryptFields(Class<?> clazz) {
        Field[] fields = ENCRYPT_FIELDS_CASH.get(clazz);
        if (fields != null) {
            return fields;
        }
        fields = getFields(clazz);
        Field[] result = null;
        if (clazz.isAnnotationPresent(Encrypt.class)) {
            result = Arrays.asList(fields)
                    .stream()
                    .filter(f -> !f.isAnnotationPresent(Ignore.class))
                    .toArray(Field[]::new);
        } else {
            result = Arrays.asList(fields)
                    .stream()
                    .filter(f -> f.isAnnotationPresent(Encrypt.class))
                    .toArray(Field[]::new);
        }

        Field[] f = ENCRYPT_FIELDS_CASH.putIfAbsent(clazz, result);
        if (f != null) {
            return f;
        }
        return result;
    }

    private static Field[] getDecryptFields(Class<?> clazz) {
        Field[] fields = DECRYPT_FIELDS_CASH.get(clazz);
        if (fields != null) {
            return fields;
        }
        fields = getFields(clazz);
        Field[] result = null;
        if (clazz.isAnnotationPresent(Decrypt.class)) {
            result = Arrays.asList(fields)
                    .stream()
                    .filter(f -> !f.isAnnotationPresent(Ignore.class))
                    .toArray(Field[]::new);
        } else {
            result = Arrays.asList(fields)
                    .stream()
                    .filter(f -> f.isAnnotationPresent(Decrypt.class))
                    .toArray(Field[]::new);
        }

        Field[] f = DECRYPT_FIELDS_CASH.putIfAbsent(clazz, result);
        if (f != null) {
            return f;
        }
        return result;
    }

    private static Field[] getFields(Class<?> clazz) {
        Field[] fields = FIELDS_CASH.get(clazz);
        if (fields != null) {
            return fields;
        }
        fields = clazz.getDeclaredFields();
        Field[] f = FIELDS_CASH.putIfAbsent(clazz, fields);
        if (f != null) {
            return f;
        }
        return fields;
    }

    /**
     * 反射获取属性的get或者set方法
     *
     * @param fieldName  属性名称
     * @param clazz      类的class对象
     * @param methodFlag true：获取读方法（get）; false：获取写方法（set）
     * @return
     */
    private static Method getMethod(String fieldName, Class<?> clazz, boolean methodFlag) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            if (methodFlag) {
                return pd.getReadMethod();
            }

            return pd.getWriteMethod();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
