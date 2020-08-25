package com.papa.signature.model.local;

import java.util.List;

/**
 * @author PAPA-GuoBa
 * @Desc 数据库的基本操作 CRUD
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/4 17:18
 */
public interface IDbAction<T> {

    /**
     * 查询单个数据
     *
     * @return
     */
    T selObject(int id);

    /**
     * 查询多个数据
     *
     * @return
     */
    List<T> selObjects();

    /**
     * 添加数据
     *
     * @param t 需要添加的实体类
     * @return
     */
    long addObject(T t);


    /**
     * 更新数据
     *
     * @param t
     */
    long upData(T t);

    /**
     * 删除数据
     *
     * @param t
     * @return
     */
    long delObject(T t);

    /**
     * 删除整张表
     *
     * @param t
     * @return
     */
    void delTable(Class t);
}
