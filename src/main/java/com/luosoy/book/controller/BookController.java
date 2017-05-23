package com.luosoy.book.controller;

import com.luosoy.book.dto.BookInfoDTO;
import com.luosoy.book.facade.BookFacade;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookFacade bf;

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

    @RequestMapping(value = "/book/{bookxh}", method = RequestMethod.GET)
    public String book(@PathVariable String bookxh, HttpServletRequest request) {
        BookInfoDTO bidto = bf.findBookInfo(bookxh);
        
        return "book/book";
    }

    @RequestMapping(value = "/book/{bookxh}/{chapterxh}", method = RequestMethod.GET)
    public String chapter(@PathVariable String bookxh, @PathVariable String chapterxh, HttpServletRequest request) {
        System.out.println(bookxh);
        System.out.println(chapterxh);
        return "book/chapter";
    }

}
