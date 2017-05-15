package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.Element;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;
import com.zjrfid.materialsmanage.tool.SysApplication;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ActivitySetting extends AppCompatActivity {

    public static final int MSG_SHOW_Message =1;
    private TextView TagTid;
    private TextView ForeTid;
    private TextView tv_power;
    private Button btn_scan;
    private SeekBar sb_power;
    private TextView SameInfo;

    private int power=15;

    private String foreTid;
    private String nowTid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_setting);

        SysApplication.getInstance().addActivity(this);
        TagTid = (TextView) findViewById(R.id.TagTid);
        ForeTid = (TextView) findViewById(R.id.ForeTid);
        SameInfo = (TextView) findViewById(R.id.SameInfo);
        tv_power = (TextView) findViewById(R.id.tv_power);
        btn_scan = (Button) findViewById(R.id.btn_scan);
        sb_power = (SeekBar) findViewById(R.id.sb_power);
        tv_power.setText(String.valueOf(power));
        sb_power.setProgress(15);
        sb_power.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_power.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv_power.setText(String.valueOf(seekBar.getProgress()));
                btn_scan.setEnabled(false);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_power.setText(String.valueOf(seekBar.getProgress()));
                power = seekBar.getProgress();
                btn_scan.setEnabled(true);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb_power.setEnabled(false);
                ForeTid.setText("");
                TagTid.setText("");
                SameInfo.setText("");
                RfidOperation.setAntennaPower(power);
                RfidOperation.DisconnectRadio();
                RfidOperation.connectRadio();
                Result testresult = RfidOperation.readUnGivenTid((short) 3, (short) 3);



                Message msg = new Message();
                msg.obj = testresult.getReadInfo();
                msg.what = MSG_SHOW_Message;
                hMsg.sendMessage(msg);

            }
        });

    }


    private class StartHander extends Handler {
        WeakReference<Activity> mActivityRef;

        StartHander(Activity activity) {
            mActivityRef = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mActivityRef.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {

                case MSG_SHOW_Message:
                    nowTid = (String) msg.obj;
                    if(foreTid ==null || foreTid =="")
                    {

                        SameInfo.setText("");
                        ForeTid.setText("");
                        TagTid.setText(nowTid);

                    }else{

                        if(foreTid.equals(nowTid))
                        {
                            SameInfo.setText("相同");
                            SameInfo.setTextColor(Color.GRAY);
                        }else{
                            SameInfo.setText("不同");
                            SameInfo.setTextColor(Color.RED);
                        }
                        ForeTid.setText(foreTid);
                        TagTid.setText(nowTid);

                    }
                    foreTid =nowTid;
                    sb_power.setEnabled(true);
                    break;
            }
        }
    }


    private Handler hMsg = new StartHander(this);

    //点击箭头
    public void back(View view){
        RfidOperation.setAntennaPower(power);
        RfidOperation.DisconnectRadio();
        RfidOperation.connectRadio();
        finish();
    }

}
