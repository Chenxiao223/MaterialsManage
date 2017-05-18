package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.Batch;
import com.zjrfid.materialsmanage.acdbentity.House;
import com.zjrfid.materialsmanage.acdbentity.House3;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo;
import com.zjrfid.materialsmanage.adapter.HouseAdapter2;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxiao on 2017/5/16.
 * 库存查询
 */
public class ActivityInventoryQuery extends Activity {
    public static ActivityInventoryQuery inventoryQuery;
    public TextView tv_warehouse, tv_amount;
    public String warehouse, cwhcode;
    public List<House3> mlist = new ArrayList<>();
    private ListView listView;
    private HouseAdapter2 adapter;
    private int amount = 0;//总数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_query);
        //
        inventoryQuery = this;
        initView();
    }

    public void initView() {
        tv_amount = (TextView) findViewById(R.id.tv_amount);
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInventoryQuery.this, Activity_TreeView.class);
                intent.putExtra("flag", 8);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.listview);
        adapter = new HouseAdapter2(this, mlist);
        listView.setAdapter(adapter);
    }

    //点击查询
    public void query(View view) {
        try {
            amount = 0;//清零
            if (getRfid().equals("")) {
                Toast.makeText(ActivityInventoryQuery.this, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
            } else {
                mlist.clear();//清空集合
                RequestParams params = new RequestParams();
                params.put("rfid", getRfid());
                //物资档案接口
                HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=&oldcord=&cinvcode=", params, new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, String s, Object o) {
                        Gson mGson = new Gson();
                        MaterialSpecificFilesInfo msfi = mGson.fromJson(s, MaterialSpecificFilesInfo.class);
                        if (msfi.getJsonData().getList().size() != 0) {
                            String wzbm = msfi.getJsonData().getList().get(0).getCINVCODE();//物资编码
                            String wzmc = msfi.getJsonData().getList().get(0).getCINVNAME();//物料名称
                            String wzzj = msfi.getJsonData().getList().get(0).getHPIGUID();//物资主键
                            //批次接口
                            RequestParams params = new RequestParams();
                            params.put("cinvcode", wzbm);
                            params.put("cwhname", warehouse);
                            params.put("cinvname", wzmc);
                            params.put("hpiGuid", wzzj);
                            params.put("fquantity", "1");
                            params.put("cwhcode", cwhcode);
                            System.out.println(warehouse + "," + cwhcode);
                            //批次信息接口
                            HttpNetworkRequest.get("goods/rs/hpoutstorageBatch", params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, String s, Object o) {
                                    Gson gson = new Gson();
                                    Batch mBatch = gson.fromJson(s, Batch.class);
                                    try {
                                        for (int j = 0; j < mBatch.getJsonData().getList().size(); j++) {
                                            //物资数量、物资单价、入库数量、
                                            House3 house = new House3((int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFQUANTITYS()),
                                                    (int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFTAXPRICE()), 0,
                                                    mBatch.getJsonData().getList().get(j).getCINVCODE(), mBatch.getJsonData().getList().get(j).getCINVNAME(),
                                                    mBatch.getJsonData().getList().get(j).getCPOSCODE(), mBatch.getJsonData().getList().get(j).getCBATCH(),
                                                    "false", mBatch.getJsonData().getList().get(j).getCWHNAME());
                                            mlist.add(house);
                                            amount = amount + (int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFQUANTITYS());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    tv_amount.setText("库存总数：" + amount);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                                    Log.e("error", "出错了");
                                }
                            });
                        } else {
                            Toast.makeText(ActivityInventoryQuery.this, "此标签未绑定", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        finish();
    }

    //扫描获取rfid
    public String getRfid() {
        Result result = RfidOperation.readUnGivenTid((short) 3, (short) 3);
        return result.getReadInfo().toString();
    }

    //点击清空
    public void clear(View view){
        tv_warehouse.setText("请选择仓库");
        warehouse=null;
        cwhcode=null;
    }
}
