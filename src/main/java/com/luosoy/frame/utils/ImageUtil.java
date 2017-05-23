/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.frame.utils;

import com.luosoy.frame.exception.SystemException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ImageUtil.class);

    // 读取本地图片获取输入流
    public static FileInputStream readImage(String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(path));
        } catch (FileNotFoundException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SystemException(ex.getMessage(), SystemException.REQUEST_EXCEPTION);
        }
        return fis;
    }

    // 读取表中图片获取输出流
    public static void readBin2Image(InputStream in, String targetPath) {
        File file = new File(targetPath);
        String path = targetPath.substring(0, targetPath.lastIndexOf("/"));
        if (!file.exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new SystemException(ex.getMessage(), SystemException.REQUEST_EXCEPTION);
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    LOG.error(ex.getMessage(), ex);
                    throw new SystemException(ex.getMessage(), SystemException.REQUEST_EXCEPTION);
                }
            }
        }
    }
}
