package com.example.helloworld.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

// 异步的会自动停止的服务
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");  // 调用父类的有参构造函数
    }

    // 这个方法在子线程中运行的
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyIntentService", "Thread id is"+Thread.currentThread().getId());

    }

    // 根据IntentService特性，这个服务在结束后应该是会自动停止的
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}
