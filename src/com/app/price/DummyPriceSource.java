package com.app.price;

import com.app.product.Product;
import java.util.ArrayList;
import java.util.List;

public class DummyPriceSource implements PriceSource {

    @Override
    public List<SearchResult> search(Product product) {

        List<SearchResult> results = new ArrayList<>();

        results.add(new SearchResult(
                "Amazon",
                product.getName(),
                799.99,
                "https://amazon.com"
        ));

        results.add(new SearchResult(
                "eBay",
                product.getName(),
                745.50,
                "https://ebay.com"
        ));

        results.add(new SearchResult(
                "AliExpress",
                product.getName(),
                689.10,
                "https://aliexpress.com"
        ));

        return results;
    }
}
