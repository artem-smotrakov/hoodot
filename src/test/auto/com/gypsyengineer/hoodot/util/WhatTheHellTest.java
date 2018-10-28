package com.gypsyengineer.hoodot.util;

import org.junit.Test;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WhatTheHellTest {

    @Test
    public void throwWithMessage() {
        try {
            throw whatTheHell("wow");
        } catch (WhatTheHell e) {
            assertEquals("wow", e.getMessage());
        }
    }

    @Test
    public void throwWithCause() {
        try {
            throw whatTheHell("yup", new RuntimeException("cause"));
        } catch (WhatTheHell e) {
            assertEquals("yup", e.getMessage());
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("cause", e.getCause().getMessage());
        }
    }
}
