package com.app.price;

public class SearchResult implements Comparable<SearchResult>{

    private String storeName;
    private String productTitle;
    private double price;
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

	public void setStoreName(String store) { 
		this.storeName = store;
	}
	public void setProductTitle(String title) { 
		this.productTitle = title;
	}
	public void setPrice(double price) {
	    this.price = price;
	}

	public void setUrl(String url2) {
		this.url = url2;
	}

	 public SearchResult() {
	        // Empty constructor (useful for setters)
	    }
}
