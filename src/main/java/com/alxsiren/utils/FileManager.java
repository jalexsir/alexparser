package com.alxsiren.utils;

import com.alxsiren.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class FileManager {

    private static int counterProduct = 0;

    public static int getCounterProduct() {
        return counterProduct;
    }

    public static void createPagesAsFiles(String urlData, int index) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("pages/" + index + ".txt"), "UTF-8"));
            writer.write(urlData);
        } catch (IOException ex) {

        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    public static void createFileAmount() {
        try (BufferedWriter output = new BufferedWriter(new FileWriter("infolog.txt", true))) {
            output.append("Amount of triggered HTTP requests: " + WebDocuments.getCounter());
            output.newLine();
            output.append("Amount of extracted products: " + getCounterProduct());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void createOutputFile(Product product) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.csv", true)));
             CSVPrinter printer = new CSVPrinter(bw, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            if (counterProduct == 0) {
                printer.printRecord("ProductName", "Brand", "Color", "Price", "ArticleID");
            }
            counterProduct++;
            printer.printRecord(product.getName(), product.getBrand(), product.getColor(),
                    product.getPrice(),
                    product.getArticleId());
        }
    }

    public static synchronized void deleteTempFolder(){
        File directory = new File("pages");
        if (directory.exists() && directory.isDirectory()) {
            directory.delete();
        }
    }

    public static synchronized void deleteFile(String filename){
        File file = new File(filename);
        if(file.exists()){
            file.delete();
        }
    }
}
