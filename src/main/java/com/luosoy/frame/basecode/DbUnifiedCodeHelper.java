package com.luosoy.frame.basecode;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.luosoy.frame.basecode.dto.BaseCodeDTO;
import com.luosoy.frame.basecode.dto.BaseCodeDefDTO;
import com.luosoy.frame.basecode.service.BaseCodeDefService;
import com.luosoy.frame.basecode.service.BaseCodeFromDbService;

@Service(value = "dbUnifiedCodeHelper")
public class DbUnifiedCodeHelper implements UnifiedCodeHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbUnifiedCodeHelper.class);

    @Autowired
    private BaseCodeDefService codeDefService;
    @Autowired
    private BaseCodeFromDbService codeService;

    @Override
    public List<BaseCodeDTO> getCodeList(String codeType) {
        List result = null;
        try {
            BaseCodeDefDTO defDto = codeDefService.getCodeDefByCodeType(codeType);
            result = codeService.getCodeList(defDto);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public String getName(String codeType, String value) {
        String result = null;
        try {
            Map<String, BaseCodeDTO> codeMap = getCodeMap(codeType);
            BaseCodeDTO resultDto = codeMap.get(value);
            result = resultDto != null ? resultDto.getLabel() : "";
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public String getValue(String codeType, String name) {
        String result = null;
        try {
            Map<String, BaseCodeDTO> codeMap = getNameMap(codeType);
            BaseCodeDTO resultDto = codeMap.get(name);
            result = resultDto != null ? resultDto.getValue() : "";
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public void converCodeToJsonAndWrite(String codeType, OutputStream stream) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.writeValue(stream, this.getCodeList(codeType));
    }

    @Override
    public String converCodeToJsonString(String codeType) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(this.getCodeList(codeType));
    }

    @Override
    public Map<String, BaseCodeDTO> getCodeMap(String codeType) {
        Map<String, BaseCodeDTO> result = null;
        try {
            BaseCodeDefDTO defDto = codeDefService.getCodeDefByCodeType(codeType);
            result = codeService.getCodeMap(defDto);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public Map<String, BaseCodeDTO> getNameMap(String codeType) {
        Map<String, BaseCodeDTO> result = null;
        try {
            BaseCodeDefDTO defDto = codeDefService.getCodeDefByCodeType(codeType);
            result = codeService.getNameMap(defDto);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public List<BaseCodeDTO> getCodeList(String codeType, Map<String, Object> paramMap) {
        List<BaseCodeDTO> result = null;
        try {
            BaseCodeDefDTO defDto = codeDefService.getCodeDefByCodeType(codeType);
            result = codeService.getCodeListParamMap(defDto, paramMap);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

    @Override
    public Map<String, BaseCodeDTO> getCodeMap(String codeType, Map<String, Object> paramMap) {
        Map<String, BaseCodeDTO> result = null;
        try {
            BaseCodeDefDTO defDto = codeDefService.getCodeDefByCodeType(codeType);
            result = codeService.getCodeMap(defDto, paramMap);
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage(), ex);
            throw new RuntimeException(ex);// NOSONAR
        }
        return result;
    }

}
