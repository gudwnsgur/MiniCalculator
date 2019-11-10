package com.dku;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\Hyoung\\Desktop\\3-2\\오토마타\\LexicalAnalyzer\\inputFile.txt";
        MyScanner myScanner = new MyScanner(path);

        myScanner.printInputFile();


    }
}