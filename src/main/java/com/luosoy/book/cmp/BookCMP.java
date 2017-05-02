/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.cmp;

import com.luosoy.book.sequence.BookSequenceProducer;
import com.luosoy.frame.jpa.identity.IdInjectionEntityListener;
import com.luosoy.frame.jpa.identity.IdSequenceConsumer;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <pre>类名: BookCMP</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2017-05-02 下午05:39:20 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "book")
@EntityListeners(value = {IdInjectionEntityListener.class})
public class BookCMP implements Serializable {

    private static final long serialVersionUID = -674866436569127688L;

    @Id
    @Basic(optional = false)
    @IdSequenceConsumer(producerClass = BookSequenceProducer.class)
    @Column(nullable = false, length = 32)
    private String xh;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String introduction;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String author;
    @Column(name = "image_xh", length = 32)
    private String imageXh;
    @Basic(optional = false)
    @Column(name = "booktype_dm", nullable = false, length = 32)
    private String booktypeDm;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;
    @Column(name = "lastchapter_xh", length = 32)
    private String lastchapterXh;
    @Basic(optional = false)
    @Column(name = "status_dm", nullable = false, length = 2)
    private String statusDm;
    private Integer number;
    private Integer clickcount;
    private Integer searchcount;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character yxbz;

    public BookCMP() {
    }

    public BookCMP(String xh) {
        this.xh = xh;
    }

    public BookCMP(String xh, String name, String introduction, String author, String booktypeDm, String statusDm, Character yxbz) {
        this.xh = xh;
        this.name = name;
        this.introduction = introduction;
        this.author = author;
        this.booktypeDm = booktypeDm;
        this.statusDm = statusDm;
        this.yxbz = yxbz;
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
    public int hashCode() {
        int hash = 0;
        hash += (xh != null ? xh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookCMP)) {
            return false;
        }
        BookCMP other = (BookCMP) object;
        if ((this.xh == null && other.xh != null) || (this.xh != null && !this.xh.equals(other.xh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.book.cmp.BookCMP[ xh=" + xh + " ]";
    }

}
