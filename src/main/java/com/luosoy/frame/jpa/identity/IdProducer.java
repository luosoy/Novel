package com.luosoy.frame.jpa.identity;

public interface IdProducer {

    /**
     * Produces an identifier with provided context.
     *
     * @param ctx the ctx
     * @return the sequence
     */
    Sequence produce(Context ctx);

}
