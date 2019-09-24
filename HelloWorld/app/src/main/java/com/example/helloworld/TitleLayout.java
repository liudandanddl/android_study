package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

// 每当我们在一个布局中引入TitleLayout时，返回button和编辑button的点击事件就已经自动实现好了。
public class TitleLayout extends LinearLayout {

//  重写构造函数
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//      对标题栏布局进行动态加载 R.layout.title要加载的布局文件的ID，第二个参数是给加载好的布局再添加一个父布局(这里指定的父布局是TitleLayout)。
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button titleBack = (Button)findViewById(R.id.title_back);
        Button titleEdit = (Button)findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You clicked edit button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
