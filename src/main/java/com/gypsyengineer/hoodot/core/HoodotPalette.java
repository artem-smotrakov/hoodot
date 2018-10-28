package com.gypsyengineer.hoodot.core;

import java.awt.image.IndexColorModel;
import java.util.Objects;

public class HoodotPalette implements Palette {

    private final IndexColorModel model;

    public static HoodotPalette newPalette(IndexColorModel model) {
        return new HoodotPalette(model);
    }

    private HoodotPalette(IndexColorModel model) {
        Objects.requireNonNull(model, "hey! model can't be null!");
        this.model = model;
    }

    @Override
    public int size() {
        return model.getMapSize();
    }
}
