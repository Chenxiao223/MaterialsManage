package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.tool.SysApplication;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ActivityMyself extends Activity {
    private ImageView iv_head;
    private Animation animation;
    private TextView tv_username,tv_jobnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        //
        tv_username= (TextView) findViewById(R.id.tv_username);
        tv_username.setText(ActivityLogin.login.getFullname());
        tv_jobnumber= (TextView) findViewById(R.id.tv_jobnumber);
        tv_jobnumber.setText("工号："+ActivityLogin.login.getJobnumber());
        iv_head= (ImageView) findViewById(R.id.iv_head);
        animation= AnimationUtils.loadAnimation(this, R.anim.alls);
        iv_head.startAnimation(animation);
        SysApplication.getInstance().addActivity(this);
    }
    //点击退出
    public void back(View view){
        startActivity(new Intent(this,ActivityHomePage.class));
        overridePendingTransition(0,R.anim.trans2);
        finish();
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,ActivityHomePage.class));
        overridePendingTransition(0,R.anim.trans2);
        finish();
    }
}
