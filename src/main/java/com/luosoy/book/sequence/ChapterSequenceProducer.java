package com.luosoy.book.sequence;



import com.luosoy.frame.jpa.identity.AbstractDbSequenceProducer;
import com.luosoy.frame.jpa.identity.Context;

/**
 * <pre>类名：TestSequenceProducer</pre>
 * <pre>描述：TODO</pre>
 * <pre>版权：税友软件集团股份有限公司</pre>
 * <pre>日期：2016/7/14</pre>
 *
 * @author zhyu
 */
public class ChapterSequenceProducer extends AbstractDbSequenceProducer{
    @Override
    protected String getSequenceSql() {
        return "SELECT NEXTVAL('chapter')";
    }

    @Override
    protected Object handleSequence(Object dbSequence, Context ctx) {
        return dbSequence.toString();
    }
}
