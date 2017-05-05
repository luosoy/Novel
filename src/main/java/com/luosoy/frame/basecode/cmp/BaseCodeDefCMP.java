package com.luosoy.frame.basecode.cmp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XT_BASECODE")
public class BaseCodeDefCMP implements Serializable {

    private static final long serialVersionUID = -4234186208472124512L;

    /**
     * The code type.
     */
    @Id
    @Column(name = "CODE_TYPE", nullable = false, length = 80)
    private String codeType;

    /**
     * The description.
     */
    @Column(length = 500)
    private String description;

    /**
     * The isleaf field.
     */
    @Column(name = "ISLEAF_FIELD", length = 80)
    private String isleafField;

    /**
     * The name field.
     */
    @Column(name = "NAME_FIELD", nullable = false, length = 80)
    private String nameField;

    /**
     * The parent field.
     */
    @Column(name = "PARENT_FIELD", length = 80)
    private String parentField;

    /**
     * The table name.
     */
    @Column(name = "CODE_SQL", nullable = false, length = 200)
    private String codeSql;

    /**
     * The value field.
     */
    @Column(name = "VALUE_FIELD", nullable = false, length = 80)
    private String valueField;

    @Column(name = "ORDER_SQL", length = 100)
    private String orderSql;

    @Column(nullable = false)
    private Character yxbz;

    /**
     * Instantiates a new base code def cmp.
     */
    public BaseCodeDefCMP() {
    }

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

    @Override
    public String toString() {
        return "BaseCodeDefCMP{" + "codeType=" + codeType + ", description=" + description + ", isleafField=" + isleafField + ", nameField=" + nameField + ", parentField=" + parentField + ", codeSql=" + codeSql + ", valueField=" + valueField + ", orderSql=" + orderSql + ", yxbz=" + yxbz + '}';
    }

}
