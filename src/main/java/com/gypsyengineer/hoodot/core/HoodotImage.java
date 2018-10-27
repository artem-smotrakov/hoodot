package com.gypsyengineer.hoodot.core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HoodotImage implements Image {

    private final BufferedImage image;

    private HoodotImage(BufferedImage image) {
        this.image = image;
    }

    public static HoodotImage fromFile(String filename) throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get(filename))) {
            return new HoodotImage(ImageIO.read(is));
        }
    }
}
