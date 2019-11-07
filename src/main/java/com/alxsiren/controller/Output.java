package com.alxsiren.controller;

import com.alxsiren.utils.FileManager;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Output implements Runnable {

    @Override
    public void run() {
        //Create temp folder
        File directory = new File("pages");
        directory.mkdir();
        if (directory.exists() && directory.isDirectory()) {

            //scanning always folder where files is appearing
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scanDirectory(directory);
            }
        } else {
            System.out.println("Directory isn`t found");
        }
    }

    private static void scanDirectory(File directory) {
        File[] files = directory.listFiles();
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (final File f : files) {
            if (!f.isFile()) {
                continue;
            }
            service.execute(new Runnable() {
                @Override
                public void run() {
                    String fullFileName = "pages/" + f.getName();
                    StringBuffer sb = new StringBuffer();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName), "UTF-8"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                        }
                        ExtractDataFromPages.extractData(sb.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        FileManager.deleteFile(fullFileName);
                    }
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
