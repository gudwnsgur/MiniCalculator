package com.dku.resource;

import java.util.HashMap;
import java.util.Iterator;

public class SymbolTable {
    public HashMap<Integer, String> id = new HashMap<>();
    // id만 예외로 (토큰번호, 토큰이름)이 아닌 (토큰값, 토큰이름)
    public HashMap<Integer, String> constant = new HashMap<>();

    public SymbolTable() {

    }
    public void putId(int tokenId, String tokenName) {
        id.put(tokenId, tokenName);
    }
    public void putConstant(int tokenValue) {
        constant.put(tokenValue, Integer.toString(tokenValue) );
    }
    public void printTable() {
        System.out.println("-------------Symbol Table-------------");
        printId();
        printConstant();
        System.out.println("--------------------------------------");
    }
    public void printId() {
        System.out.println("Id");
        Iterator<Integer> mapIter = id.keySet().iterator();
        while(mapIter.hasNext()){
            int key = mapIter.next();
            String value = id.get(key);
            System.out.println(value+ " (2," +key+ ")");
        }
    }
    public void printConstant() {
        System.out.println("Constant");
        Iterator<Integer> mapIter = constant.keySet().iterator();
        while(mapIter.hasNext()){
            int key = mapIter.next();
            String value = constant.get(key);
            System.out.println(key + " (4, "+value +")");
        }
    }
}


