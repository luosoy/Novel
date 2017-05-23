/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.dto;

import java.util.Date;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class ChapterInfoDTO {

    private String xh;
    private String name;
    private Date updatetime;

    public ChapterInfoDTO() {
    }

    public ChapterInfoDTO(String xh, String name, Date updatetime) {
        this.xh = xh;
        this.name = name;
        this.updatetime = updatetime;
    }

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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ChapterInfoDTO{" + "xh=" + xh + ", name=" + name + ", updatetime=" + updatetime + '}';
    }

}
