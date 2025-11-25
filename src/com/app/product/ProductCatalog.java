package com.app.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ProductCatalog {

    private ArrayList<String> products;

    public ProductCatalog() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try {
            File file = new File("products.txt"); // file in project root
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    products.add(line.trim().toLowerCase());
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading product list: " + e.getMessage());
        }
    }

    public String getClosestMatch(String ocrText) {
        if (ocrText == null || ocrText.isEmpty()) return "Unknown Product";

        ocrText = ocrText.toLowerCase();

        String bestMatch = "Unknown Product";
        int bestScore = -1;

        for (String product : products) {
            int score = similarityScore(ocrText, product);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = product;
            }
        }

        return bestMatch;
    }

    private int similarityScore(String a, String b) {
        int score = 0;

        for (String word : b.split(" ")) {
            if (a.contains(word)) score++;
        }

        return score;
    }
}
