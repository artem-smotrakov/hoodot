package com.gypsyengineer.hoodot.core;

import java.awt.*;

public class HoodotPalette implements Palette {

    private final Color[] colors;

    public static HoodotPalette newPalette(Color[] colors) {
        return new HoodotPalette(colors);
    }

    private HoodotPalette(Color[] colors) {
        this.colors = colors;
    }

    @Override
    public int size() {
        return colors.length;
    }

    @Override
    public Color get(int index) {
        return colors[index];
    }

    @Override
    public boolean contains(Color color) {
        for (Color c: colors) {
            if (c.equals(color)) {
                return true;
            }
        }

        return false;
    }
}
