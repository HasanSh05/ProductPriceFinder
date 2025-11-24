
package com.app.price;

import com.app.product.Product;

import java.util.List;

public interface PriceSource {
    String getName();
    List<SearchResult> search(Product product);
}
