package com.example.helloworld.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloworld.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitePalDatabaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal_database_main);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();  // 创建数据库，升级数据库只需要改完之后将对应的版本号+1重新触发该函数就可以
                Log.d("LitePalDatabaseMain", "cussecss");
            }
        });

        Button addData = findViewById(R.id.CRUD_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("OneBK");
                book.setAuthor("OneAu");
                book.setPages(454);
                book.setPrice(37.88);
                book.save();  // 插入一条数据

                // 修改
                // (1)对已存储对象修改
                book.setPrice(40.88);
                // 对象是否已存储是根据model.isSaved方法的结果来判断的，true是已存储，true的情况:(1)调用过model.save去条件数据(2)使用LitePal提供的查询API从数据库中查出来的对象。
                book.save(); // 再次调用save方法，此时LitePal会发现当前的Book对象是已存储的，因此不会再向数据库中添加一条数据，而是直接更新当前的数据
                // (2)对非已存储的对象修改
                Book book1 = new Book();
                book1.setName("OneNewBK");
                book1.setPages(464);
                book1.updateAll("name = ? and pages > ?", "OneBK", "400"); // 指定update语句的where部分

//                // 将所有书的页数都更新为0
//                Book book2 = new Book();
//                book2.setToDefault("pages");  // 将pages列的数据更新成默认值(String默认值是null, int是0，boolean是false)
//                book2.updateAll();

                // 全表查询
                List<Book> books = LitePal.findAll(Book.class);
                for (Book temp: books){
                    Log.d("LitePalDatabaseMain", "book name is "+temp.getName()+" , author is "+temp.getAuthor()+" , pages is "+temp.getPages()+" , prices is "+temp.getPrice()+".");
                }
//                LitePal.select("name", "price")
//                        .where("pages > ?", "400")
//                        .order("price desc")
//                        .limit(3)
//                        .offset(1)
//                        .find(Book.class); // 复杂查询

                // 删除 表Book里价格低于50的
                LitePal.deleteAll(Book.class, "price < ?", "50");
//                LitePal.findBySQL("select * from Book");  // 支持原生SQL

            }
        });
    }
}
