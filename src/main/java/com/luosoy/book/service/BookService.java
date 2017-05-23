/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.service;

import com.luosoy.book.dto.BookDTO;
import com.luosoy.book.repository.BookRepository;
import com.luosoy.frame.beans.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 罗真朋
 * @version 1.0
 */
@Service
public class BookService {

    @Autowired
    private BookRepository br;

    public BookDTO findBook(String bookxh) {
        BookDTO bookDTO = new BookDTO();
        BeanConvertUtil.convert(br.findOne(bookxh), bookDTO);
        return bookDTO;
    }

}
