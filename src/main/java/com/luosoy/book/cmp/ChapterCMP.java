/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.cmp;

import com.luosoy.book.sequence.BookSequenceProducer;
import com.luosoy.book.sequence.ChapterSequenceProducer;
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
 * <pre>类名: ChapterCMP</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2017-05-02 下午05:39:20 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "chapter")
@EntityListeners(value = {IdInjectionEntityListener.class})
public class ChapterCMP implements Serializable {

    private static final long serialVersionUID = 7260992935692974981L;

    @Id
    @Basic(optional = false)
    @IdSequenceConsumer(producerClass = ChapterSequenceProducer.class)
    @Column(nullable = false, length = 32)
    private String xh;
    @Basic(optional = false)
    @Column(name = "book_xh", nullable = false, length = 32)
    private String bookXh;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String context;
    private Integer number;
    private Integer clickcount;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character yxbz;

    public ChapterCMP() {
    }

    public ChapterCMP(String xh) {
        this.xh = xh;
    }

    public ChapterCMP(String xh, String bookXh, String name, String context, Date updatetime, Character yxbz) {
        this.xh = xh;
        this.bookXh = bookXh;
        this.name = name;
        this.context = context;
        this.updatetime = updatetime;
        this.yxbz = yxbz;
    }

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
    public int hashCode() {
        int hash = 0;
        hash += (xh != null ? xh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChapterCMP)) {
            return false;
        }
        ChapterCMP other = (ChapterCMP) object;
        if ((this.xh == null && other.xh != null) || (this.xh != null && !this.xh.equals(other.xh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.book.cmp.ChapterCMP[ xh=" + xh + " ]";
    }

}
