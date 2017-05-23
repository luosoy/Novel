/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.dto;

import java.util.Date;

public class BookDTO {

    private String xh;
    private String name;
    private String introduction;
    private String author;
    private String imageXh;
    private String booktypeDm;
    private Date updatetime;
    private String lastchapterXh;
    private String statusDm;
    private Integer number;
    private Integer clickcount;
    private Integer searchcount;
    private Character yxbz;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageXh() {
        return imageXh;
    }

    public void setImageXh(String imageXh) {
        this.imageXh = imageXh;
    }

    public String getBooktypeDm() {
        return booktypeDm;
    }

    public void setBooktypeDm(String booktypeDm) {
        this.booktypeDm = booktypeDm;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getLastchapterXh() {
        return lastchapterXh;
    }

    public void setLastchapterXh(String lastchapterXh) {
        this.lastchapterXh = lastchapterXh;
    }

    public String getStatusDm() {
        return statusDm;
    }

    public void setStatusDm(String statusDm) {
        this.statusDm = statusDm;
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

    public Integer getSearchcount() {
        return searchcount;
    }

    public void setSearchcount(Integer searchcount) {
        this.searchcount = searchcount;
    }

    public Character getYxbz() {
        return yxbz;
    }

    public void setYxbz(Character yxbz) {
        this.yxbz = yxbz;
    }

    @Override
    public String toString() {
        return "BookDTO{" + "xh=" + xh + ", name=" + name + ", introduction=" + introduction + ", author=" + author + ", imageXh=" + imageXh + ", booktypeDm=" + booktypeDm + ", updatetime=" + updatetime + ", lastchapterXh=" + lastchapterXh + ", statusDm=" + statusDm + ", number=" + number + ", clickcount=" + clickcount + ", searchcount=" + searchcount + ", yxbz=" + yxbz + '}';
    }

}
