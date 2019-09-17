package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate方法给当前活动创建菜单，第一参数用来指定通过哪一个资源文件来创建菜单，第二个参数用来指定菜单项将添加到哪一个Menu对象当中。
        getMenuInflater().inflate(R.menu.main, menu);
//        返回true表示允许创建的菜单显示出来，返回false创建的菜单将无法显示
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView方法来给当前的活动加载一个布局，传入的是一个布局id。项目中添加的任何资源都会在R文件中生成一个相应的资源id。
        setContentView(R.layout.first_layout);

//        在活动中，可以通过findViewById方法获取到在布局文件中定义的元素，返回的是一个View对象，可以向下转型成Button对象。
        Button button1 = (Button) findViewById(R.id.button_1);
//        setOnClickListener方法为button注册一个监听器，点击button时就会执行监听器中的onClick方法。
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                通过静态方法makeText创建出一个Toast对象，调用show方法将Toast显示出来。
//                Toast makeText(Context toast要求的上下文，活动本身就是一个Context对象, CharSequence 显示的文本内容, int 显示时长)
                Toast.makeText(FirstActivity.this, "You clicked Button1", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
