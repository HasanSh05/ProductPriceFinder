package com.app.image;

import java.io.File;

public class OcrProcessor implements ImageProcessor {

    @Override
    public String extractText(File imageFile) throws Exception {
        // TODO: Connect real OCR later (Tesseract)
        return "Samsung S23"; // placeholder text
    }

    @Override
    public String extractBarcode(File imageFile) throws Exception {
        // OCR does not read barcodes
        return null;
    }
}
