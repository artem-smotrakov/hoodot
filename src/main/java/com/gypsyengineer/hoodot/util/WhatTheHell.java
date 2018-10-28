package com.gypsyengineer.hoodot.util;

/**
 * Your favorite exception.
 */
public class WhatTheHell extends RuntimeException {

    public static WhatTheHell whatTheHell(String message) {
        return new WhatTheHell(message);
    }

    public static WhatTheHell whatTheHell(String message, Object... args) {
        return new WhatTheHell(String.format(message, args));
    }

    public static WhatTheHell whatTheHell(String message, Throwable t) {
        return new WhatTheHell(message, t);
    }

    private WhatTheHell(String message) {
        super(message);
    }

    private WhatTheHell(String message, Throwable t) {
        super(message, t);
    }
}
