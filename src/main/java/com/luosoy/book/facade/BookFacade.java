/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.facade;

import com.luosoy.book.dto.BookInfoDTO;
import com.luosoy.book.service.BookService;
import com.luosoy.book.service.ChapterService;
import com.luosoy.book.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class BookFacade {

    @Autowired
    private BookService bs;
    @Autowired
    private ChapterService cs;
    @Autowired
    private ImageService is;

    public BookInfoDTO findBookInfo(String bookxh) {
        BookInfoDTO bidto = new BookInfoDTO();
        bidto.setBdto(bs.findBook(bookxh));
        bidto.setCidtos(cs.findChapterInfo(bookxh));
        bidto.setIdto(is.findImage(bookxh));
        return bidto;
    }

}
