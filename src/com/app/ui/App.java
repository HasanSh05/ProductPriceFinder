package com.app.ui;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Product Price Finder ===");
        System.out.print("Enter image file path: ");

        String imagePath = sc.nextLine();

        // For now we just echo it back.
        // Later this will call the Image Processing + Price modules.
        System.out.println("You entered: " + imagePath);
        System.out.println("Next step: we will process this image and detect the product.");

        
        sc.close();
        
        
    }
}
