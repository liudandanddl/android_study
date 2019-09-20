package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UIActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        Button button1 = (Button) findViewById(R.id.button_1);
        editText = (EditText) findViewById(R.id.edit_text1);
        imageView = (ImageView) findViewById(R.id.image_view1);
        progressBar1 = (ProgressBar) findViewById(R.id.p_bar1);
        progressBar2 = (ProgressBar) findViewById(R.id.p_bar2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString();
                Log.d("testLDD", inputText);
//              用toast讲文本框输入的内容展示出来
                Toast.makeText(UIActivity.this, inputText, Toast.LENGTH_SHORT).show();

//              通过点击使用方法setImageResource更换图片
                imageView.setImageResource(R.drawable.img_2);

//              通过按钮点击控制进度条是否展示
                if(progressBar1.getVisibility() == View.GONE) {
                progressBar1.setVisibility(View.VISIBLE);
            }else {
                    progressBar1.setVisibility(View.GONE);
                }

//          通过点击控制进度条大小
            int progress = progressBar2.getProgress();
            progress = progress + 10;
            progressBar2.setProgress(progress);

//          对话框
            AlertDialog.Builder dialog = new AlertDialog.Builder(UIActivity.this);
            dialog.setTitle("This is a dialog");
            dialog.setMessage("Message ....");
            dialog.setCancelable(false);  // 可否取消，传入false表示不能通过Back键取消掉
//           确定button点击事件
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
//          取消button点击事件
            dialog.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();  // 显示对话框







            }

        });
    }
}
