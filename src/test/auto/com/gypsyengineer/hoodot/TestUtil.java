package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.util.WhatTheHell;

import static org.junit.Assert.fail;

public class TestUtil {

    public interface TestAction {
        void run() throws Exception;
    }

    public static void expectWhatTheHell(TestAction action) throws Exception {
        try {
            action.run();
            fail("expected WhatTheHell!");
        } catch (WhatTheHell e) {
            // good
        }
    }

    public static void expectException(TestAction action, Class clazz) {
        try {
            action.run();
            fail(String.format("expected %s", clazz.getSimpleName()));
        } catch (Exception e) {
            if (!e.getClass().equals(clazz)) {
                fail(String.format("unexpected exception: %s", e.getClass().getSimpleName()));
            }
        }
    }

}
