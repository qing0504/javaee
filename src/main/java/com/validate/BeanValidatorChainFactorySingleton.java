package com.validate;

/**
 * @author wanchongyang
 * @date 2018/11/11 8:29 PM
 */
public class BeanValidatorChainFactorySingleton {
    private BeanValidatorChainFactorySingleton() {}

    public static BeanValidatorChainFactory getInstance() {
        return BeanValidatorChainFactoryHolder.instance;
    }

    private static class BeanValidatorChainFactoryHolder {
        private static final BeanValidatorChainFactory instance = new DefaultBeanValidatorChainFactory();
    }
}
