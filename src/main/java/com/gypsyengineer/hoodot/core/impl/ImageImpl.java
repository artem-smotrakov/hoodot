package com.gypsyengineer.hoodot.core.impl;

import com.gypsyengineer.hoodot.core.Image;

import java.awt.image.BufferedImage;

public class ImageImpl implements Image {

    private final BufferedImage image;

    ImageImpl(BufferedImage image) {
        this.image = image;
    }
}
