package com.alxsiren.controller;

import com.alxsiren.entity.Product;
import com.alxsiren.utils.FileManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//Extract data from Product page

public class ExtractDataFromPages {
    private static String productName;
    private static String brand;
    private static String color;
    private static String price;
    private static String article;

    public static void extractData(String page) throws IOException {
        Document document = Jsoup.parse(page);

        productName = document.select("div.BrandAndProductName__ProductName-iay39c-1.fgjQqH").text();
        brand = document.select("img.BrandAndProductName__BrandLogo-iay39c-0.iBroms").attr("alt");
        color = document.getElementsByAttributeValue("data-test-id", "BrandNameProductNameColorName").text();
        price = document.getElementsByAttributeValue("class", "PriceBoxExtended__StyledPriceBox-sc-1t8547r-0 iogkTg").text();
        article = document.getElementsByAttributeValue("data-test-id", "ArticleNumber").text();

        if (!color.isEmpty()) {
            color = color.substring(color.lastIndexOf("in") + 3);
        }

        if (!price.isEmpty()) {
            price = price.split(" ")[0];
        }

        if (!article.isEmpty()) {
            article = article.split(" ")[1] + "";
        }

        Product product = new Product(productName, brand, color, price, article);
        FileManager.createOutputFile(product);


    }
}
