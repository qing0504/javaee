package com.java8.stream;

/**
 * @author wanchongyang
 * @date 2018/10/24 3:29 PM
 */
class ValueAbsentException extends Throwable {

    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}
