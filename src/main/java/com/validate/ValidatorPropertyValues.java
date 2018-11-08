package com.validate;

/**
 * @author wanchongyang
 * @date 2018/11/8 10:07 AM
 */
public interface ValidatorPropertyValues {
    /**
     * Return an array of the PropertyValue objects held in this object.
     */
    ValidatorPropertyValue[] getPropertyValues();

    /**
     * Return the property value with the given name, if any.
     * @param propertyName the name to search for
     * @return the property value, or {@code null}
     */
    ValidatorPropertyValue getPropertyValue(String propertyName);

    /**
     * Is there a property value (or other processing entry) for this property?
     * @param propertyName the name of the property we're interested in
     * @return whether there is a property value for this property
     */
    boolean contains(String propertyName);

    /**
     * Does this holder not contain any PropertyValue objects at all?
     */
    boolean isEmpty();
}
