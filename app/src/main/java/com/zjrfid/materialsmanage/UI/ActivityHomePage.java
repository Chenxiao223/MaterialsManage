package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.WarehouseAuthority;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.RfidOperation;
import com.zjrfid.materialsmanage.tool.MediaUtil;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityHomePage extends Activity {
    public static ActivityHomePage homePage;
    private LinearLayout linear_binding, linear_put_out_storage, linear_put_in_storage, linear_check, linear_label_detection;
    private ImageView head,iv_connection;
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    protected static final int MSG_DISCONNECT = 3;
    protected static final int MSG_CONNECT = 2;
    protected static final int MSG_OPERATION_SUCCESS = 1;
    public static WarehouseAuthority whay;
    private List<WarehouseAuthority.WareReadhouseBean> wareReadhouseBeanList;
    public static String wareReadhouseBeanString;
    MediaUtil mediaUtil=new MediaUtil(this);
    public List<String> list_code=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //
        homePage=this;
        SysApplication.getInstance().addActivity(this);
        connectRadio();//连接RFID模块
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);//注册监听网络变化的广播
        Warehouseauthority();//获取仓库权限

        linear_binding = (LinearLayout) findViewById(R.id.linear_binding);
        linear_put_out_storage = (LinearLayout) findViewById(R.id.linear_put_out_storage);
        linear_put_in_storage = (LinearLayout) findViewById(R.id.linear_put_in_storage);
        linear_check = (LinearLayout) findViewById(R.id.linear_check);
        linear_label_detection = (LinearLayout) findViewById(R.id.linear_label_detection);
        head = (ImageView) findViewById(R.id.head);
        iv_connection= (ImageView) findViewById(R.id.iv_connection);
        //点击盘点管理
        linear_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHomePage.this, ActivityInventoryManage.class));
            }
        });
        //点击头像
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHomePage.this, ActivityMyself.class));
                overridePendingTransition(R.anim.trans, 0);
            }
        });
        //点击绑定
        linear_binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHomePage.this, ActivityBinding.class));
            }
        });
        //点击出库
        linear_put_out_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHomePage.this, ActivityMaterialOutboundOrder.class));
            }
        });
        //点击入库
        linear_put_in_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHomePage.this, ActivityGoodsReceipt.class));
            }
        });
        //点击检测
        linear_label_detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHomePage.this, ActivityTagTest.class));
            }
        });
    }

    //点击设置
    public void setting(View view){
        //跳转到设置页面
        startActivity(new Intent(this,ActivitySetting.class));
    }

    //点击back键
    @Override
    public void onBackPressed() {
        //点击弹出对话框
        new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("是否要退出物资通应用？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HttpNetworkRequest.delete("public/rs/device/login", new BaseHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, String s, Object o) {
                                //断开RFID模块
                                disconnectRadio();
//                                finish();
//                                System.exit(0);
//                                SysApplication.getInstance().exit();
                                ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                                manager.killBackgroundProcesses(getPackageName());
                                SysApplication.getInstance().exit();

                            }

                            @Override
                            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                            }
                        });
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isAvailable()){
//                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            }else {
                mediaUtil.music(R.raw.p);//语音提示
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//注销广播
    }

    //连接RFID模块，返回结果
    public void connectRadio()
    {
        new Thread(){

            public void run() {

                Message closemsg = new Message();

                try {
                    RfidOperation.setAntennaPower(15);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                closemsg.obj = RfidOperation.connectRadio();
                closemsg.what = MSG_CONNECT;
                hMsg.sendMessage(closemsg);
            };

        }.start();

    }
    private Handler hMsg = new StartHander(this);
    private  class StartHander extends Handler {
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

                case MSG_DISCONNECT:
                    int returnValue = (Integer) msg.obj;
                    switch(returnValue){
                        case 0:
//                            tv_rfid_isconneted.setText("rfid已连接");
                            Toast.makeText(ActivityHomePage.this, "rfid已连接", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
//                            tv_rfid_isconneted.setText("失败");
                            Toast.makeText(ActivityHomePage.this, "失败", Toast.LENGTH_SHORT).show();
                            break;

                        case -1:
//                            tv_rfid_isconneted.setText("失败：忙中");
                            Toast.makeText(ActivityHomePage.this, "失败：忙中", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;



                case MSG_CONNECT:
                    int returnValue1 = (Integer) msg.obj;
                    switch(returnValue1){
                        case 0:
//                            tv_rfid_isconneted.setText("rfid已连接");
//                            Toast.makeText(ActivityHomePage.this, "rfid已连接", Toast.LENGTH_SHORT).show();
                            iv_connection.setImageDrawable(getResources().getDrawable(R.drawable.connection2));
                            break;

                        case -1:
//                            tv_rfid_isconneted.setText("失败");
                            Toast.makeText(ActivityHomePage.this, "失败", Toast.LENGTH_SHORT).show();
                            break;

                        case -2:
//                            tv_rfid_isconneted.setText("失败：忙中");
                            Toast.makeText(ActivityHomePage.this, "失败：忙中", Toast.LENGTH_SHORT).show();

                            break;

                        case 2:
//                            tv_rfid_isconneted.setText("失败：设置天线");
                            Toast.makeText(ActivityHomePage.this, "失败：设置天线", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    break;

                case MSG_OPERATION_SUCCESS:
                    String returnValue2 = (String) msg.obj;
//                    tv_show_epc.setText(returnValue2);
                    break;

                default:

                    break;

            }
        }
    };
    //连接RFID模块，返回结果
    public void disconnectRadio()
    {
        new Thread(){

            public void run() {

                Message closemsg = new Message();
                closemsg.obj =RfidOperation.DisconnectRadio();
                closemsg.what = MSG_DISCONNECT;
                hMsg.sendMessage(closemsg);
            };

        }.start();

    }

    private  void Warehouseauthority(){
//        //仓库权限接口
        HttpNetworkRequest.post("goods/rs/hpPersonAndOrg", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson1 = new Gson();
                whay = gson1.fromJson(s, WarehouseAuthority.class);
                wareReadhouseBeanList = whay.getWareReadhouse();
                wareReadhouseBeanString = "";
                for (int j = 0; j < wareReadhouseBeanList.size(); j++) {
//                    list_code.add(wareReadhouseBeanList.get(j).getCwhcode());
                    list_code.add("01");
                    if (j < (wareReadhouseBeanList.size() - 1)) {
                        wareReadhouseBeanString = wareReadhouseBeanString + "'" + wareReadhouseBeanList.get(j).getCwhcode() + "',";
                    }
                    if (j == (wareReadhouseBeanList.size() - 1)) {
                        wareReadhouseBeanString = wareReadhouseBeanString + "'" + wareReadhouseBeanList.get(j).getCwhcode() + "'";
                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                System.out.println("失败了");
            }
        });
    }


}
