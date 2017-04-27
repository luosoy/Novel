/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.frame.jpa.utils;

import com.luosoy.frame.annotation.AnnotationUtils;
import com.luosoy.frame.exception.SystemException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;

/**
 * @author 罗真朋
 * @version 1.0
 */
public class EntityBeanUtil {

    /**
     * The <code>int</code> value representing the <code>static</code> modifier.
     */
    public static final int STATIC = 0x00000008;

    /**
     * 用于合并并保存数据
     *
     * @param <T>
     * @param repository 保存repository
     * @param oldEntity 从数据返回的实体类
     * @param newEntity 新的实体类
     * @return 返回保存后的对象
     */
    public static <T> T mergeSave(CrudRepository repository, T oldEntity, T newEntity) {
        return mergeSave(repository, oldEntity, newEntity, false);
    }

    /**
     * 用于合并并保存数据
     *
     * @param <T>
     * @param repository 保存repository
     * @param oldEntity 从数据返回的实体类
     * @param newEntity 新的实体类
     * @param isNotCopyNull 是否拷贝空对象
     * @return 返回保存后的对象
     */
    public static <T> T mergeSave(CrudRepository repository, T oldEntity, T newEntity, boolean isNotCopyNull) {
        validEntity(oldEntity);
        validEntity(newEntity);
        mergeEntity(oldEntity, newEntity, isNotCopyNull);
        repository.save(oldEntity);
        return oldEntity;
    }

    /**
     * 用于合并并保存数据
     *
     * @param <T>
     * @param repository 保存repository
     * @param oldEntitys 从数据返回的实体类
     * @param newEntitys 新的实体类
     * @return
     */
    public static <T> List<T> mergeSave(CrudRepository repository, List<T> oldEntitys, List<T> newEntitys) {
        return mergeSave(repository, oldEntitys, newEntitys, false);
    }

    /**
     * 用于合并并保存数据
     *
     * @param <T>
     * @param repository 保存repository
     * @param oldEntitys 从数据返回的实体类
     * @param newEntitys 新的实体类
     * @param isNotCopyNull 是否拷贝空对象
     * @return 返回保存后的对象
     */
    public static <T> List<T> mergeSave(CrudRepository repository, List<T> oldEntitys, List<T> newEntitys, boolean isNotCopyNull) {
        if (CollectionUtils.isEmpty(oldEntitys) && CollectionUtils.isNotEmpty(newEntitys)) {
            repository.save(newEntitys);
            return newEntitys;
        } else if (CollectionUtils.isNotEmpty(oldEntitys) && CollectionUtils.isEmpty(newEntitys)) {
            repository.delete(newEntitys);
            return oldEntitys;
        } else if (CollectionUtils.isNotEmpty(oldEntitys) && CollectionUtils.isNotEmpty(newEntitys)) {
            validEntity(oldEntitys);
            validEntity(newEntitys);
            List<T> persistEntitys = new ArrayList<T>();
            List<T> insertEntitys = getInsertEntitys(oldEntitys, newEntitys);
            List<T> deleteEntitys = getDeleteEntitys(oldEntitys, newEntitys);
            List<T> updateEntitys = getUpateEntitys(oldEntitys, newEntitys, isNotCopyNull);
            if (CollectionUtils.isNotEmpty(deleteEntitys)) {
                repository.delete(deleteEntitys);
            }
            if (CollectionUtils.isNotEmpty(insertEntitys)) {
                repository.save(insertEntitys);
                persistEntitys.addAll(insertEntitys);
            }
            if (CollectionUtils.isNotEmpty(updateEntitys)) {
                repository.save(updateEntitys);
                persistEntitys.addAll(updateEntitys);
            }
            return persistEntitys;
        } else {
            throw new SystemException("there is no Entity to persist", SystemException.REQUEST_EXCEPTION);
        }
    }

    private static <T> void mergeEntity(T oldEntity, T newEntity, boolean isNotCopyNull) {
        Class<?> newClass = newEntity.getClass();
        List<Field> Idfields = getIdFields(newClass);
        Field[] fields = newClass.getDeclaredFields();
        for (Field field : fields) {
            if (isIdField(field, Idfields)) {
                continue;
            }

            try {
                field.setAccessible(true);
                if (!isStatic(field.getModifiers())) {
                    Object obj = field.get(newEntity);
                    if (isNotCopyNull) {
                        if (obj != null) {
                            if (obj instanceof Number && ((Number) obj).toString().equals("0")) {
                                continue;
                            }
                            field.set(oldEntity, obj);
                        }
                    } else {
                        field.set(oldEntity, obj);
                    }
                }
            } catch (IllegalArgumentException ex) {
                throw new SystemException("mergerEntity can't set field", ex, SystemException.REQUEST_EXCEPTION);
            } catch (IllegalAccessException ex) {
                throw new SystemException("mergerEntity can't set field", ex, SystemException.REQUEST_EXCEPTION);
            }
        }
    }

