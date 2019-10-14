package com.example.helloworld.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload() {
            Log.d("MyService", "startDownload executed!");
        }

        public int getProgess(){
            Log.d("MyService", "getProgess executed!");
            return 0;
        }
    }

    // service中唯一一个抽象方法
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
    public MyService() {
    }

    // 服务第一次创建的时候调用
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("MyService", "onCreate executed!");
    }

    // 服务每次启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed!");
        return super.onStartCommand(intent, flags, startId);
    }

    // 服务销毁的时候调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed!");
    }
}
