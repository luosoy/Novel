package com.luosoy.frame.basecode;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <p>Project: basecode-core</p>
 * <p>Function: The Class StringToBooleanSerializable.</p>
 * <p>Description: 将字符串转换成boolean类型的数据</p>
 * <p>Company: 税友软件集团股份有限公司</p>
 * 
 * @author gong
 * @version 2.0
 */
public class StringToBooleanSerializable extends JsonSerializer<String> {

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        gen.writeBoolean("true".equalsIgnoreCase(value));

    }

}
