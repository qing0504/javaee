package com.validate;

/**
 * @author wanchongyang
 * @date 2018/11/8 10:43 AM
 */
public abstract class AbstractBeanValidator implements BeanValidator {
    private MutableValidatorPropertyValues propertyValues;

    /**
     * Specify property values for this bean, if any.
     */
    public void setPropertyValues(MutableValidatorPropertyValues propertyValues) {
        this.propertyValues = (propertyValues != null ? propertyValues : new MutableValidatorPropertyValues());
    }

    /**
     * Return property values for this bean (never {@code null}).
     */
    @Override
    public MutableValidatorPropertyValues getPropertyValues() {
        return this.propertyValues;
    }
}
