package com.gypsyengineer.hoodot.core;

import com.gypsyengineer.hoodot.core.impl.FactoryImpl;

import java.io.IOException;

public interface Factory {

    static Factory standardFactory() {
        return new FactoryImpl();
    }

    Image image(String filename) throws IOException;
}
