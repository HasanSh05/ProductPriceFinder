package com.app.product;

public class Product {

    private String name;
    private String brand;
    private String barcode;
    private String rawText;

    // Minimal constructor
    public Product(String name) {
        this(name, null, null, null);
    }

    // Full constructor
    public Product(String name, String brand, String barcode, String rawText) {
        this.name = name;
        this.brand = brand;
        this.barcode = barcode;
        this.rawText = rawText;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getRawText() {
        return rawText;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', brand='" + brand + "', barcode='" + barcode + "'}";
    }
}
