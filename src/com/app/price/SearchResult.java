package com.app.price;

public class SearchResult implements Comparable<SearchResult>{

    private String storeName;
    private String productTitle;
    private Double price;
    private String url;

    public SearchResult(String storeName, String productTitle, double price, String url) {
        this.storeName = storeName;
        this.productTitle = productTitle;
        this.price = price;
        this.url = url;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return storeName + " - " + productTitle + " - " + price + " - " + url;
    }
    
    @Override
    public int compareTo(SearchResult other) {
        if (this.price < other.price)
            return -1;    
        else if (this.price > other.price)
            return 1;     
        else
            return 0;  
    }
    
}
