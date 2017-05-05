package com.luosoy.frame.basecode.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luosoy.frame.basecode.StringToBooleanSerializable;
import java.io.Serializable;

public class BaseCodeDTO implements Serializable {

    private static final long serialVersionUID = -9085421654857597337L;

    private String value;

    private String label;

    private String parent;

    @JsonSerialize(using = StringToBooleanSerializable.class)
    private String isLeaf;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public String toString() {
        return "CodeDTO{" + "value=" + value + ", label=" + label + ", parent=" + parent + ", isLeaf=" + isLeaf + '}';
    }

}
