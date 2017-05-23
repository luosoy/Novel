/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.service;

import com.luosoy.book.cmp.ImageCMP;
import com.luosoy.book.dto.ImageDTO;
import com.luosoy.book.repository.ImageRepository;
import com.luosoy.frame.beans.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository ir;

    public ImageDTO findImage(String bookxh) {
        ImageDTO idto = new ImageDTO();
        ImageCMP icmp = ir.findByBookXhAndYxbz(bookxh, "Y");
        BeanConvertUtil.convert(icmp, idto);
        return idto;
    }

}
