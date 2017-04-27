package com.luosoy.frame.serial;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/** 
 * <pre>类名: JsonDateSerializer</pre>
 * <pre>描述: 日期序列化类</pre>
 * <pre>版权: 税友软件集团股份有限公司</pre>
 * <pre>日期: 2016-6-20 下午3:46:25</pre>
 * @author：kle
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeString(DateFormatUtils.format(value, "yyyy-MM-dd"));
    }

}
