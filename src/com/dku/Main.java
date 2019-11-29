package com.dku;

import com.dku.resource.OpTable;
import com.dku.resource.SymbolTable;

import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //String path = "C:\\Users\\Hyoung\\Desktop\\3-2\\오토마타\\LexicalAnalyzer\\inputFile.txt";
        //MyScanner myScanner = new MyScanner(path);

        OpTable opTable = new OpTable();
        SymbolTable symbolTable = new SymbolTable();
        Scanner sc = new Scanner(System.in);
        MyScanner myScanner = new MyScanner(opTable, symbolTable);
        MyParser myParser;
        List<String> token; // 모든 token 을 나눈 List

        String status = null;
        boolean comment = false;
        boolean isStarted = false;


        while (true) {
            String line = sc.nextLine();


            token = myScanner.scan(line);
/*            if(token.get(0).equals(opTable.operator.get(1))) { //   op://
                comment = true;
            }*/

            //여기서부터
            if (token.get(0).equals(opTable.operator.get(9)) &&
                    token.get(1).equals(opTable.operator.get(9))) {
                comment = true;
            }
            // 여기까지 지워야될거

            if (comment) ; // comment 상태면 무시

            else if (token.contains("Error"))
                System.out.println("ERR : wrong token existed");
                // scanner 에서 오류 발생 시 parser X

            else {
                myParser = new MyParser(opTable, symbolTable, token);   // 나뉜 토큰으로 Parser 생성

                if (token.get(0).equals(opTable.keyword.get(50))) {
                    if (token.contains(opTable.keyword.get(0))) {
                        isStarted = true;
                        status = "program";
                    } else {
                        System.out.println("ERR : wrong program name");
                    }
                } // program 시작을 알림

                else if (token.get(0).equals(opTable.keyword.get(53))) {
                    System.out.println("program end");  // end
                    break;
                }   // program 종료를 알림 ( while 문 종료 )

                if (isStarted) {    // program 이 시작했을 때
                    if (token.get(0).equals(opTable.keyword.get(51)))
                        status = opTable.keyword.get(51);   // var
                    else if (token.get(0).equals(opTable.keyword.get(52)))
                        status = opTable.keyword.get(52);   // begin

                    if (myParser.parse(status)) {
                        if (status.equals(opTable.keyword.get(51))) {
                            myScanner.addToSymbolTable();
                        }   // 상태가 var 일 때만 symbol table 의 id 에 변수 저장
                    }// 해당 상태에 대한 parser 시작
                    else {
                        System.out.println("Err : syntax error");
                    }
                } else {
                    System.out.println("ERR : program not started");
                }
                // program 이 시작하지 않았을 때

         /*       if(token.get(token.size()-1).equals(opTable.keyword.get(3))) {
                    comment = false;
                }*/

                // myParser.print();
            }

/*            if(token.get(0).equals(opTable.operator.get(1))) { //   op://
                comment = false;
            }*/

            // 여기서부터
            if ((token.get(0).equals(opTable.operator.get(9)) &&
                    token.get(1).equals(opTable.operator.get(9)))
            ) {
                comment = false;
            }
            // 여기까지 나중에 지워야 될거

        }
    }
}