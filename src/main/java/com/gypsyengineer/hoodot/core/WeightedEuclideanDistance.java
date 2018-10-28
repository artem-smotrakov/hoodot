package com.gypsyengineer.hoodot.core;

import java.awt.*;

public class WeightedEuclideanDistance implements ColorDistance {

    public static WeightedEuclideanDistance weightedEuclideanDistance() {
        return new WeightedEuclideanDistance();
    }

    private WeightedEuclideanDistance() {}

    /**
     * See https://www.compuphase.com/cmetric.htm for details.
     */
    @Override
    public double get(Color first, Color second) {
        int red1 = first.getRed();
        int red2 = second.getRed();
        int rmean = (red1 + red2) / 2;
        int r = red1 - red2;
        int g = first.getGreen() - second.getGreen();
        int b = first.getBlue() - second.getBlue();
        return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }
}
