package com.gypsyengineer.hoodot.core;

import org.junit.Test;

import java.awt.*;

import static com.gypsyengineer.hoodot.TestUtil.expectException;
import static com.gypsyengineer.hoodot.core.PaletteBuilder.paletteBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HoodotPaletteTest {

    @Test
    public void buildFromRGB() {
        Palette palette = paletteBuilder()
                .color(0, 0, 0)
                .color(42, 0, 42)
                .color(0, 42, 0)
                .color(0, 0, 42)
                .color(42, 42, 42)
                .create();
        assertNotNull(palette);
        assertEquals(5, palette.size());
    }

    @Test
    public void buildFromColors() {
        Palette palette = paletteBuilder()
                .color(Color.BLACK)
                .color(Color.BLUE)
                .color(Color.CYAN)
                .create();
        assertNotNull(palette);
        assertEquals(3, palette.size());
    }

    @Test
    public void wrongColor() throws Exception {
        expectException(
                () -> paletteBuilder().color(300, 0, 0),
                IllegalArgumentException.class);
        expectException(
                () -> paletteBuilder().color(0, 300, 0),
                IllegalArgumentException.class);
        expectException(
                () -> paletteBuilder().color(0, 0, 300),
                IllegalArgumentException.class);
        expectException(
                () -> paletteBuilder().color(-1, 0, 0),
                IllegalArgumentException.class);
        expectException(
                () -> paletteBuilder().color(0, -1, 0),
                IllegalArgumentException.class);
        expectException(
                () -> paletteBuilder().color(0, 0, -1),
                IllegalArgumentException.class);
    }
}
