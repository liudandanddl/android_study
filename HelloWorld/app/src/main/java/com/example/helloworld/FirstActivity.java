package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

//    菜单:标题栏右侧多了一个三点的符号，就是菜单按钮
//    菜单显示方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate方法给当前活动创建菜单，第一参数用来指定通过哪一个资源文件来创建菜单，第二个参数用来指定菜单项将添加到哪一个Menu对象当中。
        getMenuInflater().inflate(R.menu.main, menu);
//        返回true表示允许创建的菜单显示出来，返回false创建的菜单将无法显示
        return true;
    }
//  菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "You clicked add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked remove", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView方法来给当前的活动加载一个布局，传入的是一个布局id。项目中添加的任何资源都会在R文件中生成一个相应的资源id。
        setContentView(R.layout.first_layout);

//        Toast
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

//      点击“Finish Activity”button，销毁当前的activity
        Button buttonFinish = (Button) findViewById(R.id.button_finish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Activity类提供了finish方法，调用一下这个方法就可以销毁当前活动，和按back键效果一样
                finish();
            }
        });

//      显示intent使用
//      Intent有多个构造函数的重载，使用的这个传入两个参数，分别是第一个参数Content提供启动活动的上下文；第二个参数Class指定想要启动的目标活动。通过这个构造函数，构建出Intent的“意图”。.
//      Activity类提供了startActivity方法，专门用于启动活动的，接收一个Intent参数。想要回到上个活动的话，按back键就可以销毁当前活动从而回到上一个活动
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "Hello from FirstActivity to HelloWorldActivity";
                Intent intent = new Intent(FirstActivity.this, HelloWorldActivity.class);
//              使用putExtra传递参数去HelloWorldActivity
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

//      隐士Intent
//      使用Intent的另一个构造函数，直接将action的字符串传进去，表明想要启动能够响应com.example.helloworld.ACTION_START这个action的活动。
//      为什么没有指定category，是因为android.intent.category.DEFAULT是一种默认的category，在调用startActivity方法的时候回自动将这个category添加到Intent中。
        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.helloworld1.ACTION_START");
//              每个Intent只能指定一个action，但却能指定多个category
//              自定义一个category，值为com.example.helloworld1.MY_CATEGORY
                intent.addCategory("com.example.helloworld1.MY_CATEGORY");
                startActivity(intent);
            }
        });

//      使用隐士Intent，打开浏览器实现启动其他程序的活动
        Button button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              指定Intent的action是常量Intent.ACTION_VIEW
                Intent intent = new Intent(Intent.ACTION_VIEW);
//              setData方法接收一个URI对象，主要用于指定当前Intent正在操作的数据，这些数据通常都是以字符串的形式传入到Uri.parse方法中解析产生的。
                intent.setData(Uri.parse("http://baidu.com"));
                startActivity(intent);
            }
        });


        Button button_t = (Button) findViewById(R.id.button_5);
        button_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
//              这里使用startActivityForResult启动ThirdActivity，请求码只要是一个唯一的值就可以，这里传入了1.
                startActivityForResult(intent, 1);
            }
        });

        Button button6 = (Button) findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * .由于在一个活动中有可能调用startActivityForResult方法去启动很多不同的活动，每一个活动返回的数据都会调到onActivityResult这个方法，
     * 因此首先要检查requestCode的值来判断数据来源，确定数据是从ThirdActivity返回的之后，再通过resultCode来判断处理结果是否成功。
     *
     * @param requestCode 即我们在启动活动时传入的请求码
     * @param resultCode 即我们在返回数据时传入的处理结果
     * @param data 携带着返回数据的Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    String reData = data.getStringExtra("data_return");
                    Log.d("HelloWorldActivity", reData);
                }
                break;
        }
    }
}

