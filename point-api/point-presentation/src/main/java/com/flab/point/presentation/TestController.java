package com.flab.point.presentation;

import com.flab.point.application.TestManager;

public class TestController {

    TestManager testManager = new TestManager();

    public void test() {
        testManager.test();
    }
}
