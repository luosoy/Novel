package com.luosoy.frame.basecode.dto;

import java.io.Serializable;

public class BaseCodeDefDTO implements Serializable {

    private static final long serialVersionUID = -3098238101731766485L;

    private String codeType;

    /**
     * The description.
     */
    private String description;

    /**
     * The isleaf field.
     */
    private String isleafField;

    /**
     * The name field.
     */
    private String nameField;

    /**
     * The parent field.
     */
    private String parentField;

    /**
     * The table name.
     */
    private String codeSql;

    /**
     * The value field.
     */
    private String valueField;

    private String orderSql;

    private Character yxbz;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsleafField() {
        return isleafField;
    }

    public void setIsleafField(String isleafField) {
        this.isleafField = isleafField;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getParentField() {
        return parentField;
    }

    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    public String getCodeSql() {
        return codeSql;
    }

    public void setCodeSql(String codeSql) {
        this.codeSql = codeSql;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public String getOrderSql() {
        return orderSql;
    }

    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }

    public Character getYxbz() {
        return yxbz;
    }

    public void setYxbz(Character yxbz) {
        this.yxbz = yxbz;
    }

}
