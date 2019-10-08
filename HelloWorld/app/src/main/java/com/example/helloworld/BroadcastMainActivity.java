package com.example.helloworld;

// 广播

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class BroadcastMainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);
        intentFilter = new IntentFilter();  // 创建一个intent的实例，广播接收器想要监听什么广播，就在这里添加相应的action
        // 当网络状态发生变化时，系统发出的正是一条值为android.net.conn.CONNECTIVITY_CHANGE的广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        // networkChangeReceiver会受到所有值为android.net.conn.CONNECTIVITY_CHANGE的广播
        registerReceiver(networkChangeReceiver, intentFilter);  // 调用registerReceiver方法进行注册。

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver); // 动态注册的广播接收器一定要取消注册
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {
       /* 每当网络状态发生变化时，onReceive方法就会得到执行。
         * @param context The Context in which the receiver is running.
         * @param intent  The Intent being received.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // ConnectivityManager这是一个系统服务类，专门用于管理网络连接的
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){ // 判断当前网络是否是有效网络
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
