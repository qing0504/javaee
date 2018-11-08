package com.validate;

import com.common.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanchongyang
 * @date 2018/11/8 10:09 AM
 */
public class MutableValidatorPropertyValues implements ValidatorPropertyValues, Serializable {
    private final List<ValidatorPropertyValue> propertyValueList;

    public MutableValidatorPropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    /**
     * Construct a new MutableValidatorPropertyValues object using the given List of
     * PropertyValue objects as-is.
     * <p>This is a constructor for advanced usage scenarios.
     * It is not intended for typical programmatic use.
     * @param propertyValueList List of PropertyValue objects
     */
    public MutableValidatorPropertyValues(List<ValidatorPropertyValue> propertyValueList) {
        this.propertyValueList = (propertyValueList != null ? propertyValueList : new ArrayList<>());
    }

    public void addPropertyValue(String propertyName, String propertyValue) {
        addPropertyValue(new ValidatorPropertyValue(propertyName, propertyValue));
    }

    public MutableValidatorPropertyValues addPropertyValue(ValidatorPropertyValue pv) {
        if (contains(pv.getName())) {
            this.getPropertyValue(pv.getName()).setValue(pv.getValue());
            return this;
        }

        this.propertyValueList.add(pv);
        return this;
    }

    @Override
    public ValidatorPropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new ValidatorPropertyValue[this.propertyValueList.size()]);
    }

    public Object get(String propertyName) {
        ValidatorPropertyValue pv = getPropertyValue(propertyName);
        return (pv != null ? pv.getValue() : null);
    }

    @Override
    public ValidatorPropertyValue getPropertyValue(String propertyName) {
        Optional<ValidatorPropertyValue> first = this.propertyValueList.stream().filter(p -> p.getName().equals(propertyName)).findFirst();
        return first.isPresent() ? first.get() : null;
    }

    @Override
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    @Override
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    public List<ValidatorPropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }
}
