package com.app.price;

import com.app.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriceAggregator {
    private final List<PriceSource> sources = new ArrayList<>();

    public PriceAggregator(List<PriceSource> sources) {
        if (sources != null) {
            this.sources.addAll(sources);
        }
    }
    public void addSource(PriceSource source) {
        if (source != null) {
            this.sources.add(source);
        }
    }

    public List<SearchResult> searchAll(Product product) throws Exception {
        List<SearchResult> allResults = new ArrayList<>();

        if (product == null) {
            return allResults;
        }

        for (PriceSource source : sources) {
            List<SearchResult> results = source.search(product);
            if (results != null && !results.isEmpty()) {
                allResults.addAll(results);
            }
        }

        return allResults;
    }
    public SearchResult findBestPrice(Product product) throws Exception {
        List<SearchResult> all = searchAll(product);

        if (all.isEmpty()) {
            return null;
        }

        return all.stream()
                .min(Comparator.comparing(SearchResult::getPrice))
                .orElse(null);
    }
}