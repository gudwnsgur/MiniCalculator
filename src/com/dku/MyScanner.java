package com.dku;

import com.dku.resource.OpTable;
import com.dku.resource.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class MyScanner {

    final private static String onlyDigit = "^[0-9]*$";
    boolean ERR ;
    int idIndex = 0;
    OpTable opTable;
    SymbolTable symbolTable;

    List<String> inputFile = new ArrayList<>(); // 받은 코드를 \n 로 나눈 List
    List<String> realToken = new ArrayList<>(); // 모든 token 을 나눈 List

    public MyScanner(OpTable opTable, SymbolTable symbolTable) {
        this.opTable = opTable;
        this.symbolTable = symbolTable;
    }   // 파일을 받아 inputFile List 에 줄 단위로 추가

/*        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                inputFile.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }*/

    public List<String> scan(String line) {
        ERR = false;
        inputFile.add(line);

        realToken.clear();
        tokenizeAllCode(); // inputFile 을 tokenize

        inputFile.remove(line);
        if(ERR) {
            realToken.add("Error");
        }
        return realToken;
    }


    public void tokenizeAllCode() {
        String[] ops = opTable.operator.values().toArray(new String[0]);
        String[] delimiters = opTable.delimiter.values().toArray(new String[0]);

        List<String> divisionList = new ArrayList<>();

        for (String str : ops) divisionList.add(str);
        for (String str : delimiters) divisionList.add(str);
        divisionList.add(" ");
        // 식별자, 구별자, " " 가 존재하는 List 생성

        for (String input : inputFile) {
            int index = 0;
            String[] arrayWord = input.split("");

            int i;
            for (i = 0; i < arrayWord.length; i++) {

                for (String separator : divisionList) {
                    if (arrayWord[i].equals(separator)) {

                        realToken.add(input.substring(index, i));
                        index = i;
                        realToken.add(input.substring(i, i + 1));
                        index = i + 1;
                    }
                }
            }
            boolean isLastWordSeparator = false;
            for (String separator : divisionList) {
                if (arrayWord[i - 1].equals(separator)) isLastWordSeparator = true;
                else isLastWordSeparator = false;
            }
            if (!isLastWordSeparator) {
                realToken.add(input.substring(index, i));
                index = i + 1;
            }
        }
        realToken.removeAll(Collections.singleton(" "));
        realToken.removeAll(Collections.singleton(""));
    }
    // 구분된 token 중 SymbolTable 에 들어가야 할 token 추가
    public void addToSymbolTable() {
        for (String token : realToken) {
            boolean isKeyword = false;
            for (String keyword : opTable.keyword.values()) {
                if (token.matches(keyword)) {
                    isKeyword = true;
                }
            }

            if (token.charAt(0) >= 'a' && token.charAt(0) <= 'z' && !isKeyword) {
                if (token.length() != 1) ERR = true;    // a~z 인지 확인

                boolean isInIdTable = false;
                for (String id : symbolTable.id.keySet()) {
                    if (token.equals(id))
                        isInIdTable = true;     // 이미 존재하는 변수인지 확인
                }

                if (!isInIdTable && !ERR)
                    symbolTable.putId(token, 0);

            }   // 첫 문자가 알파벳일 때 id 인지 확인 후 추가

            if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
                if (token.matches(onlyDigit)) {
                    int num = Integer.parseInt(token);
                    symbolTable.putConstant(num);
                } else {
                    ERR = true;
                }

            }   // 첫 문자가 상수일 때 constant 인지 확인 후 추가
        }
    }


}