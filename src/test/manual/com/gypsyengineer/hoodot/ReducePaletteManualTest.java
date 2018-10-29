package com.gypsyengineer.hoodot;

import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;

import java.util.ArrayList;
import java.util.List;

import static com.gypsyengineer.hoodot.Viewer.viewer;
import static com.gypsyengineer.hoodot.core.operation.ReducePalette.reducePalette;

public class ReducePaletteManualTest {

    public static void main(String[] args) throws Exception {
        Image original = HoodotImage.fromFile("images/bridge.jpg");
        Image[] images = reduce(original);
        viewer(images).run();
    }

    private static Image[] reduce(Image original) {
        List<Image> images = new ArrayList<>();
        for (int size : List.of(5, 10, 15, 20)) {
            Image converted = reducePalette()
                    .set(size)
                    .of(original);
            images.add(converted);
        }
        images.add(original);
        return images.toArray(new Image[0]);
    }
}
