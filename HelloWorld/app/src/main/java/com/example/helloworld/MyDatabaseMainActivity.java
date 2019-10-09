package com.example.helloworld;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MyDatabaseMainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_database_main);
        // 这里的version传入高于现在的版本，则onUpgrade方法就会被执行
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        Button createDatebase = findViewById(R.id.create_database);
        createDatebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 第一次点击的时候监测到当前程序并没有BookStore.db数据库，于是会创建数据库并调用MyDatabaseHelper.onCreate方法创建表。
                // 第二次点击的时候此时该数据库已经存在，因此不会再创建一次。
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = findViewById(R.id.CRUD_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 对数据库的数据进行增删改查操作
                // 插入数据 第一个参数表名，第二个用于在未指定添加数据的情况下给某些可为空的列自动复制null，一般不用直接传入null， 第三个将表中的每个列名以及相应的数据传入
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "First Book");
                values.put("author", "One Person");
                values.put("pages", 145);
                values.put("price", 14.25);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 组装第二条数据
                values.put("name", "Second Book");
                values.put("author", "Two Person");
                values.put("pages", 245);
                values.put("price", 24.25);
                db.insert("Book", null, values); // 插入第二条数据
//                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)", new String[] {"Second Book", "Two Person", "245", "24.25"});
                values.clear();

                selectBook();// 查询并打印数据

                // 更新数据
                values.put("price", 4.25);
                // 第三个参数对应SQL语句中的where部分，表示更新所有name=?的行，？占位符， 第四个参数提供一个字符串数组为第三个参数中的每个占位符指定相应的内容
                db.update("Book", values, "name = ?", new String[] { "First Book"});
//                db.execSQL("update Book set price = ? where name = ?", new String[] {"4.25", "First Book"});  // 可以直接用SQL语句

                // 删除数据 后两个参数不指定，默认删除所有行
                db.delete("Book", "pages > ?", new String[] {"200"});
//                db.execSQL("delete from Book where pages > ?", new String[] {"200"});

                selectBook();// 查询并打印数据

                db.delete("Book", null, null);// 删除所有数据
            }
        });
    }
    public void selectBook() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from Book", null);
        // 查询 第二个参数用于指定查询结果展示列，默认查所有列。第三、四用于约束查某几行数据默认查全表。第五个group by。第七个order by。
        // 返回值Cursor对象
        Cursor cursor = db.query("Book", null, null, null, null, null, null); // 查询Book表所有数据
        if (cursor.moveToFirst()) { // 将数据的指针移动到第一行的位置
            do {
                // 遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String pages = cursor.getString(cursor.getColumnIndex("pages"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String toPrint = "book name is "+name+" , author is "+author+" , pages is "+pages+" , prices is "+price+".";
                Toast.makeText(this, toPrint, Toast.LENGTH_SHORT).show();
                Log.d("MyDatabaseMainActivity", toPrint);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
