package com.gypsyengineer.hoodot.core;

/**
 * An interface for an operation with an image.
 */
public interface Operation {

    /**
     * Apply the operation to an image.
     * The method may apply the operation to the original image,
     * or return a modified copy of the original image.
     *
     * @param image An image to process.
     * @return An image after applying the operation.
     */
    Image apply(Image image);

    /*
     * The methods below are just alternative names for the apply() method.
     * Just to make code to look like a human language.
     */

    default Image of(Image image) {
        return apply(image);
    }

    default Image to(Image image) {
        return apply(image);
    }

    default Image from(Image image) {
        return apply(image);
    }
}
