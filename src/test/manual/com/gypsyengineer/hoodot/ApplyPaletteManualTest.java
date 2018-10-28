package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.core.ColorDistance;
import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Palette;

import java.util.ArrayList;
import java.util.List;

import static com.gypsyengineer.hoodot.Viewer.viewer;
import static com.gypsyengineer.hoodot.core.EuclideanDistance.euclideanDistance;
import static com.gypsyengineer.hoodot.core.PaletteBuilder.paletteBuilder;
import static com.gypsyengineer.hoodot.core.WeightedEuclideanDistance.weightedEuclideanDistance;
import static com.gypsyengineer.hoodot.core.operation.ApplyPalette.applyPalette;
import static java.awt.Color.*;

public class ApplyPaletteManualTest {

    public static void main(String[] args) throws Exception {
        Image original = HoodotImage.fromFile("images/hoover_dam.jpg");

        Palette palette = paletteBuilder()
                .color(black)
                .color(white)
                .color(blue)
                .color(magenta)
                .color(cyan)
                .color(gray)
                .color(lightGray)
                .color(darkGray)
                .color(green)
                .color(orange)
                .color(pink)
                .color(red)
                .color(yellow)
                .create();

        Image[] images = convert(original, palette,
                euclideanDistance(), weightedEuclideanDistance());

        viewer(images).run();
    }

    private static Image[] convert(Image original, Palette palette, ColorDistance... distances) {
        List<Image> images = new ArrayList<>();
        images.add(original);
        for (ColorDistance distance : distances) {
            Image converted = applyPalette()
                    .set(palette)
                    .set(distance)
                    .to(original);
            images.add(converted);
        }
        return images.toArray(new Image[0]);
    }
}
