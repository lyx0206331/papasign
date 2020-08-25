package com.papa.signature.model.local;

import com.papa.signature.model.IpConfigBean;
import com.papa.signature.model.IpConfigBean_Table;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * @author PAPA-GuoBa
 * @Desc 用户类的操作
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/4 17:26
 */
public class IpCofigDao implements IDbAction<IpConfigBean> {
    // 将自身实例化对象设置为一个属性，并用static修饰
    private static IpCofigDao instance;

    private IpCofigDao() {

    }

    /**
     * 单例模式 全局只要一个IpCofigDao实列
     *
     * @return
     */
    public static IpCofigDao getInstance() {
        if (instance == null) {
            synchronized (IpCofigDao.class) {
                if (instance == null) {
                    instance = new IpCofigDao();
                }
            }
        }
        return instance;
    }
    /**
     * @param ipId 根据id来查询ip地址
     * @return
     */
    @Override
    public IpConfigBean selObject(int ipId) {
        IpConfigBean user = SQLite.select().from(IpConfigBean.class).where(IpConfigBean_Table.ipId.is(ipId)).querySingle();
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public List<IpConfigBean> selObjects() {
        List<IpConfigBean> users = SQLite.select().from(IpConfigBean.class).queryList();
        if (users != null && users.size() > 0) {
            return users;
        }
        return null;
    }

    /**
     * @param user
     * @return 返回值是1 则是添加成功，要是0则是失败。
     */
    @Override
    public long addObject(IpConfigBean user) {
        long insert = user.insert();
        if (insert > 0) {
            return insert;
        }
        return 0;
    }

    @Override
    public long upData(IpConfigBean user) {
        boolean update = user.update();
        if (update) {
            return 1;
        }
        return 0;
    }

    @Override
    public long delObject(IpConfigBean user) {
        boolean delete = user.delete();
        if (delete) {
            return 1;
        }
        return 0;
    }

    @Override
    public void delTable(Class v) {
        Delete.table(v);
    }
}
