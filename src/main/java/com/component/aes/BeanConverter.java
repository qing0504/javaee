package com.component.aes;

import com.common.utils.AESUtil;
import com.component.aes.annotation.Decrypt;
import com.component.aes.annotation.Encrypt;
import com.component.aes.annotation.Ignore;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JavaBean对象自动加、解密
 *
 * @author wanchongyang
 * @date 2018/5/6 下午2:20
 */
public class BeanConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConverter.class);

    private static final ConcurrentHashMap<Field, Method> FIELD_GET_METHOD_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Field, Method> FIELD_SET_METHOD_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Method> FIELD_METHOD_CACHE = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Class<?>, Field[]> FIELDS_CACHE = new ConcurrentHashMap<>();

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
            Class<?> superClazz = clazz;
            while (superClazz != Object.class) {
                Field[] fields = getFields(superClazz);
                if (fields != null && fields.length > 0) {
                    if (clazz.isAnnotationPresent(Encrypt.class)) {
                        for (Field f : fields) {
                            Method getMethod = getMethod(f.getName(), o.getClass(), true);
                            if (getMethod == null) {
                                continue;
                            }
                            Object value = getMethod.invoke(o);
                            if (value != null) {
                                if (value.getClass() == String.class && "".equals(value)) {
                                    continue;
                                }

                                Method setMethod = getSetterMethod(superClazz, f);
                                if (setMethod == null) {
                                    continue;
                                }
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
                            if (getMethod == null) {
                                continue;
                            }
                            Object value = getMethod.invoke(o);
                            if (value != null) {
                                if (value.getClass() == String.class && "".equals(value)) {
                                    continue;
                                }

                                Method setMethod = getSetterMethod(superClazz, f);
                                if (setMethod == null) {
                                    continue;
                                }
                                if (f.isAnnotationPresent(Encrypt.class)) {
                                    setMethod.invoke(t, AESUtil.encrypt(String.valueOf(value), encryptPassword));
                                } else {
                                    setMethod.invoke(t, value);
                                }
                            }
                        }
                    }
                }
                superClazz = superClazz.getSuperclass();
            }

        } catch (Exception e) {
            LOGGER.error("BeanConverter toEncrypt e:{}", e);
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
            Class<?> superClazz = clazz;
            while (superClazz != Object.class) {
                Field[] fields = getFields(superClazz);
                if (fields != null && fields.length > 0) {
                    if (clazz.isAnnotationPresent(Decrypt.class)) {
                        for (Field f : fields) {
                            Method getMethod = getMethod(f.getName(), o.getClass(), true);
                            if (getMethod == null) {
                                continue;
                            }
                            Object value = getMethod.invoke(o);
                            if (value != null) {
                                Method setMethod = getSetterMethod(superClazz, f);
                                if (setMethod == null) {
                                    continue;
                                }
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
                            if (getMethod == null) {
                                continue;
                            }
                            Object value = getMethod.invoke(o);
                            if (value != null) {
                                Method setMethod = getSetterMethod(superClazz, f);
                                if (setMethod == null) {
                                    continue;
                                }
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

                superClazz = superClazz.getSuperclass();
            }

        } catch (Exception e) {
            LOGGER.error("BeanConverter toDecrypt e:{}", e);
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
                LOGGER.error("Can not find get method for field {} in class {}. ", field.getName(), clazz.getName());
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
                LOGGER.error("Can not find set method for field {} in class {}. ", field.getName(), clazz.getName());
            }
        }
        return null;
    }

    private static Field[] getFields(Class<?> clazz) {
        Field[] fields = FIELDS_CACHE.get(clazz);
        if (fields != null) {
            return fields;
        }
        fields = clazz.getDeclaredFields();
        // 过滤serialVersionUID字段
        Field[] realFields = Arrays.asList(fields)
                .stream()
                .filter(f -> !"serialVersionUID".equals(f.getName()))
                .toArray(Field[]::new);
        Field[] f = FIELDS_CACHE.putIfAbsent(clazz, realFields);
        if (f != null) {
            return f;
        }
        return realFields;
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
            String cacheKey = StringUtils.joinWith("$", clazz.getName(), fieldName, (methodFlag ? METHOD_GET_PREFIX : METHOD_SET_PREFIX));
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method method = methodFlag ? pd.getReadMethod() : pd.getWriteMethod();
            Method m = FIELD_METHOD_CACHE.putIfAbsent(cacheKey, method);
            if (m != null) {
                return m;
            }

            return method;
        } catch (IntrospectionException e) {
            LOGGER.error("Can not find method for field {} in class {}. ", fieldName, clazz.getName());
        }

        return null;
    }
}
