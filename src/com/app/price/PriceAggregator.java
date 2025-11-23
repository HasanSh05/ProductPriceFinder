package com.app.price;

import java.util.Collections;
import java.util.List;

public class PriceAggregator {

    public SearchResult getBestPrice(List<SearchResult> results) {

        if (results == null || results.isEmpty()) {
            return null;
        }

        // sort list ascending by price (because compareTo does that)
        Collections.sort(results);

        // the first element is the cheapest
        return results.get(0);
    }
}
