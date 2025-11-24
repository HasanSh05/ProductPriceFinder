package com.app.price;

import com.app.product.Product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;//encoding url parameters
import java.nio.charset.StandardCharsets;//hay lal utf (enccoding websites)
import java.util.ArrayList;
import java.util.List;

public class DummyPriceSource implements PriceSource {

    private static final String USER_AGENT = "Mozilla/5.0 (ProductPriceFinder)";
    private static final int TIMEOUT_MS = 10_000;

    @Override
    public String getName() {
        return "MultiStore"; // this source aggregates multiple stores
    }

    @Override
    public List<SearchResult> search(Product product) {
        List<SearchResult> results = new ArrayList<>();
        
        String query = product.getName();
        results.addAll(searchEbay(query));
        results.addAll(searchAmazon(query));
        results.addAll(searchWalmart(query));
        return results;
    }
    //ebay 
    private List<SearchResult> searchEbay(String query) {
        List<SearchResult> results = new ArrayList<>();
        try {
            String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = "https://www.ebay.com/sch/i.html?_nkw=" + encoded;

            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT_MS)
                    .get();

            Elements items = doc.select(".s-item");

            for (Element item : items) {
                String title = item.select(".s-item__title").text();
                if (title.isBlank() || "Shop on eBay".equals(title)) continue;

                String priceText = item.select(".s-item__price").text();
                BigDecimal price = parsePrice(priceText);
                if (price == null) continue;

                String link = item.select(".s-item__link").attr("abs:href");
                if (link.isBlank()) continue;

                results.add(buildResult("eBay", title, price, "USD", link));
            }

        } catch (IOException e) {
           e.printStackTrace();
        }

        return results;
    }

    // amazon

    private List<SearchResult> searchAmazon(String query) {
        List<SearchResult> results = new ArrayList<>();

        try {
            String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = "https://www.amazon.com" + encoded;

            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT_MS)
                    .get();

            // kel block bel website
            Elements items = doc.select("div[data-component-type=s-search-result]");

            for (Element item : items) {
                String title = item.select("h2 a span").text();
                if (title.isBlank()) continue;

                Element priceContainer = item.selectFirst(".a-price");
                if (priceContainer == null) continue;

                String priceWhole = priceContainer.select(".a-price-whole").text();
                String priceFraction = priceContainer.select(".a-price-fraction").text();
                String priceText = (priceWhole + "." + priceFraction).trim();
                BigDecimal price = parsePrice(priceText);
                if (price == null) continue;

                String link = item.select("h2 a").attr("abs:href");
                if (link.isBlank()) continue;

                results.add(buildResult("Amazon", title, price, "USD", link));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    // walmart
private List<SearchResult> searchWalmart(String query) {
    List<SearchResult> results = new ArrayList<>();

    try {
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://www.walmart.com/search?q=" + encoded;

        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT_MS)
                .get();

        // Walmart markup changes a lot; these selectors are heuristic
        Elements items = doc.select("div.search-result-gridview-item, div[data-item-id]");

        for (Element item : items) {
            // title stored in aria-label or within a link/span
            String title = item.select("a[aria-label]").attr("aria-label");
            if (title.isBlank()) {
                title = item.select("a span").text();
            }
            if (title.isBlank()) continue;

            // price: try common price spans
            String priceText = "";
            Element priceEl = item.selectFirst("span[aria-hidden=true]");
            if (priceEl != null) {
                priceText = priceEl.text();
            }
            if (priceText.isBlank()) {
                priceText = item.select("span.price-characteristic, span.price-main").text();
            }

            BigDecimal price = parsePrice(priceText);
            if (price == null) continue;

            Element linkEl = item.selectFirst("a[href]");
            if (linkEl == null) continue;

            String link = linkEl.attr("abs:href");
            if (link.isBlank()) continue;

            results.add(buildResult("Walmart", title, price, "USD", link));
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return results;
}

//helper 

private SearchResult buildResult(String store,
                                 String title,
                                 BigDecimal price,
                                 String currency,
                                 String url) {
    SearchResult sr = new SearchResult();

    sr.getStoreName(title);
    sr.getPrice(price);
    sr.getUrl(url);
    return sr;
}

private BigDecimal parsePrice(String priceText) {
    if (priceText == null) return null;

    // keep only digits, dots, commas
    String cleaned = priceText.replaceAll("[^0-9.,]", "");
    if (cleaned.isBlank()) return null;
    cleaned = cleaned.replace(",", ".");

    // if there are multiple dots, keep only the last as decimal separator
    int firstDot = cleaned.indexOf('.');
    int lastDot = cleaned.lastIndexOf('.');
    if (firstDot != -1 && lastDot != firstDot) {
        cleaned = cleaned.substring(0, lastDot).replace(".", "") + cleaned.substring(lastDot);
    }

    try {
        return new BigDecimal(cleaned);
    } catch (NumberFormatException e) {
        return null;
    }
}
}