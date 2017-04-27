/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/search")
    public String search(String search, HttpServletRequest request) {
        request.setAttribute("search", search);
        return "book/search";
    }

}
