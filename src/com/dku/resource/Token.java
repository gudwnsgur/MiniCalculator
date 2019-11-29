package com.dku.resource;

import java.util.List;

public class Token {
    boolean ERR;
    List<String> token;

    public boolean isERR() {
        return ERR;
    }

    public void setERR(boolean ERR) {
        this.ERR = ERR;
    }

    public List<String> getToken() {
        return token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }
}
