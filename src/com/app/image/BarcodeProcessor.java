package com.app.image;

import java.io.File;

public class BarcodeProcessor implements ImageProcessor {

    @Override
    public String extractText(File imageFile) throws Exception {
        return null; // barcode reader doesn't extract text
    }

    @Override
    public String extractBarcode(File imageFile) throws Exception {
        // TODO: implement ZXing later
        return null;
    }
}
