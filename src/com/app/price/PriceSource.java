package com.app.price;

import com.app.product.Product;
import java.util.List;

public interface PriceSource {
    List<SearchResult> search(Product product) throws Exception;
}
