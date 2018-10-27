package com.gypsyengineer.hoodot.core;

import org.junit.Test;

import java.io.IOException;

import static com.gypsyengineer.hoodot.core.operation.MakeSquare.makeSquare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HoodotImageTest {

    @Test
    public void load() throws IOException {
        Image image = HoodotImage.fromFile("images/hoover_dam.jpg");
        assertNotNull(image);
        assertEquals(1216, image.width());
        assertEquals(1346, image.height());
    }

    @Test
    public void square() throws IOException {
        Image image = makeSquare().from(HoodotImage.fromFile("images/hoover_dam.jpg"));
        assertNotNull(image);
        assertEquals(1216, image.width());
        assertEquals(1216, image.height());
    }
}
