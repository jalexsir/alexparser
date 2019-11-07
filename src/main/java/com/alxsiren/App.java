package com.alxsiren;

import com.alxsiren.controller.ExtractMainPage;
import com.alxsiren.controller.Output;

public class App {
    public static void main(String[] args) {
        //First thread with extracting links
        Thread extractSite = new Thread(new ExtractMainPage());
        extractSite.start();

        //Second thread with scunning and extracting data to file
        Thread parseSite = new Thread(new Output());
        parseSite.setDaemon(true);
        parseSite.start();

    }
}
