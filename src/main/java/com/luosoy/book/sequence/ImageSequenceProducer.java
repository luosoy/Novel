package com.luosoy.book.sequence;



import com.luosoy.frame.jpa.identity.AbstractDbSequenceProducer;
import com.luosoy.frame.jpa.identity.Context;

public class ImageSequenceProducer extends AbstractDbSequenceProducer{
    @Override
    protected String getSequenceSql() {
        return "SELECT NEXTVAL('image')";
    }

    @Override
    protected Object handleSequence(Object dbSequence, Context ctx) {
        return dbSequence.toString();
    }
}
