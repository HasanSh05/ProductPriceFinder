package com.app.product;

public class ProductIdentifierService {

    /**
     * Takes raw OCR text or barcode and returns a structured Product object.
     */
    public Product identify(String text, String barcode) {

        // CASE 1: Barcode found (stronger than OCR)
        if (barcode != null && !barcode.isEmpty()) {
            return new Product("Unknown Product", null, barcode, text);
        }

        // CASE 2: OCR found text
        if (text != null && !text.isEmpty()) {

            // simple cleaning
            String clean = text.trim().replaceAll("\\s+", " ");

            // basic name extraction (longest word or full string)
            String name = clean;

            return new Product(name, null, null, text);
        }

        // CASE 3: nothing detected
        return new Product("Unknown Product");
    }
}
