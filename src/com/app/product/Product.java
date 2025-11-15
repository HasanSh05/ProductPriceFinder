package com.app.product;

public class Product {

    private String name;
    private String brand;
    private String barcode;
    private String rawText;

    // Minimal constructor (for when we only have a name)
    public Product(String name) {
        this.name = name;
    }

    // Full constructor
    public Product(String name, String brand, String barcode, String rawText) {
        this.name = name;
        this.brand = brand;
        this.barcode = barcode;
        this.rawText = rawText;
    }

    // Getters
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
