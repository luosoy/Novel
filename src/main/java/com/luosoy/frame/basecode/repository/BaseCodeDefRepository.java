package com.luosoy.frame.basecode.repository;

import com.luosoy.frame.basecode.cmp.BaseCodeDefCMP;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BaseCodeDefRepository extends CrudRepository<BaseCodeDefCMP, String>, 
        JpaSpecificationExecutor<BaseCodeDefCMP> {

    /**
     * Find by code type.
     *
     * @param codeType the code type
     * @return the base code def cmp
     */
    public BaseCodeDefCMP findByCodeType(String codeType);

}
