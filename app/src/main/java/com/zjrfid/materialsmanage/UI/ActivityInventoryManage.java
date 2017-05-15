package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.tool.SysApplication;

/**
 * Created by chenxiao on 2017/4/14.
 * 盘点管理
 */
public class ActivityInventoryManage extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_manage);
        //
        SysApplication.getInstance().addActivity(this);
    }

    //点击盘点单
    public void ppd(View view){
        startActivity(new Intent(this,ActivityInventoryList.class));
    }

    //点击盘亏出库单
    public void pkckd(View view){
        startActivity(new Intent(this,ActivityInventoryLossesOut.class));
    }

    //点击盘盈入库单
    public void pyrkd(View view){
        startActivity(new Intent(this,ActivityInventoryProFitInt.class));
    }

    //点击回退键
    public void back(View view){
        finish();
    }
}
