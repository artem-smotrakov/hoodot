package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Palette;

import static com.gypsyengineer.hoodot.Viewer.viewer;
import static com.gypsyengineer.hoodot.core.PaletteBuilder.paletteBuilder;
import static com.gypsyengineer.hoodot.core.operation.ApplyPalette.applyPalette;
import static java.awt.Color.*;

public class ApplyPaletteManualTest {

    public static void main(String[] args) throws Exception {
        Image original = HoodotImage.fromFile("images/hoover_dam.jpg");

        Palette p = paletteBuilder()
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

        Image converted = applyPalette(p).to(original);

        viewer(converted, original).run();
    }
}
