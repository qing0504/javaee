package com.ioc.support;

import com.common.utils.PackageUtil;
import com.common.utils.StringConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class DefaultApplicationContext implements ApplicationContext {
    private static final ConcurrentHashMap<String, BeanDefinition> BEAN_NAME_MAP = new ConcurrentHashMap(16);
    private static final ConcurrentHashMap<Class<?>, BeanDefinition> BEAN_CLASS_MAP = new ConcurrentHashMap(16);

    private String scanPackageLocation;

    private static boolean isInit = false;

    public DefaultApplicationContext() {
        // 默认扫描当前包
        this(DefaultApplicationContext.class.getPackage().getName());
    }

    public DefaultApplicationContext(String scanPackageLocation) {
        this.scanPackageLocation = scanPackageLocation;
        if (!isInit) {
            loadBeanDefinitions(scanPackageLocation);
            isInit = true;
        }
    }

    public String getScanPackageLocation() {
        return scanPackageLocation;
    }

    private synchronized void loadBeanDefinitions(String scanPackageLocation) {
        List<Class<?>> classList = PackageUtil.getClass(scanPackageLocation, true);
        if (classList.size() > 0) {
            classList.stream().filter(c -> c.isAnnotationPresent(Component.class)).forEach(
                    clazz -> {
                        Component component = clazz.getAnnotation(Component.class);
                        String typeName = clazz.getTypeName();
                        String beanName = StringConvertUtil.toLowerCaseFirstOne(typeName.substring(typeName.lastIndexOf(".") + 1));
                        if (!StringUtils.isBlank(component.value())) {
                            beanName = component.value();
                        }

                        if (BEAN_NAME_MAP.contains(beanName)) {
                            throw new RuntimeException("duplicated beanName." + beanName);
                        }

                        try {
                            Object newInstance = clazz.newInstance();
                            Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyInvocationHandler(newInstance));
                            BeanDefinition beanDefinition = new BeanDefinition(beanName, component.scope(), clazz, proxy);
                            BEAN_NAME_MAP.put(beanName, beanDefinition);
                            BEAN_CLASS_MAP.put(clazz, beanDefinition);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
    }

    @Override
    public Object getBean(String beanName) {
        if (BEAN_NAME_MAP.containsKey(beanName)) {
            BeanDefinition beanDefinition = BEAN_NAME_MAP.get(beanName);
            if (beanDefinition.isSingleton()) {
                return beanDefinition.getTargetObj();
            }

            try {
                return Proxy.newProxyInstance(beanDefinition.getClazz().getClassLoader(), beanDefinition.getClazz().getInterfaces(), new ProxyInvocationHandler(beanDefinition.getClazz().newInstance()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("no such beanName BeanDefinition.beanName：" + beanName);
    }

    @Override
    public <T> T getBean(Class<T> t) {
        if (BEAN_CLASS_MAP.containsKey(t)) {
            BeanDefinition beanDefinition = BEAN_CLASS_MAP.get(t);
            if (beanDefinition.isSingleton()) {
                return (T) beanDefinition.getTargetObj();
            }

            try {
                return (T) Proxy.newProxyInstance(beanDefinition.getClazz().getClassLoader(), beanDefinition.getClazz().getInterfaces(), new ProxyInvocationHandler(beanDefinition.getClazz().newInstance()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        throw new RuntimeException("no such beanClass BeanDefinition.beanClass:" + t);
    }
}
