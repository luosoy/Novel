/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.dto;

public class ImageDTO {

    private String xh;
    private String bookXh;
    private String type;
    private String path;
    private Character yxbz;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getBookXh() {
        return bookXh;
    }

    public void setBookXh(String bookXh) {
        this.bookXh = bookXh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Character getYxbz() {
        return yxbz;
    }

    public void setYxbz(Character yxbz) {
        this.yxbz = yxbz;
    }

    @Override
    public String toString() {
        return "ImageDTO{" + "xh=" + xh + ", bookXh=" + bookXh + ", type=" + type + ", path=" + path + ", yxbz=" + yxbz + '}';
    }

}
