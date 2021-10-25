package com.example.myapplication.link_php_db;

public class ReviewData {
    private String storename;
    private String body;
    private String writename;
    private String star;

    public String getStorename(){return storename;}
    public String getBody(){return body;}
    public String getWritename(){return writename;}
    public String getStar(){return star;}

    public void setStorename(String name){this.storename = name;}
    public void setBody(String body){this.body = body;}
    public void setWritename(String writename){this.writename = writename;}
    public void setStar(String star){this.star = star;}
}
