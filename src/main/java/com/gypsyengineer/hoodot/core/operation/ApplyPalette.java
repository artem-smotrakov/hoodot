package com.gypsyengineer.hoodot.core.operation;

import com.gypsyengineer.hoodot.core.*;
import com.gypsyengineer.hoodot.core.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ApplyPalette implements Operation {

    private Palette palette;
    private ColorDistance distance;

    public static ApplyPalette applyPalette() {
        return new ApplyPalette();
    }

    private ApplyPalette() {}

    public ApplyPalette set(Palette palette) {
        if (palette.size() == 0) {
            throw whatTheHell("palette can't be empty!");
        }

        if (palette.size() == 0) {
            throw whatTheHell("it doesn't make sense to paint with one color in palette!");
        }

        this.palette = palette;

        return this;
    }

    public ApplyPalette set(ColorDistance distance) {
        Objects.requireNonNull(distance, "hey! distance can't be null!");
        this.distance = distance;
        return this;
    }

    @Override
    public HoodotImage apply(Image image) {
        BufferedImage bufferedImage = image.bufferedImage();

        BufferedImage newBufferedImage = new BufferedImage(
                bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                TYPE_INT_RGB);

        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                Color color = nearestColor(bufferedImage.getRGB(i, j));
                newBufferedImage.setRGB(i, j, color.getRGB());
            }
        }

        return HoodotImage.from(newBufferedImage);
    }

    private Color nearestColor(int rgb) {
        return nearestColor(new Color(rgb));
    }

    private Color nearestColor(Color color) {
        int index = 0;
        double minDistance = distance.get(color, palette.get(index));
        for (int i = 1; i < palette.size(); i++) {
            Color newColor = palette.get(i);
            double newDistance = distance.get(color, newColor);
            if (newDistance < minDistance) {
                minDistance = newDistance;
                index = i;
            }
        }

        return palette.get(index);
    }
}
