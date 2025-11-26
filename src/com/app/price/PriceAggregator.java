package com.app.price;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriceAggregator {

    public PriceAggregator() {
        // empty constructor
    }

    public SearchResult getBestPrice(List<SearchResult> results) {

        if (results == null || results.isEmpty()) {
            return null;
        }

        // remove null or zero price entries BEFORE sorting
        results.removeIf(r -> r == null || r.getPrice() <= 100.0);

        if (results.isEmpty()) return null;

        // sort ascending
        results.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));

        return results.get(0);
    }


}
