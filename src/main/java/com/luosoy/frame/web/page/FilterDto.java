/*
 * Project Name: framework-core-ext
 * File Name: Filter.java
 * Class Name: Filter
 *
 * Copyright 2015 Servyou Software Group Co., Ltd.
 *
 * Licensed under the Servyou
 *
 * http://www.servyou.com.cn
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.servyou.framework.extension.page;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FilterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运算符.
     */
    public enum Operator {

        /**
         * 等于.
         */
        EQ,
        /**
         * 不等于.
         */
        NE,
        /**
         * 大于.
         */
        GT,
        /**
         * 小于.
         */
        LT,
        /**
         * 大于等于.
         */
        GE,
        /**
         * 小于等于.
         */
        LE,
        /**
         * 相似 .
         */
        LIKE,
        /**
         * 包含.
         */
        IN,
        /**
         * 为Null.
         */
        IS_NULL,
        /**
         * 不为Null.
         */
        IS_NOT_NULL;

        /**
         * 从String中获取Operator.
         *
         * @param value 值
         * @return String对应的operator
         */
        public static Operator fromString(String value) {
            return Operator.valueOf(value.toLowerCase());
        }
    }

    /**
     * 默认是否忽略大小写 .
     */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /**
     * 属性 .
     */
    private String property;

    /**
     * 运算符 .
     */
    private Operator operator;

    /**
     * 值 .
     */
    private Object value;

    /**
     * 是否忽略大小写 .
     */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 初始化一个新创建的Filter对象 .
     */
    public FilterDto() {
    }

    /**
     * 初始化一个新创建的Filter对象.
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     */
    public FilterDto(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 初始化一个新创建的Filter对象.
     *
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     * @param ignoreCase 忽略大小写
     */
    public FilterDto(String property, Operator operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 返回等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 等于筛选
     */
    public static FilterDto eq(String property, Object value) {
        return new FilterDto(property, Operator.EQ, value);
    }

    /**
     * 返回等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static FilterDto eq(String property, Object value, boolean ignoreCase) {
        return new FilterDto(property, Operator.EQ, value, ignoreCase);
    }

    /**
     * 返回不等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 不等于筛选
     */
    public static FilterDto ne(String property, Object value) {
        return new FilterDto(property, Operator.NE, value);
    }

    /**
     * 返回不等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static FilterDto ne(String property, Object value, boolean ignoreCase) {
        return new FilterDto(property, Operator.NE, value, ignoreCase);
    }

    /**
     * 返回大于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 大于筛选
     */
    public static FilterDto gt(String property, Object value) {
        return new FilterDto(property, Operator.GT, value);
    }

    /**
     * 返回小于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 小于筛选
     */
    public static FilterDto lt(String property, Object value) {
        return new FilterDto(property, Operator.LT, value);
    }

    /**
     * 返回大于等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 大于等于筛选
     */
    public static FilterDto ge(String property, Object value) {
        return new FilterDto(property, Operator.GE, value);
    }

    /**
     * 返回小于等于筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 小于等于筛选
     */
    public static FilterDto le(String property, Object value) {
        return new FilterDto(property, Operator.LE, value);
    }

    /**
     * 返回相似筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 相似筛选
     */
    public static FilterDto like(String property, Object value) {
        return new FilterDto(property, Operator.LIKE, value);
    }

    /**
     * 返回包含筛选.
     *
     * @param property 属性
     * @param value 值
     * @return 包含筛选
     */
    public static FilterDto in(String property, Object value) {
        return new FilterDto(property, Operator.IN, value);
    }

    /**
     * 返回为Null筛选.
     *
     * @param property 属性
     * @return 为Null筛选
     */
    public static FilterDto isNull(String property) {
        return new FilterDto(property, Operator.IS_NULL, null);
    }

    /**
     * 返回不为Null筛选.
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    public static FilterDto isNotNull(String property) {
        return new FilterDto(property, Operator.IS_NOT_NULL, null);
    }

    /**
     * 返回忽略大小写筛选.
     *
     * @return 忽略大小写筛选
     */
    public FilterDto ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    /**
     * 获取属性.
     *
     * @return 属性
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置属性.
     *
     * @param property 属性
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取运算符.
     *
     * @return 运算符
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置运算符.
     *
     * @param operator 运算符
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取值.
     *
     * @return 值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 设置值.
     *
     * @param value 值
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * 获取是否忽略大小写.
     *
     * @return 是否忽略大小写
     */
    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    /**
     * 设置是否忽略大小写.
     *
     * @param ignoreCase 是否忽略大小写
     */
    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    /**
     * (non-Javadoc).
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        FilterDto other = (FilterDto) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator())
                .append(getValue(), other.getValue()).isEquals();
    }

    /**
     * (non-Javadoc).
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }

}
