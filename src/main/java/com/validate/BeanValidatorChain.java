package com.validate;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanchongyang
 * @date 2018/11/8 2:00 PM
 */
public class BeanValidatorChain {
    private List<BeanValidator> chain;

    public BeanValidatorChain() {
        chain = new LinkedList<>();
    }

    public void add(BeanValidator validator) {
        Optional.ofNullable(validator).ifPresent(v -> this.chain.add(validator));
    }

    public void add(int index, BeanValidator validator) {
        Optional.ofNullable(validator).ifPresent(v -> this.chain.add(index, validator));
    }

    public List<BeanValidator> getChain() {
        return chain;
    }
}
