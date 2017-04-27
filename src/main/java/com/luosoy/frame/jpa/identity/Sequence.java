/**
 *
 */
package com.luosoy.frame.jpa.identity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sequence implements Serializable {

    /**
     * The sequence.
     */
    private Object sequence;// NOSONAR

    /**
     * Instantiates a new sequence.
     *
     * @param sequence the sequence
     */
    public Sequence(Object sequence) {
        this.sequence = sequence;
    }

    /**
     * Gets the sequence.
     *
     * @return the sequence
     */
    public Object getSequence() {
        return sequence;
    }

    /**
     * Sets the sequence.
     *
     * @param sequence the new sequence
     */
    public void setSequence(Object sequence) {
        this.sequence = sequence;
    }
}
