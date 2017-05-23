/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.service;

import com.luosoy.book.dto.ChapterInfoDTO;
import com.luosoy.book.repository.ChapterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository cr;

    public List<ChapterInfoDTO> findChapterInfo(String bookxh) {
        return cr.findChapterInfo(bookxh);
    }
}
