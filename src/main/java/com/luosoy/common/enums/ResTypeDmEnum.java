/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.common.enums;

import com.luosoy.frame.beans.BaseEnum;

/**
 * <pre>类名: ResTypeDmEnum</pre>
 * <pre>描述: TODO</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-12-22 上午10:12:58 </pre>
 *
 * @author 罗真朋
 * @version 1.0
 */
public enum ResTypeDmEnum implements BaseEnum {
    /**
     * 目录
     */
    ML("01", "目录"),
    /**
     * 模块
     */
    MK("02", "模块");
    
    private String code;
    private String text;

    private ResTypeDmEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

}
