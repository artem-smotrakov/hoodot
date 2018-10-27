package com.gypsyengineer.hoodot.core;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class HoodotImageTest {

    @Test
    public void load() throws IOException {
        Image image = HoodotImage.fromFile("images/hoover_dam.jpg");
        assertNotNull(image);
    }
}
