package com.dku.resource;

import java.util.HashMap;
import java.util.Iterator;


public class OpTable {
    public HashMap<Integer, String> delimiter = new HashMap<>();
    public HashMap<Integer, String> keyword = new HashMap<>();
    public HashMap<Integer, String> operator = new HashMap<>();

    public OpTable() {

        operator.put(4, ">"); operator.put(5, "<"); // 토큰 번호를 몰라 임의로 지정

        operator.put(6, "+"); operator.put(7, "-");
        operator.put(8, "*"); operator.put(9, "/");
        operator.put(10, "=");

        delimiter.put(11,",");  delimiter.put(12,";");
        delimiter.put(13, "("); delimiter.put(14, ")");
        delimiter.put(15, "["); delimiter.put(16,"]");
        delimiter.put(17, "{"); delimiter.put(18, "}");

        keyword.put(25, "boolean"); keyword.put(26, "int");
        keyword.put(27, "float");   keyword.put(28, "double");
        keyword.put(29, "String");

        keyword.put(30, "if");   keyword.put(31,"else");
        keyword.put(32, "for");  keyword.put(33, "while");
        keyword.put(34, "true"); keyword.put(35, "false");
    }



    public void printTable() {
        System.out.println("-------------Operation Table-------------");
        printOperator();
        printDelimiter();
        printKeyword();
        System.out.println("-----------------------------------------");
    }
    public void printOperator() {
        System.out.println("operator");
        Iterator<Integer> mapIter = operator.keySet().iterator();
        while(mapIter.hasNext()){
            int key = mapIter.next();
            String value = operator.get(key);
            System.out.println(value+ " (" +key+ ", -)");
        }
    }
    public void printDelimiter() {
        System.out.println("delimiter");
        Iterator<Integer> mapIter = delimiter.keySet().iterator();
        while(mapIter.hasNext()){
            int key = mapIter.next();
            String value = delimiter.get(key);
            System.out.println(value+ " (" +key+ ", -)");
        }
    }
    public void printKeyword() {
        System.out.println("keywords");
        Iterator<Integer> mapIter = keyword.keySet().iterator();
        while(mapIter.hasNext()){
            int key = mapIter.next();
            String value = keyword.get(key);
            System.out.println(value+ " (" +key+ ", -)");
        }
    }
}