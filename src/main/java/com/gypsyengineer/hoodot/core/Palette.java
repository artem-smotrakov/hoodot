package com.gypsyengineer.hoodot.core;

import java.awt.*;

/**
 * Just a palette.
 */
public interface Palette {
    int size();
    Color get(int index);
    boolean contains(Color color);
}
