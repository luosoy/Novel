/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.cmp;

import com.luosoy.book.sequence.ImageSequenceProducer;
import com.luosoy.frame.jpa.identity.IdInjectionEntityListener;
import com.luosoy.frame.jpa.identity.IdSequenceConsumer;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Entity
@Table(name = "image")
@EntityListeners(value = {IdInjectionEntityListener.class})
public class ImageCMP implements Serializable {

    private static final long serialVersionUID = 3702168878229139840L;

    @Id
    @Basic(optional = false)
    @IdSequenceConsumer(producerClass = ImageSequenceProducer.class)
    @Column(nullable = false, length = 32)
    private String xh;
    @Basic(optional = false)
    @Column(name = "book_xh", nullable = false, length = 32)
    private String bookXh;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String type;
    @Column(length = 100)
    private String path;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character yxbz;

    public ImageCMP() {
    }

    public ImageCMP(String xh) {
        this.xh = xh;
    }

    public ImageCMP(String xh, String bookXh, String type, Character yxbz) {
        this.xh = xh;
        this.bookXh = bookXh;
        this.type = type;
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
    public int hashCode() {
        int hash = 0;
        hash += (xh != null ? xh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImageCMP)) {
            return false;
        }
        ImageCMP other = (ImageCMP) object;
        if ((this.xh == null && other.xh != null) || (this.xh != null && !this.xh.equals(other.xh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luosoy.book.cmp.ImageCMP[ xh=" + xh + " ]";
    }

}
