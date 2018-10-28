package com.gypsyengineer.hoodot.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HoodotImage implements Image {

    private final BufferedImage image;

    public static HoodotImage fromFile(String filename) throws IOException {
        try (InputStream is = Files.newInputStream(Paths.get(filename))) {
            return new HoodotImage(ImageIO.read(is));
        }
    }

    public static HoodotImage from(BufferedImage image) {
        return new HoodotImage(image);
    }

    private HoodotImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public int width() {
        return image.getWidth();
    }

    @Override
    public int height() {
        return image.getHeight();
    }

    @Override
    public Color color(int x, int y) {
        return new Color(bufferedImage().getRGB(x, y));
    }

    @Override
    public BufferedImage bufferedImage() {
        return image;
    }
}
