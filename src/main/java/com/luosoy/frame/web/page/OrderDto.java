/*
 * Project Name: framework-core-ext
 * File Name: Order.java
 * Class Name: Order
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
package com.luosoy.frame.web.page;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Sort.Direction;

public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

   
    /**
     * 默认方向.
     */
    private static final Direction DEFAULT_DIRECTION = Direction.DESC;

    /**
     * 属性 .
     */
    private String sortField;

    /**
     * 方向.
     */
    private Direction sortOrder = DEFAULT_DIRECTION;

    /**
     * 初始化一个新创建的Order对象.
     */
    public OrderDto() {
    }

    /**
     * @param property 属性
     * @param direction 方向
     */
    public OrderDto(String property, Direction direction) {
        this.sortField = property;
        this.sortOrder = direction;
    }

    /**
     * 返回递增排序.
     *
     * @param property 属性
     * @return 递增排序
     */
    public static OrderDto asc(String property) {
        return new OrderDto(property, Direction.ASC);
    }

    /**
     * 返回递减排序.
     *
     * @param property 属性
     * @return 递减排序
     */
    public static OrderDto desc(String property) {
        return new OrderDto(property, Direction.DESC);
    }

    /**
     * 获取属性.
     *
     * @return 属性
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * 设置属性.
     *
     * @param sortField 属性
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * 获取方向.
     *
     * @return 方向
     */
    public Direction getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置方向.
     *
     * @param sortOrder 方向
     */
    public void setSortOrder(Direction sortOrder) {
        this.sortOrder = sortOrder;
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
        OrderDto other = (OrderDto) obj;
        return new EqualsBuilder().append(getSortField(), other.getSortField()).append(getSortOrder(), other.getSortOrder()).isEquals();
    }

    /**
     * (non-Javadoc).
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getSortField()).append(getSortOrder()).toHashCode();
    }

}
