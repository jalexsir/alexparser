package com.alxsiren.controller;

import com.alxsiren.utils.FileManager;
import com.alxsiren.utils.WebDocuments;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;

//Extract links from main page with products

public class ExtractMainPage implements Runnable {
    private static String mainUrl = "https://www.aboutyou.de/maenner/bekleidung";
    private static int maxSeconds = 13;
    private static int minSeconds = 5;

    private static ArrayList<String> productLinks = new ArrayList<>();

    @Override
    public void run() {
        try {
            extractProductLinks(mainUrl);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void extractProductLinks(String url) throws InterruptedException {
        mainUrl = url;
        Document document = WebDocuments.getWebDocument(url);
        Elements elements = document.getElementsByAttributeValue("class", "MixedTileRowContainer-sc-1n50fuf-0 hzvjNO");

        addLinksByElements(elements);

        //Shuffle links to connect not 1,2,3,4.....
        Collections.shuffle(productLinks);
        StringBuffer pageProduct = null;

        int countLinksFromMainPage = productLinks.size();

        for (int i = 0; i < productLinks.size(); i++) {
            pageProduct = new StringBuffer();
            Document doc = WebDocuments.getWebDocument(productLinks.get(i));

            //add inner links by color from productPage
            if (i < countLinksFromMainPage) {
                addLinksByElements(doc.select("div.BuyBox__OverlayWrapper-sc-12rq7nw-3.bnzdui"));
                System.out.print("\r" + "Counting....please wait");
            } else {
                System.out.print("\r" + "Saving products -> " + (i + 1) + "/" + productLinks.size() + "   ");
            }

            //add content to txt files
            pageProduct.append(doc.select("section.DividerSection-qv552u-0.bTtcZu"));
            pageProduct.append(doc.select("div.Tabs__StyledTabs-sc-1nuz8fx-1.jSzmhj"));
            FileManager.createPagesAsFiles(pageProduct.toString(), i + 1);

            //Random sleep to immitation human serfing to internet
            Thread.sleep(((int) (Math.random() * ((maxSeconds - minSeconds) + 1)) + minSeconds) * 1000);
        }

        //As result of finish program
        FileManager.createFileAmount();
        System.out.print("\r" + "Result in a file -> " + "result.csv" + "   ");
        System.out.println("");
        System.out.println("Amount of triggered HTTP requests: " + WebDocuments.getCounter());
        System.out.println("Amount of extracted products: " + FileManager.getCounterProduct());
        Thread.sleep(3000);
        FileManager.deleteTempFolder();
    }

    //extracter only links from elements
    private static void addLinksByElements(Elements elements) {
        for (Element link : elements.select("a")) {
            productLinks.add(mainUrl.substring(0, mainUrl.lastIndexOf(".") + 3) + link.attr("href"));
        }
    }
}
