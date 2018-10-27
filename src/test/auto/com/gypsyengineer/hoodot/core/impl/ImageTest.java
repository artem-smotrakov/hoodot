package com.gypsyengineer.hoodot.core.impl;

import com.gypsyengineer.hoodot.core.Factory;
import com.gypsyengineer.hoodot.core.Image;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ImageTest {

    @Test
    public void load() throws IOException {
        Image image = Factory.standardFactory().image("images/hoover_dam.jpg");
        assertNotNull(image);
    }
}
