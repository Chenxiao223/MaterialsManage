package com.zjrfid.materialsmanage.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.tool.MediaUtil;
import com.zjrfid.materialsmanage.tool.SysApplication;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityStart extends AppCompatActivity {
    MediaUtil mediaUtil=new MediaUtil(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SysApplication.getInstance().addActivity(this);
        //判断网络连接，如果没有网络就报语音
        if (!checkNetworkInfo()){
            Toast.makeText(ActivityStart.this, "网络连接失败，请连接网络", Toast.LENGTH_SHORT).show();
            mediaUtil.music(R.raw.p);
        }
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(ActivityStart.this,ActivityLogin.class));
                finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }
    public boolean checkNetworkInfo() {
        ConnectivityManager conMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }
}
