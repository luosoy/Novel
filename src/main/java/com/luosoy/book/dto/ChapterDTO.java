/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.dto;

import java.util.Date;

public class ChapterDTO {

    private String xh;
    private String bookXh;
    private String name;
    private String context;
    private Integer number;
    private Integer clickcount;
    private Date updatetime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Character getYxbz() {
        return yxbz;
    }

    public void setYxbz(Character yxbz) {
        this.yxbz = yxbz;
    }

    @Override
    public String toString() {
        return "ChapterDTO{" + "xh=" + xh + ", bookXh=" + bookXh + ", name=" + name + ", context=" + context + ", number=" + number + ", clickcount=" + clickcount + ", updatetime=" + updatetime + ", yxbz=" + yxbz + '}';
    }

}
