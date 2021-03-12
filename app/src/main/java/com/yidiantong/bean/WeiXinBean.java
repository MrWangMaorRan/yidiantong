package com.yidiantong.bean;

public class WeiXinBean {
    private  String name;
    private  String path;
    public   String codeone = "0";

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

    @Override
    public String toString() {
        return "WeiXinBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", code='" + codeone + '\'' +
                '}';
    }
}
