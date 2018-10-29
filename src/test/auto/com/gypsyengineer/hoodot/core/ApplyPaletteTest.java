package com.gypsyengineer.hoodot.core;

import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static com.gypsyengineer.hoodot.core.WeightedEuclideanDistance.weightedEuclideanDistance;
import static com.gypsyengineer.hoodot.core.PaletteBuilder.paletteBuilder;
import static com.gypsyengineer.hoodot.core.SmartDistance.smartDistance;
import static com.gypsyengineer.hoodot.core.operation.ApplyPalette.applyPalette;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ApplyPaletteTest {

    @Test
    public void withWeightedEuclideanDistance() throws IOException {
        Image image = HoodotImage.fromFile("images/hoover_dam.jpg");
        assertNotNull(image);

        Palette blackWhiteGray = paletteBuilder()
                .color(Color.BLACK)
                .color(Color.WHITE)
                .color(Color.GRAY)
                .create();

        Image newImage = applyPalette()
                .set(blackWhiteGray)
                .set(smartDistance())
                .to(image);

        assertEquals(image.width(), newImage.width());
        assertEquals(image.height(), newImage.height());

        for (int x = 0; x < newImage.width(); x++) {
            for (int y = 0; y < newImage.height(); y++) {
                assertTrue(blackWhiteGray.contains(newImage.color(x, y)));
            }
        }
    }

    @Test
    public void withEuclideanDistance() throws IOException {
        Image image = HoodotImage.fromFile("images/hoover_dam.jpg");
        assertNotNull(image);

        Palette blackWhiteGray = paletteBuilder()
                .color(Color.BLACK)
                .color(Color.WHITE)
                .color(Color.GRAY)
                .create();

        Image newImage = applyPalette()
                .set(blackWhiteGray)
                .set(weightedEuclideanDistance())
                .to(image);

        assertEquals(image.width(), newImage.width());
        assertEquals(image.height(), newImage.height());

        for (int x = 0; x < newImage.width(); x++) {
            for (int y = 0; y < newImage.height(); y++) {
                assertTrue(blackWhiteGray.contains(newImage.color(x, y)));
            }
        }
    }
}
