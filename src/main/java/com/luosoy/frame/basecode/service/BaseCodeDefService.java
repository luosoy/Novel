package com.luosoy.frame.basecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.luosoy.frame.basecode.cmp.BaseCodeDefCMP;
import com.luosoy.frame.basecode.dto.BaseCodeDefDTO;
import com.luosoy.frame.basecode.repository.BaseCodeDefRepository;
import com.luosoy.frame.beans.BeanConvertUtil;

@Service
public class BaseCodeDefService {

    /**
     * The base code repository.
     */
    @Autowired
    private BaseCodeDefRepository baseCodeRepository;

    /**
     * 取得所有的数据.
     *
     * @param sc the sc
     * @param pageable the pageable
     * @return the all code def
     */
    public Page<BaseCodeDefDTO> getAllCodeDef(Specification<BaseCodeDefCMP> sc, Pageable pageable) {
        Page<BaseCodeDefCMP> pageList = baseCodeRepository.findAll(sc, pageable);
        List<BaseCodeDefDTO> resultList = BeanConvertUtil.convertList(BaseCodeDefCMP.class, BaseCodeDefDTO.class,
                pageList.getContent());
        return new PageImpl<BaseCodeDefDTO>(resultList, pageable, pageList.getTotalElements());
    }

    /**
     * 取得所有的数据.
     *
     * @return the all code def
     */
    public List<BaseCodeDefDTO> getAllCodeDef() {
        List<BaseCodeDefCMP> defList = (List<BaseCodeDefCMP>) baseCodeRepository.findAll();
        List<BaseCodeDefDTO> result = BeanConvertUtil.convertList(BaseCodeDefCMP.class, BaseCodeDefDTO.class, defList);
        return result;
    }

    /**
     * 根据code类型取得单个BaseCode的定义.
     *
     * @param codeType the code type
     * @return the code def by code type
     */
    @Cacheable(value = "basecode-def", key = "#codeType")
    public BaseCodeDefDTO getCodeDefByCodeType(String codeType) {
        BaseCodeDefCMP cmp = baseCodeRepository.findOne(codeType);
        return BeanConvertUtil.convert(cmp, new BaseCodeDefDTO());
    }

}
