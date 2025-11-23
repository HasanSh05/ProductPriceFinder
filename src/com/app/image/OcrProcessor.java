package com.app.image;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;

public class OcrProcessor implements ImageProcessor {

    @Override
    public String extractText(File imageFile) throws Exception {
        Tesseract tesseract = new Tesseract();

        // Put tessdata folder in the project root (same level as src)
        tesseract.setDatapath("C:/Users/User/eclipse-workspace/ProductPriceFinder/tessdata");

        tesseract.setLanguage("eng");

        return tesseract.doOCR(imageFile).trim();
    }

    @Override
    public String extractBarcode(File imageFile) throws Exception {
        return null; // OCR doesn't read barcodes
    }
}
