package com.luosoy.frame.web.page;

import cn.com.servyou.framework.extension.page.FilterDto;
import com.luosoy.frame.exception.SystemException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableDto implements Serializable {

    private static final long serialVersionUID = 6087799947613628055L;

    /**
     * 默认页码.
     */
    private static final int DEFAULT_PAGE_INDEX = 0;

    /**
     * 默认每页记录数 .
     */
    private static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 最大每页记录数 .
     */
    private static final int MAX_PAGE_SIZE = 1000;

    /**
     * 页码 .
     */
    private int pageIndex = DEFAULT_PAGE_INDEX;

    /**
     * 每页记录数 .
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 搜索属性.
     */
    private String searchProperty;

    /**
     * 搜索值.
     */
    private String searchValue;

    /**
     * 排序属性 .
     */
    private String sortField;

    /**
     * 排序方向 .
     */
    private Direction sortOrder;

    /**
     * 筛选.
     */
    private List<FilterDto> filters = new ArrayList<FilterDto>();

    /**
     * 排序 .
     */
    private List<OrderDto> orders = new ArrayList<OrderDto>();

    /**
     * 初始化一个新创建的Pageable对象.
     */
    public PageableDto() {
    }

    /**
     * 初始化一个新创建的Pageable对象.
     *
     * @param pageIndex 页码
     * @param pageSize 每页记录数
     */
    public PageableDto(Integer pageIndex, Integer pageSize) {
        if (pageIndex != null && pageIndex >= 1) {
            this.pageIndex = pageIndex;
        }
        if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        }
    }

    /**
     * 获取页码.
     *
     * @return 页码
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置页码.
     *
     * @param pageIndex 页码
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 1 ? DEFAULT_PAGE_INDEX : pageIndex;
    }

    /**
     * 获取每页记录数.
     *
     * @return 每页记录数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数.
     *
     * @param pageSize 每页记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? DEFAULT_PAGE_SIZE : (pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize);
    }

    /**
     * 获取搜索属性.
     *
     * @return 搜索属性
     */
    public String getSearchProperty() {
        return searchProperty;
    }

    /**
     * 设置搜索属性.
     *
     * @param searchProperty 搜索属性
     */
    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }

    /**
     * 获取搜索值.
     *
     * @return 搜索值
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * 设置搜索值.
     *
     * @param searchValue 搜索值
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * 获取排序属性.
     *
     * @return 排序属性
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * 设置排序属性.
     *
     * @param sortField 排序属性
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * 获取排序方向.
     *
     * @return 排序方向
     */
    public Direction getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序方向.
     *
     * @param sortOrder 排序方向
     */
    public void setSortOrder(Direction sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取筛选.
     *
     * @return 筛选
     */
    public List<FilterDto> getFilters() {
        return filters;
    }

    /**
     * 设置筛选.
     *
     * @param filters 筛选
     */
    public void setFilters(List<FilterDto> filters) {
        this.filters = filters;
    }

    /**
     * 获取排序.
     *
     * @return 排序
     */
    public List<OrderDto> getOrders() {
        return orders;
    }

    /**
     * 设置排序.
     *
     * @param orders 排序
     */
    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
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
        PageableDto other = (PageableDto) obj;
        return new EqualsBuilder().append(getPageIndex(), other.getPageIndex()).append(getPageSize(), other.getPageSize())
                .append(getSearchProperty(), other.getSearchProperty()).append(getSearchValue(), other.getSearchValue())
                .append(getSortField(), other.getSortField()).append(getSortOrder(), other.getSortOrder())
                .append(getFilters(), other.getFilters()).append(getOrders(), other.getOrders()).isEquals();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.pageIndex;
        hash = 89 * hash + this.pageSize;
        hash = 89 * hash + (this.searchProperty != null ? this.searchProperty.hashCode() : 0);
        hash = 89 * hash + (this.searchValue != null ? this.searchValue.hashCode() : 0);
        hash = 89 * hash + (this.sortField != null ? this.sortField.hashCode() : 0);
        hash = 89 * hash + (this.sortOrder != null ? this.sortOrder.hashCode() : 0);
        hash = 89 * hash + (this.filters != null ? this.filters.hashCode() : 0);
        hash = 89 * hash + (this.orders != null ? this.orders.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param <T>
     * @Title: 取得指定对象的DTO
     * @Description: 取得指定对象的DTO
     * @param clazz 指定类型
     * @return
     */
    public <T> T getDTO(Class<T> clazz) {
        T result = BeanUtils.instantiate(clazz);
        if (this.filters != null && !this.filters.isEmpty()) {
            for (FilterDto dto : this.filters) {
                if (BeanUtils.getPropertyDescriptor(clazz, dto.getProperty()) != null) {
                    try {
                        org.apache.commons.beanutils.BeanUtils.setProperty(result, dto.getProperty(), dto.getValue());
                    } catch (IllegalAccessException e) {
                        throw new SystemException(e.getMessage(), e, SystemException.TYPE_MISMATCH);
                    } catch (InvocationTargetException e) {
                        throw new SystemException(e.getMessage(), e, SystemException.TYPE_MISMATCH);
                    }
                }
            }
        }
        return result;
    }

    public Pageable buildPageable() {
        if (StringUtils.isNotEmpty(this.sortField) && this.sortOrder != null) {
            return new PageRequest(this.pageIndex, this.pageSize, new Sort(this.sortOrder, this.sortField));
        } else if (CollectionUtils.isNotEmpty(orders)) {
            List<Sort.Order> lorder = new ArrayList<Sort.Order>();
            for (OrderDto order : orders) {
                lorder.add(new Sort.Order(order.getSortOrder(), order.getSortField()));
            }
            return new PageRequest(this.pageIndex, this.pageSize, new Sort(lorder));
        }
        return new PageRequest(this.pageIndex, this.pageSize);
    }

}
