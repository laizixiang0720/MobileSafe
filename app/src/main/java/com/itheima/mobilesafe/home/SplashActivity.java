package com.itheima.mobilesafe.home;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.itheima.mobilesafe.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private TextView tv_version; //应用版本号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        init();
    }
    private void init() {
        tv_version = findViewById(R.id.tv_version);
        try {
            //获取程序包信息
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("当前版本：" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("当前版本：");
        }
        //利用Timer让此界面延迟3秒后再跳转,timer中有一个线程,这个线程不断执行task
        Timer timer = new Timer();
        //timertask实现runnable接口,TimerTask类表示一个在指定时间内执行的task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //跳转到首页界面
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task, 3000);//设置这个task在延迟三秒之后自动执行
    }

}



