package com.validate.support;

import com.validate.contract.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author wanchongyang
 * @date 2018/11/6 2:56 PM
 */
public class ValidatorChain {
    private List<Validator> chain;

    public ValidatorChain() {
        chain = new LinkedList<>();
    }

    public void add(Validator validator) {
        Optional.ofNullable(validator).ifPresent(v -> this.chain.add(validator));
    }

    public void add(int index, Validator validator) {
        Optional.ofNullable(validator).ifPresent(v -> this.chain.add(index, validator));
    }

    public List<Validator> getChain() {
        return chain;
    }
}
