package com.ioc;

import com.common.utils.PackageUtil;
import com.common.utils.StringConvertUtil;
import org.apache.commons.lang3.StringUtils;

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
            loadDefinitions(scanPackageLocation);
            isInit = true;
        }
    }

    private synchronized void loadDefinitions(String scanPackageLocation) {
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
                            BeanDefinition beanDefinition = new BeanDefinition(beanName, component.scope(), clazz, clazz.newInstance());
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
            return BEAN_NAME_MAP.get(beanName).getTargetObj();
        }

        throw new RuntimeException("no such beanName BeanDefinition.beanName：" + beanName);
    }

    @Override
    public <T> T getBean(Class<T> t) {
        if (BEAN_CLASS_MAP.containsKey(t)) {
            return (T) BEAN_CLASS_MAP.get(t).getTargetObj();
        }

        throw new RuntimeException("no such beanClass BeanDefinition.beanClass:" + t);
    }
}
