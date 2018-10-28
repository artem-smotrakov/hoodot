package com.gypsyengineer.hoodot.core;

import java.awt.*;
import java.awt.image.IndexColorModel;
import java.util.ArrayList;
import java.util.List;

import static com.gypsyengineer.hoodot.util.WhatTheHell.whatTheHell;

public class PaletteBuilder {

    public static final int bits = 16;

    private final List<Byte> red = new ArrayList<>();
    private final List<Byte> green = new ArrayList<>();
    private final List<Byte> blue = new ArrayList<>();

    public static PaletteBuilder paletteBuilder() {
        return new PaletteBuilder();
    }

    private PaletteBuilder() {}

    public PaletteBuilder color(int r, int g, int b) {
        red.add(check(r));
        green.add(check(g));
        blue.add(check(b));

        return this;
    }

    public PaletteBuilder color(Color color) {
        red.add(check(color.getRed()));
        green.add(check(color.getGreen()));
        blue.add(check(color.getBlue()));

        return this;
    }

    public Palette create() {
        return HoodotPalette.newPalette(model());
    }

    private IndexColorModel model() {
        int size = red.size();
        if (green.size() != size) {
            throw whatTheHell("too much green!");
        }
        if (blue.size() != size) {
            throw whatTheHell("too much blue!");
        }

        return new IndexColorModel(bits, size,
                toBytes(red), toBytes(green), toBytes(blue));
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
