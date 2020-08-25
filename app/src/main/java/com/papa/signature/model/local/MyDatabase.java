package com.papa.signature.model.local;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * @author PAPA-GuoBa
 * @Desc  本地数据库的使用  -----》创建数据库
 * @EmailCommunication liuxingwen@163.com
 * @createdate 2019/9/4 16:54
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    //数据库名称
    public static final String NAME = "signdb";
    //数据库版本号
    public static final int VERSION = 1;
}
