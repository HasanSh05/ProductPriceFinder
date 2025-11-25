package com.app.product;

public class ProductIdentifierService {

	
	private String cleanText(String raw) {
	    if (raw == null) return "";
	    String clean = raw.replaceAll("[^A-Za-z0-9 ]", " ");
	    clean = clean.trim().replaceAll("\\s+", " ");
	    return clean.toLowerCase();
	}

	public Product identify(String text, String barcode) {

	    // Barcode rule
	    if (barcode != null && !barcode.isEmpty()) {
	        return new Product("Product with barcode " + barcode, null, barcode, text);
	    }

	    // Clean OCR
	    String clean = cleanText(text);

	    // Use catalog to find closest match
	    ProductCatalog catalog = new ProductCatalog();
	    String bestMatch = catalog.getClosestMatch(clean);

	    return new Product(bestMatch, null, null, text);
	}
}
