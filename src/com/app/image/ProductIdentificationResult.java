package com.app.image;

public class ProductIdentificationResult {
private String productName;
private String brand;
private String model;
private String barcode;

public ProductIdentificationResult(String productName, String brand, String model, String barcode) {
  super();
  this.productName = productName;
  this.brand = brand;
  this.model = model;
  this.barcode = barcode;
}
public ProductIdentificationResult() {
}
public String getProductName() {
  return productName;
}
public void setProductName(String productName) {
  this.productName = productName;
}
public String getBrand() {
  return brand;
}
public void setBrand(String brand) {
  this.brand = brand;
}
public String getModel() {
  return model;
}
public void setModel(String model) {
  this.model = model;
}
public String getBarcode() {
  return barcode;
}
public void setBarcode(String barcode) {
  this.barcode = barcode;
}
public void tostring() {
  System.out.println("productname:"+productName);
  System.out.println("brand:"+brand);
  System.out.println("model:"+model);
  System.out.println("barcode:"+barcode);
}
}