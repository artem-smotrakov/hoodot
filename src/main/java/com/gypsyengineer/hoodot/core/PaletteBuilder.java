package com.gypsyengineer.hoodot.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;

public class PaletteBuilder {

    public static final int bits = 16;

    private final List<Color> colors = new ArrayList<>();

    public static PaletteBuilder paletteBuilder() {
        return new PaletteBuilder();
    }

    private PaletteBuilder() {}

    public PaletteBuilder color(int r, int g, int b) {
        colors.add(new Color(r, g, b));
        return this;
    }

    public PaletteBuilder color(Color color) {
        colors.add(color);
        return this;
    }

    public Palette create() {
        return HoodotPalette.newPalette(colors.toArray(new Color[0]));
    }

    private static byte check(int color) {
        if (color < 0 || color > 255) {
            throw whatTheHell("wrong color component: (%d)!", color);
        }

        return (byte) color;
    }

    /**
     * Convert a list of bytes to a byte array.
     * @param list A list of bytes.
     * @return A byte array.
     */
    private static byte[] toBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        int i = 0;
        for (Byte b : list) {
            bytes[i++] = b;
        }
        return bytes;
    }
}
