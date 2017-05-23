/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.book.repository;

import com.luosoy.book.cmp.ChapterCMP;
import com.luosoy.book.dto.ChapterInfoDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapterRepository extends JpaRepository<ChapterCMP, String>, JpaSpecificationExecutor<ChapterCMP> {

    @Query(value = "select new com.luosoy.book.dto.ChapterInfoDTO(cc.xh,cc.name,cc.updatetime) from ChapterCMP cc where cc.bookXh=:bookxh")
    public List<ChapterInfoDTO> findChapterInfo(@Param(value = "bookxh") String bookxh);
}
