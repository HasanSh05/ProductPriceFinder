package com.app.ui;

import com.app.image.BarcodeProcessor;
import com.app.image.OcrProcessor;
import com.app.product.Product;
import com.app.product.ProductIdentifierService;
import com.app.price.DummyPriceSource;
import com.app.price.PriceAggregator;
import com.app.price.PriceSource;
import com.app.price.SearchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {

    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);
    //asks user la path w converts it into a file
        System.out.println("===Welcome to Waffer ===");
        System.out.print("Enter image file path: ");

        String imagePath = sc.nextLine();
        File img = new File(imagePath);

        if (!img.exists()) {
            System.out.println("Error: File does not exist!");
            return;
        }
        
       //reads l content tabaa l image,byeemil extract lal ocr(barcode baad ma eemlneha)
        String ocrText = "";
        String detectedBarcode = "";

        try {
            OcrProcessor ocr = new OcrProcessor();
            ocrText = ocr.extractText(img);

            BarcodeProcessor bcp = new BarcodeProcessor();
            detectedBarcode = bcp.extractBarcode(img);

        } catch (Exception e) {
            System.out.println("Error reading image content: " + e.getMessage());
        }
        
        System.out.println("OCR RAW TEXT: " + ocrText);

        
        //trues to identify l product mn khilel l ocr text
    
        ProductIdentifierService identifier = new ProductIdentifierService();

        Product product = identifier.identify(ocrText, detectedBarcode);

        System.out.println("Detected product name: " + product.getName());

     // Phase 3: Prepare the price sources (stores)
        List<PriceSource> sources = new ArrayList<>();

        // For now we only use a dummy source until real scraping is added
        sources.add(new DummyPriceSource());

     // Phase 4: Collect prices from all sources
        List<SearchResult> allResults = new ArrayList<>();

        for (PriceSource ps : sources) {
            try {
                List<SearchResult> temp = ps.search(product);  // get results from this source
                allResults.addAll(temp);                       // add them to the main list
            } catch (Exception e) {
                System.out.println("Error while retrieving prices: " + e.getMessage());
            }
        }
        
     // Phase 5: Find the best (cheapest) price
        PriceAggregator aggregator = new PriceAggregator();

        SearchResult best = aggregator.getBestPrice(allResults);

        System.out.println("\n--- Price Results ---");
        for (SearchResult r : allResults) {
            System.out.println(r);
        }

        System.out.println("\nBest price:");
        System.out.println(best);
        
        sc.close();
    



        
    }
}
