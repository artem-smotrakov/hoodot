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

}
