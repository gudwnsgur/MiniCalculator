package com.dku.resource;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    public HashMap<String, Integer> id = new HashMap<>();
    // id만 예외로 (토큰번호, 토큰이름)이 아닌 (토큰값, 토큰이름)
    public HashMap<Integer, String> constant = new HashMap<>();

    public SymbolTable() {

    }

    public void putId(String tokenName, int tokenId) {
        id.put(tokenName, tokenId);
    }

    public void putConstant(int tokenValue) {
        constant.put(tokenValue, Integer.toString(tokenValue));
    }

    public void printTable() {
        System.out.println("-------------Symbol Table-------------");
        printId();
        printConstant();
        System.out.println("--------------------------------------");
    }

    public void printId() {
        System.out.println("Id");
        for (Map.Entry<String, Integer> entry : id.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }

    public void printConstant() {
        System.out.println("Constant");
        for (Map.Entry<Integer, String> entry : constant.entrySet()) {
            System.out.println(entry.getKey() + " (4, " + entry.getValue() + ")");
        }
    }
}


