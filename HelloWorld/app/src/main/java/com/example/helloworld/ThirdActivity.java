package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

////      按钮button_hello的响应事件
//        Button button = (Button) findViewById(R.id.button_th);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////              构建了一个仅用于传递数据的Intent
//                Intent intent = new Intent();
//                intent.putExtra("data_return", "hello helloWorldActivity");
////              setResult方法专门用于向上一个活动返回数据的，第一个参数用于向上一个活动返回处理结果(一般值RESULT_OK或者RESULT_CANCELED)，第二个参数把带有数据的Intent传递回去，调用finish销毁当前活动
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
    }
}