    private static <T> void validEntity(List<T> entity) {
        for (T t : entity) {
            validEntity(t);
        }
    }

    private static <T> void validEntity(T entity) {
        Assert.notNull(entity);
        if (!entity.getClass().isAnnotationPresent(Entity.class)) {
            throw new SystemException("the class is not Entity class", SystemException.REQUEST_EXCEPTION);
        }
    }

    private static boolean isIdField(Field field, List<Field> Idfields) {
        for (Field Idfield : Idfields) {
            if (field.equals(Idfield)) {
                return true;
            }
        }
        return false;
    }

    private static <T> List<T> getInsertEntitys(List<T> oldEntitys, List<T> newEntitys) {
        List<T> insertEntitys = new ArrayList<T>();
        for (T newEntity : newEntitys) {
            List<Field> idFields = getIdFields(newEntity.getClass());
            boolean isinsert = false;
            for (Field field : idFields) {
                Object newval = null;
                field.setAccessible(true);
                try {
                    newval = field.get(newEntity);
                } catch (IllegalArgumentException ex) {
                    throw new SystemException("getInsertEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                } catch (IllegalAccessException ex) {
                    throw new SystemException("getInsertEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                }
                if (newval != null) {
                    if (newval instanceof Number && ((Number) newval).toString().equals("0")) {
                        isinsert = true;
                        break;
                    }
                } else {
                    isinsert = true;
                    break;
                }
            }
            if (!isinsert) {
                boolean isEq = true;
                for (T oldEntity : oldEntitys) {
                    boolean idFieldEq = true;
                    for (Field field : idFields) {
                        try {
                            field.setAccessible(true);
                            if (!field.get(oldEntity).equals(field.get(newEntity))) {
                                idFieldEq = false;
                                break;
                            }
                        } catch (IllegalArgumentException ex) {
                            throw new SystemException("getInsertEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                        } catch (IllegalAccessException ex) {
                            throw new SystemException("getInsertEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                        }
                    }
                    if (idFieldEq) {
                        isEq = false;
                        break;
                    }
                }
                if (isEq) {
                    isinsert = true;
                }
            }
            if (isinsert) {
                insertEntitys.add(newEntity);
            }

        }
        return insertEntitys;
    }

    private static <T> List<T> getDeleteEntitys(List<T> oldEntitys, List<T> newEntitys) {
        List<T> deleteEntitys = new ArrayList<T>();
        for (T oldEntity : oldEntitys) {
            List<Field> idFields = getIdFields(oldEntity.getClass());
            boolean isdelete = true;
            for (T newEntity : newEntitys) {
                boolean idFieldEq = true;
                for (Field field : idFields) {
                    try {
                        field.setAccessible(true);
                        if (!field.get(oldEntity).equals(field.get(newEntity))) {
                            idFieldEq = false;
                            break;
                        }
                    } catch (IllegalArgumentException ex) {
                        throw new SystemException("getDeleteEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                    } catch (IllegalAccessException ex) {
                        throw new SystemException("getDeleteEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                    }
                }
                if (idFieldEq) {
                    isdelete = false;
                    break;
                }
            }
            if (isdelete) {
                deleteEntitys.add(oldEntity);
            }
        }
        return deleteEntitys;
    }

    private static <T> List<T> getUpateEntitys(List<T> oldEntitys, List<T> newEntitys, boolean isNotCopyNull) {
        List<T> updateEntitys = new ArrayList<T>();
        for (T oldEntity : oldEntitys) {
            List<Field> idFields = getIdFields(oldEntity.getClass());
            for (T newEntity : newEntitys) {
                boolean idFieldEq = true;
                for (Field field : idFields) {
                    try {
                        field.setAccessible(true);
                        if (!field.get(oldEntity).equals(field.get(newEntity))) {
                            idFieldEq = false;
                            break;
                        }
                    } catch (IllegalArgumentException ex) {
                        throw new SystemException("getUpateEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                    } catch (IllegalAccessException ex) {
                        throw new SystemException("getUpateEntitys can't get field", ex, SystemException.REQUEST_EXCEPTION);
                    }
                }
                if (idFieldEq) {
                    mergeEntity(oldEntity, newEntity, isNotCopyNull);
                    updateEntitys.add(oldEntity);
                    break;
                }
            }

        }
        return updateEntitys;
    }

    private static List<Field> getIdFields(Class<?> cls) {
        List<Field> Idfields = new ArrayList<Field>();
        Idfields.addAll(AnnotationUtils.getAnnotationFieldsInClass(EmbeddedId.class, cls));
        Idfields.addAll(AnnotationUtils.getAnnotationFieldsInClass(Id.class, cls));
        return Idfields;
    }

    private static boolean isStatic(int mod) {
        return (mod & STATIC) != 0;
    }

}
