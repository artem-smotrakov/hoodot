package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.core.ColorDistance;
import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Palette;

import java.util.ArrayList;
import java.util.List;

import static com.gypsyengineer.hoodot.Viewer.viewer;
import static com.gypsyengineer.hoodot.core.WeightedEuclideanDistance.weightedEuclideanDistance;
import static com.gypsyengineer.hoodot.core.PaletteBuilder.paletteBuilder;
import static com.gypsyengineer.hoodot.core.SmartDistance.smartDistance;
import static com.gypsyengineer.hoodot.core.operation.ApplyPalette.applyPalette;
import static java.awt.Color.*;

public class ApplyPaletteManualTest {

    public static void main(String[] args) throws Exception {
        Image original = HoodotImage.fromFile("images/bridge.jpg");

        Palette palette = paletteBuilder()
                .color(66, 134, 244)
                .color(160, 65, 244)
                .color(244, 65, 211)
                .color(244, 65, 133)
                .color(244, 65, 73)
                .color(65, 196, 244)
                .color(65, 244, 139)
                .color(65, 244, 82)
                .color(175, 244, 65)
                .color(238, 244, 65)
                .color(244, 175, 65)
                .color(244, 112, 65)
                .color(244, 65, 6)
                .color(124, 79, 33)
                .color(124, 124, 33)
                .color(75, 124, 33)
                .color(33, 124, 64)
                .color(33, 120, 124)
                .color(33, 70, 124)
                .color(43, 33, 124)
                .color(107, 33, 124)
                .color(124, 33, 102)
                .color(124, 33, 63)
                .color(black)
                .color(white)
                //.color(gray)
                //.color(lightGray)
                .color(darkGray)
                .create();

        Image[] images = convert(original, palette,
                weightedEuclideanDistance()
                        .red(2)
                        .green(4)
                        .blue(3),
                weightedEuclideanDistance(),
                smartDistance());

        viewer(images).run();
    }

    private static Image[] convert(Image original, Palette palette,
                                   ColorDistance... distances) {

        List<Image> images = new ArrayList<>();
        for (ColorDistance distance : distances) {
            Image converted = applyPalette()
                    .set(palette)
                    .set(distance)
                    .to(original);
            images.add(converted);
        }
        images.add(original);
        return images.toArray(new Image[0]);
    }
}
