package com.gypsyengineer.hoodot.core;

import java.awt.*;

/**
 * See https://www.compuphase.com/cmetric.htm for details.
 */
public class SmartDistance implements ColorDistance {

    public static SmartDistance smartDistance() {
        return new SmartDistance();
    }

    private SmartDistance() {}

    @Override
    public double get(Color first, Color second) {
        int red1 = first.getRed();
        int red2 = second.getRed();
        int rmean = (red1 + red2) / 2;
        double a = first.getAlpha() - second.getAlpha();
        double aa = a * a;
        int r = red1 - red2;
        int g = first.getGreen() - second.getGreen();
        int b = first.getBlue() - second.getBlue();
        return Math.sqrt(aa + (((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
    }
}
