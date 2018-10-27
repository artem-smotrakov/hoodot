package com.gypsyengineer.hoodot.core.impl;

import com.gypsyengineer.hoodot.core.Factory;
import com.gypsyengineer.hoodot.core.Image;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FactoryImpl implements Factory {

    @Override
    public Image image(String filename) throws IOException  {
        try (InputStream is = Files.newInputStream(Paths.get(filename))) {
            return new ImageImpl(ImageIO.read(is));
        }
    }
}
