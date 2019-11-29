package com.dku;

import com.dku.resource.OpTable;
import com.dku.resource.SymbolTable;
import java.util.*;

import static java.lang.Math.pow;

public class MyParser {
    OpTable opTable;
    SymbolTable symbolTable;
    List<String> token; // 모든 token 을 나눈 List
    boolean syntaxErr;
    final private static String onlyDigit = "^[0-9]*$";
    int dex;
    Stack stack = new Stack();
    boolean Err = false;

    public MyParser(OpTable opTable, SymbolTable symbolTable, List<String> token) {
        this.opTable = opTable;
        this.symbolTable = symbolTable;
        this.token = token;
    }
    
    public boolean parse(String status) {
        stack.clear();
        Err = false;
        boolean parsing = true;

        if(token.isEmpty()) return true;
        if(status.equals(opTable.keyword.get(50))) {
            return true;
        }   // program
        else if(status.equals(opTable.keyword.get(51))) {
            if(token.get(0).equals(status)) return true;
            if(!declare()) Err=true;   // declare 문법 오류 시 false
        }   // var
        else if(status.equals(opTable.keyword.get(52))) {
            if(token.get(0).equals(status)) return true;
            if(!begin()) Err=true; // begin 문법 오류 시 false
        }   // begin
        return !Err;
    }
    boolean declare() {
        syntaxErr = false;
        int index=0;

        decS(index);


        if(syntaxErr) return false;
        else return true;
    }
    void decS(int index) {
        if( token.get(index).equals(opTable.keyword.get(26)) || // int
            token.get(index).equals(opTable.keyword.get(27)) || // float
            token.get(index).equals(opTable.keyword.get(28)) ){ // double

            decA(++index);
        }
        else syntaxErr=true;
    }
    void decA (int index) {
        if(token.get(index).charAt(0) >='a' && token.get(index).charAt(0) <='z') {  // alphabet
            decB(++index);
        }
        else syntaxErr = true;
    }
    void decB(int index) {
        if(token.get(index).equals(opTable.delimiter.get(12))) {    // ;
            return;
        }
        else if(token.get(index).equals(opTable.delimiter.get(11))) { // ,
            index ++;
            if (token.get(index).charAt(0) >= 'a' && token.get(index).charAt(0) <= 'z') {
                decB(++index);
            }
        }
        else {
            syntaxErr = true;
        }
    }




    boolean begin() { // 아직 구현 X
        syntaxErr = false;

        dex=0;


        if(token.get(dex).equals(opTable.keyword.get(54))) {
            dex++; E();
            if(stack.size()==1) {
                System.out.println(stack.pop());
            }
        }   // print

        else if(symbolTable.id.containsKey(token.get(dex))) {
            dex++;
            if(token.get(dex).equals(opTable.operator.get(10))) {
                dex++; E();
                if(stack.size()==1) {
                    symbolTable.id.put(token.get(0), (int) stack.pop());
                }
            }
            else syntaxErr = true;
        } // 할당문

        else syntaxErr = true;

        if(syntaxErr) return false;
        else return true;
    }

    void E () {
        T();
        while(token.get(dex).equals(opTable.operator.get(6)) || // +, -
              token.get(dex).equals(opTable.operator.get(7)) ) {
            String op = token.get(dex);
            dex++; T(); operate(op);
        }
    }
    void T() {
        F();
        while(token.get(dex).equals(opTable.operator.get(8)) || // *, /
              token.get(dex).equals(opTable.operator.get(9))) {
            String op = token.get(dex);
            dex++; F(); operate(op);
        }
    }
    void F() {
        P();
        while(token.get(dex).equals(opTable.operator.get(11))) {// ^
            String op = token.get(dex);
            dex++; P(); operate(op);
        }

    }
    void P() {
        if(symbolTable.id.containsKey(token.get(dex))) {
            stack.push(symbolTable.id.get(token.get(dex)));    // 변수
        }
        else if(token.get(dex).matches(onlyDigit)) {
            stack.push(Integer.parseInt(token.get(dex)));   // 상수
        }
        else if(token.get(dex).equals(opTable.delimiter.get(13))) {// (
            dex++;
            E();
            if(!token.get(dex).equals(opTable.delimiter.get(14))) { // )
                Err=true;
            }
        }
        dex++;
     }
    void operate(String op) {
        int d2=(int)stack.pop();
        int d1=(int)stack.pop();

        if(op=="/" && d2==0) {
            System.out.println("ERR : division by 0");
            Err = true;
        }
        if(Err) return;
        if(op.equals(opTable.operator.get(6))) stack.push(d1+d2);   // +
        else if(op.equals(opTable.operator.get(7))) stack.push(d1-d2); // -
        else if(op.equals(opTable.operator.get(8))) stack.push(d1*d2); // *
        else if(op.equals(opTable.operator.get(9))) stack.push(d1/d2); // /
        else if(op.equals(opTable.operator.get(11))) stack.push((int)pow(d2,d1)); // ^
    }


    public void print() {
        System.out.println(token);

        System.out.println("Id Table 현황");
        for(Map.Entry<String, Integer> entry : symbolTable.id.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }
}
