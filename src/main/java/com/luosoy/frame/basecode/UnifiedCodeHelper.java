package com.luosoy.frame.basecode;

import com.luosoy.frame.basecode.dto.BaseCodeDTO;
import java.io.OutputStream;

import java.util.List;
import java.util.Map;

/**
 * <p>Project: basecode-core</p>
 * <p>Function: The Interface UnifiedCodeHelper.</p>
 * <p>Description: 代码操作辅助类统一接口</p>
 * <p>Company: 税友软件集团股份有限公司</p>
 * 
 * @author gong
 * @version 2.0
 */
public interface UnifiedCodeHelper {

    /**
     * 取得某个baseCode的列表.
     * 
     * @param codeType
     *            basecode类型
     * @return the code list
     */
    public List<BaseCodeDTO> getCodeList(String codeType);

    /**
     * 根据.
     * 
     * @param codeType
     *            the code type
     * @param value
     *            the value
     * @return the name
     */
    public String getName(String codeType, String value);

    /**
     * 取得指定CodeType的键值对.
     * 
     * @param codeType
     *            the code type
     * @return the code map
     */
    public Map<String, BaseCodeDTO> getCodeMap(String codeType);

    /**
     * 根据指定的过滤条件取得缓存的数据List.
     * 
     * @param codeType
     *            code类型
     * @param paramMap
     *            过滤条件
     * @return the code list
     */
    public List<BaseCodeDTO> getCodeList(String codeType, Map<String, Object> paramMap);

    /**
     * 根据指定的过滤条件取得缓存的数据Map.
     * 
     * @param codeType
     *            code类型
     * @param paramMap
     *            过滤条件
     * @return the code map
     */
    public Map<String, BaseCodeDTO> getCodeMap(String codeType, Map<String, Object> paramMap);

    /**
     * 将json数据写入到输出流.
     * 
     * @param codeType
     *            the code type
     * @param stream
     *            the stream
     * @throws Exception
     *             the exception
     */
    public void converCodeToJsonAndWrite(String codeType, OutputStream stream) throws Exception;// NOSONAR

    /**
     * 将指定的codeType转换成JSON字符串.
     * 
     * @param codeType
     *            the code type
     * @return the string
     * @throws Exception
     *             the exception
     */
    public String converCodeToJsonString(String codeType) throws Exception;// NOSONAR

}
