package com.gypsyengineer.hoodot.core;

import java.awt.*;

public class EuclideanDistance implements ColorDistance {

    public static EuclideanDistance euclideanDistance() {
        return new EuclideanDistance();
    }

    private EuclideanDistance() {}

    @Override
    public double get(Color first, Color second) {
        double r = pow2(first.getRed() - second.getRed());
        double g = pow2(first.getGreen() - second.getGreen());
        double b = pow2(first.getBlue() - second.getBlue());
        return Math.sqrt(r + g + b);
    }

    private static double pow2(int a) {
        return a * a;
    }
}
