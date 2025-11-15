package com.app.image;

import java.io.File;

public interface ImageProcessor {

    /**
     * Extracts plain text from an image using OCR.
     */
    String extractText(File imageFile) throws Exception;

    /**
     * Extracts barcode from an image if present.
     */
    String extractBarcode(File imageFile) throws Exception;
}
