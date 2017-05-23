package com.luosoy.book.controller;

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

    @RequestMapping(value = "/book/{bookid}", method = RequestMethod.GET)
    public String book(@PathVariable String bookid, HttpServletRequest request) {
        
        return "book/book";
    }

    @RequestMapping(value = "/book/{bookid}/{chapterid}", method = RequestMethod.GET)
    public String chapter(@PathVariable String bookid, @PathVariable String chapterid, HttpServletRequest request) {
        System.out.println(bookid);
        System.out.println(chapterid);
        return "book/chapter";
    }

}
