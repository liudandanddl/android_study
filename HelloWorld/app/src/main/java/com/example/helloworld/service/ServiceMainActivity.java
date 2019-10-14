package com.example.helloworld.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.example.helloworld.R;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        // 服务与活动成果绑定的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service; // 通过向下转型得到具体的Service的Binder实例
            downloadBinder.startDownload();
            downloadBinder.getProgess();
        }

        // 服务与活动接触绑定的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        Button bindService = findViewById(R.id.bind_service);
        Button unbindService = findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        Button startIntentService = findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); // 启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务 在MyService的任何一个位置调用stopSelf()方法就能让这个服务停止
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务 BIND_AUTO_CREATE表示在活动和服务进行绑定后自动创建服务，这会使得MyService中的onCreate方法得到执行，但onStartCommand不会执行
                break;
            case R.id.unbind_service:
                Intent unbindIntent = new Intent(this, MyService.class);
                unbindService(connection); // 解绑服务和活动之间的绑定，会调用MyService的onDestroy方法
                break;
            case R.id.start_intent_service:
                Log.d("MainActivity", "Thread id is "+Thread.currentThread().getId()); // 打印主线程ID
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }

    }
}
