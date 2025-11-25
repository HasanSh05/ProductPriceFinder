package com.app.image;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;

public class OcrProcessor implements ImageProcessor {

    @Override
    public String extractText(File imageFile) throws Exception {
        Tesseract tesseract = new Tesseract();

        // Absolute path to tessdata
        tesseract.setDatapath("C:/Users/User/eclipse-workspace/ProductPriceFinder/tessdata");
        tesseract.setLanguage("eng");

        return tesseract.doOCR(imageFile);
    }

    @Override
    public String extractBarcode(File imageFile) {
        return null;
    }

    public ProductIdentificationResult processImage(String imagePath) throws Exception {
        File file = new File(imagePath);
        String text = extractText(file);

        ProductIdentificationResult result = new ProductIdentificationResult();
        result.setProductName(text);
        return result;
    }
}
