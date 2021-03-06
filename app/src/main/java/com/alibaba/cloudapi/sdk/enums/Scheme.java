package com.alibaba.cloudapi.sdk.enums;

/**
 * Created by fred on 2017/7/14.
 */
public enum Scheme {
    HTTP("HTTP://"),
    HTTPS("HTTPS://"),
    WEBSOCKET("WS://");

    String value;

    Scheme(String name){
        this.value = name;
    }

    public String getValue(){
        return value;
    }
}
