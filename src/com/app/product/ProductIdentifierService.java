package com.app.product;

public class ProductIdentifierService {

    public Product identify(String text, String barcode) {

        // If barcode exists → strong hint but we still analyze text
        if (barcode != null && !barcode.isEmpty()) {
            return new Product("Unknown Product (Barcode)", null, barcode, text);
        }

        // Clean the OCR text
        if (text != null) {
            text = text.replaceAll("[^A-Za-z0-9 ]", "");  // keep only letters/numbers/spaces
            text = text.toLowerCase().trim();
        } else {
            text = "";
        }

        String name = "Unknown Product";

        // ---------- BRAND DETECTION ----------
        if (text.contains("iphone")) {
            name = "iPhone";
        }
        if (text.contains("apple")) {
            name = "Apple iPhone";
        }
        if (text.contains("samsung")) {
            name = "Samsung";
        }
        if (text.contains("galaxy")) {
            name = "Samsung Galaxy";
        }

        // ---------- MODEL NUMBER DETECTION ----------
        for (int i = 10; i <= 30; i++) {
            if (text.contains(String.valueOf(i))) {
                name += " " + i;
                break;
            }
        }

        return new Product(name, null, null, text);
    }
}
