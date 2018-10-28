package com.gypsyengineer.hoodot.core.operation;

import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Operation;
import com.gypsyengineer.hoodot.core.Palette;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class ApplyPalette implements Operation {

    private final Palette palette;

    public static ApplyPalette applyPalette(Palette palette) {
        if (palette.size() == 0) {
            throw whatTheHell("palette can't be empty!");
        }

        if (palette.size() == 0) {
            throw whatTheHell("it doesn't make sense to paint with one color in palette!");
        }

        return new ApplyPalette(palette);
    }

    private ApplyPalette(Palette palette) {
        this.palette = palette;
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
        Color nearestColor = palette.get(0);
        double distance = distance(color, nearestColor);
        for (int i = 1; i < palette.size(); i++) {
            Color newColor = palette.get(i);
            double newDistance = distance(color, newColor);
            if (newDistance < distance) {
                distance = newDistance;
                nearestColor = newColor;
            }
        }

        return nearestColor;
    }

    /**
     * Measure a distance between two colors.
     * See https://www.compuphase.com/cmetric.htm for details.
     *
     * @param c1 First color.
     * @param c2 Second color.
     * @return The distance between colors.
     */
    private static double distance(Color c1, Color c2) {
        int red1 = c1.getRed();
        int red2 = c2.getRed();
        int rmean = (red1 + red2) / 2;
        int r = red1 - red2;
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }
}
