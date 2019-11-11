package com.dku;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\Hyoung\\Desktop\\3-2\\오토마타\\LexicalAnalyzer\\inputFile.txt";
        MyScanner myScanner = new MyScanner(path);

        myScanner.printInputFile();     // input file 출력
        myScanner.scan();
        System.out.println();
        myScanner.printTokenized();     // 잘린 토큰들 나열
        System.out.println();
        myScanner.printResultTable();   // Table 출력

    }
}