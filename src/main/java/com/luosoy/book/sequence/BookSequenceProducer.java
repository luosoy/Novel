package com.luosoy.book.sequence;



import com.luosoy.frame.jpa.identity.AbstractDbSequenceProducer;
import com.luosoy.frame.jpa.identity.Context;

public class BookSequenceProducer extends AbstractDbSequenceProducer{
    @Override
    protected String getSequenceSql() {
        return "SELECT NEXTVAL('book')";
    }

    @Override
    protected Object handleSequence(Object dbSequence, Context ctx) {
        return dbSequence.toString();
    }
}
