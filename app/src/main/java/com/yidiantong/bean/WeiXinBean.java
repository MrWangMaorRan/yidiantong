package com.yidiantong.bean;

import java.io.Serializable;

public class WeiXinBean {
    private  String name;
    private  String path;

    public WeiXinBean(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

}
