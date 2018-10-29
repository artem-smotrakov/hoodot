package com.gypsyengineer.hoodot.core.operation;

import com.gypsyengineer.hoodot.core.*;

import java.awt.Color;
import java.util.*;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;

public class ExtractPalette implements Operation {

    public static final int default_palette_size = 20;

    private int size = default_palette_size;
    private Palette palette;

    public static ExtractPalette extractPalette() {
        return new ExtractPalette();
    }

    private ExtractPalette() {}

    public ExtractPalette set(int size) {
        this.size = size;
        return this;
    }

    public Palette palette() {
        return palette;
    }

    @Override
    public Image apply(Image image) {
        Map<Color, Integer> hist = new HashMap<>();
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Color color = image.color(x, y);
                Integer n = hist.get(color);
                hist.put(color, n == null ? 1 : n + 1);
            }
        }

        if (size > hist.size()) {
            throw whatTheHell("the picture has less than %d colors (%d)",
                    size, hist.size());
        }

        List<Map.Entry<Color, Integer>> list = new ArrayList<>(hist.entrySet());
        Collections.sort(list, (e1, e2) -> e1.getValue() > e2.getValue() ? 1 : 0);

        Color[] colors = new Color[size];
        for (int i = 0; i < size; i++) {
            colors[i] = list.get(i).getKey();
        }

        palette = HoodotPalette.newPalette(colors);

        return image;
    }

}
