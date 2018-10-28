package com.gypsyengineer.hoodot.core.operation;

import com.gypsyengineer.hoodot.core.HoodotImage;
import com.gypsyengineer.hoodot.core.Image;
import com.gypsyengineer.hoodot.core.Operation;

public class MakeSquare implements Operation {

    public static final int X = 0;
    public static final int Y = 0;

    public static MakeSquare makeSquare() {
        return new MakeSquare();
    }

    private MakeSquare() {}

    @Override
    public Image apply(Image image) {
        int w = image.width();
        int h = image.height();

        int a = w < h ? w : h;
        return HoodotImage.from(image.bufferedImage().getSubimage(X, Y, a, a));
    }

}
