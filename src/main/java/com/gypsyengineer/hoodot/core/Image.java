package com.gypsyengineer.hoodot.core;

import java.awt.image.BufferedImage;

/**
 * Just an image.
 */
public interface Image {
    int width();
    int height();
    BufferedImage bufferedImage();
}
