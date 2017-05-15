package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.tool.SysApplication;

import java.util.Random;

public class ActivityTagTest extends Activity {
    private Button btn_test;
    private static TextView text;
    private static String[] str = {">>检测中...", ">>正在写入数据...", ">>写入完毕...", ">>读取数据...", ">>读取完毕...", ">>校验中...", ">>检测完毕..."};
    String content = "";
    private ImageView iv_verdict;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagtest);
        //
        text = (TextView) findViewById(R.id.text);
        iv_verdict= (ImageView) findViewById(R.id.iv_verdict);
        btn_test= (Button) findViewById(R.id.btn_test);
        SysApplication.getInstance().addActivity(this);
    }
    //点击检测
    public void test(View view) {
        iv_verdict.setVisibility(View.GONE);//隐藏图片
        btn_test.setEnabled(false);//让按钮不可点击
        final int[] count = {0};
        content = "";
        new Thread(){
            @Override
            public void run() {
                while (count[0] <str.length){
                    Message m=new Message();
                    m.what=1;
                    m.arg1= count[0];
                    handler.sendMessage(m);
                    count[0]++;
                    try {
                        sleep(1000*(new Random().nextInt(4)+1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(2);
            }
        }.start();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                text.setText(content += str[msg.arg1] + "\n");
            }else if (msg.what==2){
                //让图片显示
                iv_verdict.setVisibility(View.VISIBLE);
                animation= AnimationUtils.loadAnimation(ActivityTagTest.this, R.anim.alls);
                iv_verdict.startAnimation(animation);
                //让按钮可以点击
                btn_test.setEnabled(true);
            }
        }
    };
    //点击回退
    public void back(View view) {
        finish();
    }
}
