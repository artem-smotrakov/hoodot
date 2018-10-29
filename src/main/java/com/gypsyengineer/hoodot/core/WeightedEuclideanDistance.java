package com.gypsyengineer.hoodot.core;

import java.awt.*;

public class WeightedEuclideanDistance implements ColorDistance {

    private int rWeight = 1;
    private int gWeight = 1;
    private int bWeight = 1;

    public static WeightedEuclideanDistance weightedEuclideanDistance() {
        return new WeightedEuclideanDistance();
    }

    private WeightedEuclideanDistance() {}

    public WeightedEuclideanDistance red(int weight) {
        rWeight = weight;
        return this;
    }

    public WeightedEuclideanDistance green(int weight) {
        gWeight = weight;
        return this;
    }

    public WeightedEuclideanDistance blue(int weight) {
        bWeight = weight;
        return this;
    }

    @Override
    public double get(Color first, Color second) {
        double r = rWeight * pow2(first.getRed() - second.getRed());
        double g = gWeight * pow2(first.getGreen() - second.getGreen());
        double b = bWeight * pow2(first.getBlue() - second.getBlue());
        return Math.sqrt(r + g + b);
    }

    private static double pow2(int a) {
        return a * a;
    }
}
