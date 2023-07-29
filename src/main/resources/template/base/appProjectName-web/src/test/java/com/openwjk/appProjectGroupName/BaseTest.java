package com.openwjk.#{appProjectName};

import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/29 8:35
 */
@ComponentScan("com.openwjk.#{appProjectName}")
public class BaseTest {
    @Before
    public void init(){
        System.out.println("----------测试开始----------");
    }

    @After
    public void end(){
        System.out.println("----------测试开始----------");
    }
}
