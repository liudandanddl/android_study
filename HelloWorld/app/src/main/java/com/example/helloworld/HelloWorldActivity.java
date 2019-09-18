package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);

//      getIntent方法获取到用于启动HelloWorldActivity的Intent。调用getStringExtra方法获取指定key的value。
        Intent intent = getIntent();
        String first_data = intent.getStringExtra("extra_data");
        Log.d("HelloWorldActivity", first_data);

    }
}
