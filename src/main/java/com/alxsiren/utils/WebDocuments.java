package com.alxsiren.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//Return Document using Jsoup and counterRequsts

public class WebDocuments {
    private static int counter;

    public static int getCounter() {
        return counter;
    }

    public static Document getWebDocument(String url) {
        counter++;
        Document page = null;
        try {
            page = Jsoup.connect(url)
                    .userAgent(RandomAgent.getAgent())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }
}
