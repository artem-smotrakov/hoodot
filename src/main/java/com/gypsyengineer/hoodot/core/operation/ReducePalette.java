package com.gypsyengineer.hoodot.core.operation;

import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Operation;
import com.gypsyengineer.hoodot.core.Palette;

public class ReducePalette implements Operation {

    private ExtractPalette extractPalette = ExtractPalette.extractPalette();
    private ApplyPalette applyPalette = ApplyPalette.applyPalette();

    public static ReducePalette reducePalette() {
        return new ReducePalette();
    }

    private ReducePalette() {}

    public ReducePalette set(int size) {
        extractPalette.set(size);
        return this;
    }

    public Palette palette() {
        return extractPalette.palette();
    }

    @Override
    public Image apply(Image image) {
        extractPalette.from(image);
        applyPalette.set(extractPalette.palette());
        return applyPalette.to(image);
    }
}
