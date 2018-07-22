package com.ioc.impl;

import com.ioc.support.Component;
import com.ioc.EnterpriseService;

/**
 * @author wanchongyang
 * @date 2018/7/22
 */
@Component(scope = "prototype")
public class EnterpriseServiceImpl implements EnterpriseService {
    @Override
    public void manage() {
        System.out.println("good work.");
    }
}
