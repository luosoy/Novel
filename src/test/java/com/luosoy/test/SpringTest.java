/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.test;

import com.luosoy.book.dto.ChapterInfoDTO;
import com.luosoy.book.service.ChapterService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>类名: TestTjycl</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016年05月30日 上午10:47:02 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = {"classpath*:/spring/test-context.xml"})
public class SpringTest {

    @Autowired
    private ChapterService cs;

    @Test
    public void test() {
        List<ChapterInfoDTO> findChapterInfo = cs.findChapterInfo("11111");
        System.out.println(findChapterInfo);
    }
}
