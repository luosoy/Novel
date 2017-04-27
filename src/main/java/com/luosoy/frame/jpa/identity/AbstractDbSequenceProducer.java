package com.luosoy.frame.jpa.identity;

import com.luosoy.frame.exception.SystemException;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDbSequenceProducer implements IdProducer {

    @Autowired
    @Qualifier(value = "IdSequeceEntityManager")
    private EntityManager em;

    @Override
    public Sequence produce(Context ctx) {
        try {
            if (em != null) {
                Object result = em.createNativeQuery(getSequenceSql()).getSingleResult();
                Object sequence = handleSequence(result, ctx);
                return new Sequence(sequence);
            } else {
                throw new SystemException("no EntityManager Produces with IdSequeceEntityManager Qualifier ,please create a EntityManager", SystemException.PERSIST_EXCEPTION);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("produce sequence is wrong,the sql is " + getSequenceSql(), ex);
        }
    }

    /**
     * @Title: getSequenceSql
     * @Description: 获取序号生成器的SQL语句
     * @return String 返回类型
     */
    protected abstract String getSequenceSql();

    /**
     *
     * @Title: handleSequence
     * @Description: 对序号加工处理
     * @param dbSequence 输入参数
     * @param ctx 输入参数
     * @return Object 返回类型
     */
    protected abstract Object handleSequence(Object dbSequence, Context ctx);

}
