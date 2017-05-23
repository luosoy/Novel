/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.dto;

import java.util.List;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class BookInfoDTO {

    private BookDTO bdto;
    private List<ChapterInfoDTO> cidtos;
    private ImageDTO idto;
    private String imageUrl;

    public BookDTO getBdto() {
        return bdto;
    }

    public void setBdto(BookDTO bdto) {
        this.bdto = bdto;
    }

    public List<ChapterInfoDTO> getCidtos() {
        return cidtos;
    }

    public void setCidtos(List<ChapterInfoDTO> cidtos) {
        this.cidtos = cidtos;
    }

    public ImageDTO getIdto() {
        return idto;
    }

    public void setIdto(ImageDTO idto) {
        this.idto = idto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BookInfoDTO{" + "bdto=" + bdto + ", cidtos=" + cidtos + ", idto=" + idto + ", imageUrl=" + imageUrl + '}';
    }

}
