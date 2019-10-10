package com.example.helloworld.database;

import org.litepal.crud.LitePalSupport;

// 在litepal使用中 对应Book表
// LitePalSupport 里提供了对数据的CRUD方法
public class Book extends LitePalSupport {

    private int id;
    private String author;
    private double price;
    private String name;
    private int pages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


}
