package com.app.image;

import java.io.File;

public class BarcodeProcessor implements ImageProcessor {

    @Override
    public String extractText(File imageFile) throws Exception {
        // Barcode reader does not extract text
        return null;
    }

    @Override
    public String extractBarcode(File imageFile) throws Exception {
        // TODO: Add real ZXing later
        return null; // placeholder
    }
}
