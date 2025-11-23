package com.app.image;

import java.io.File;

public interface ImageProcessor {

    String extractText(File imageFile) throws Exception;

    String extractBarcode(File imageFile) throws Exception;
}
