package com.ioc;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class BeanDefinition {
    private String beanName;

    private String scope;

    private Class<?> clazz;

    private Object targetObj;

    public BeanDefinition(String beanName, String scope, Class<?> clazz, Object targetObj) {
        this.beanName = beanName;
        this.scope = scope;
        this.clazz = clazz;
        this.targetObj = targetObj;
    }

    public boolean isSingleton() {
        if ("singleton".equals(this.beanName)) {
            return true;
        }

        return false;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(Object targetObj) {
        this.targetObj = targetObj;
    }
}
