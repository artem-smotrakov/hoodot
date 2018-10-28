package com.gypsyengineer.hoodot.core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Just an image.
 */
public interface Image {
    int width();
    int height();
    Color color(int x, int y);
    BufferedImage bufferedImage();
}
