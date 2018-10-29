package com.gypsyengineer.hoodot.core;

import com.gypsyengineer.hoodot.core.operation.ReducePalette;
import org.junit.Test;

import java.io.IOException;

import static com.gypsyengineer.hoodot.core.operation.ReducePalette.reducePalette;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReducePaletteTest {

    @Test
    public void reduce() throws IOException {
        Image image = HoodotImage.fromFile("images/hoover_dam.jpg");
        assertNotNull(image);

        ReducePalette reducePalette = reducePalette();
        reducePalette.set(10);
        Image updated = reducePalette.apply(image);
        assertNotNull(updated);
        assertEquals(image.width(), updated.width());
        assertEquals(image.height(), updated.height());

        Palette palette = reducePalette.palette();
        assertNotNull(palette);

        assertEquals(10, palette.size());

        for (int x = 0; x < updated.width(); x++) {
            for (int y = 0; y < updated.height(); y++) {
                palette.contains(updated.color(x, y));
            }
        }
    }
}
