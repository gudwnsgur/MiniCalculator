package com.dku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MyScanner {

    List<String> inputFile = new ArrayList<>();


    public MyScanner(String path) {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputFile.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    public void printInputFile () {
        for(String input : inputFile) {
            System.out.println(input);
        }
    }
}

/*

inputfile 을 scanner 하자.
일단 special form에서 지정을 해놓고
한문자씩읽는데




 */
