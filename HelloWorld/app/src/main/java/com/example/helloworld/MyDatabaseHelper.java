package com.example.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement, " +
            "author text, " +
            "price real, " +
            "pages integer, " +
            "name text)";

    public static final String CREATE_CATEGORY = "create table Category (" +
            "id integer primary key autoincrement, " +
            "category_name text, " +
            "category_code integer)";

    private Context mcontext;

    /*
    构造函数：
    name：数据库名字；
    factory：允许我们在查询数据的时候返回一个自定义的Cursor，一般都是传入null；
    version：当前数据库的版本号，可用于对数据库进行升级操作。
     */
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);  // 执行建表语句
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mcontext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改表结构或者新增加表
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book"); // 在创建表的时候如果表存在会报错
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
