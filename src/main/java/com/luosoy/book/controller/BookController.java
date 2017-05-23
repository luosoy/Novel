/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lenovo
 */
@Controller
public class BookController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "book/index";
    }
    
    @RequestMapping(value = "/{novelType}", method = RequestMethod.GET)
    public String novelType(@PathVariable String novelType) {
        System.out.println(novelType);
        return "book/noveltype";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(String search, HttpServletRequest request) {
        request.setAttribute("search", search);
        return "book/search";
    }
    @RequestMapping(value = "/book/{bookid}", method = RequestMethod.GET)
    public String book(@PathVariable String bookid, HttpServletRequest request) {
        System.out.println(bookid);
        return "book/book";
    }
    
    @RequestMapping(value = "/book/{bookid}/{chapterid}", method = RequestMethod.GET)
    public String chapter(@PathVariable String bookid,@PathVariable String chapterid, HttpServletRequest request) {
        System.out.println(bookid);
        System.out.println(chapterid);
        return "book/chapter";
    }

}
