package com.luosoy.frame.basecode.service;

import com.luosoy.frame.basecode.dto.BaseCodeDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.luosoy.frame.basecode.dto.BaseCodeDefDTO;
import com.luosoy.frame.exception.SystemException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@Service
public class BaseCodeFromDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCodeFromDbService.class);

    @Autowired
    @Qualifier(value = "basecodeEntityManagerFactory")
    private EntityManagerFactory emf;

    /**
     * 取得Map形式的Code key:codeValue, value BaseCodeDTO.
     *
     * @param codeModel the code model
     * @return the code map
     */
    @Cacheable(value = "basecode-common", key = "#codeModel.getCodeType()+\"Map\"")
    public Map<String, BaseCodeDTO> getCodeMap(BaseCodeDefDTO codeModel) {
        Map<String, BaseCodeDTO> resultMap = new HashMap<String, BaseCodeDTO>();
        List<BaseCodeDTO> codeList = this.getCodeListParamMap(codeModel, null);
        for (BaseCodeDTO code : codeList) {
            resultMap.put(code.getValue(), code);
        }
        return resultMap;
    }

    @Cacheable(value = "basecode-common", key = "#codeModel.getCodeType()+#param+\"Map\"")
    public Map<String, BaseCodeDTO> getCodeMap(BaseCodeDefDTO codeModel, Map<String, Object> param) {
        Map<String, BaseCodeDTO> resultMap = new HashMap<String, BaseCodeDTO>();
        List<BaseCodeDTO> codeList = this.getCodeListParamMap(codeModel, param);
        for (BaseCodeDTO code : codeList) {
            resultMap.put(code.getValue(), code);
        }
        return resultMap;
    }

    /**
     * 根据返回code列表.
     *
     * @param codeModel 代码模型定义
     * @return the code list param map
     */
    @Cacheable(value = "basecode-common", key = "#codeModel.getCodeType()+\"List\"")
    public List<BaseCodeDTO> getCodeList(BaseCodeDefDTO codeModel) {
        List<BaseCodeDTO> codeList = this.getCodeListParamMap(codeModel, null);
        return codeList;
    }

    /**
     * 根据参数MAP中指定的查询条件返回code列表.
     *
     * @param codeModel 代码模型定义
     * @param param 指定的查询条件
     * @return the code list param map
     */
    public List<BaseCodeDTO> getCodeListParamMap(BaseCodeDefDTO codeModel, Map<String, Object> param) {
        List<BaseCodeDTO> result = null;
        try {
            String baseSql = this.buildBaseSql(codeModel);
            if (param == null) {
                param = new HashMap<String, Object>();// NOSONAR
            }
            String whereCause = this.buildWhereCauseFromMap(param);
            StringBuilder sqlbuffer = new StringBuilder(baseSql);
            sqlbuffer.append(whereCause).append(" ")
                    .append(StringUtils.isNotEmpty(codeModel.getOrderSql()) ? codeModel.getOrderSql() : "");
            if (emf == null) {
                throw new SystemException("no EntityManagerFactory Produces with basecodeEntityManagerFactory Qualifier ,please create a EntityManagerFactory", SystemException.PERSIST_EXCEPTION);
            }
            Query query = emf.createEntityManager().createNativeQuery(sqlbuffer.toString(), BaseCodeDTO.class);
            for (Entry<String, Object> entry : param.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            result = query.getResultList();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;

    }

    /**
     * 生成基本的原生SQL.
     *
     * @param codeModel the code model
     * @param distinckSign the distinck sign
     * @return the string
     */
    private String buildBaseSql(BaseCodeDefDTO codeModel) {
        StringBuilder sqlBuffer = new StringBuilder();
        if (codeModel.getValueField() == null) {
            throw new RuntimeException("代码模型未设置值字段CodeField");// NOSONAR
        }
        if (codeModel.getNameField() == null) {
            throw new RuntimeException("代码模型未设置名称字段NameField");// NOSONAR
        }
        if (codeModel.getCodeSql() == null) {
            throw new RuntimeException("代码模型未设置CodeSql");// NOSONAR
        }

        sqlBuffer.append(" SELECT ");
        sqlBuffer.append(codeModel.getValueField()).append(" as value ,");
        sqlBuffer.append(codeModel.getNameField()).append(" as label ,");
        sqlBuffer.append(codeModel.getParentField() != null ? codeModel.getParentField() : "''").append(" as parent, ");
        sqlBuffer.append(codeModel.getIsleafField() != null ? codeModel.getIsleafField() : "'false'").append(" as isleaf");
        sqlBuffer.append(" from( ").append(codeModel.getCodeSql()).append(" ) bs");
        return sqlBuffer.toString();
    }

    private String buildWhereCauseFromMap(Map<String, Object> param) {
        StringBuilder sqlBuffer = new StringBuilder(" WHERE 1=1 ");
        if (param != null && param.size() > 0) {
            for (Entry<String, Object> entry : param.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    sqlBuffer.append(" AND ");
                    sqlBuffer.append("bs.").append(entry.getKey()).append(" = :" + entry.getKey());
                }
            }
        }
        return sqlBuffer.toString();

    }

}
