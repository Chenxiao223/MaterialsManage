package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.zjrfid.materialsmanage.acdbentity.CheckInventoryLossesOutBody;
import com.zjrfid.materialsmanage.acdbentity.CheckInventoryLossesOutHeader;
import com.zjrfid.materialsmanage.acdbentity.InventoryLossesOut;
import com.zjrfid.materialsmanage.adapter.AdapterCheckLossesOut;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/11. 盘亏出库单（查看详情）界面
 */
public class ActivityCheckInventoryLossesOut extends Activity {
    public static ActivityCheckInventoryLossesOut CheckInventoryLossesOut;
    private TextView tv_checkOutInventoryNumber, tv_checkOutWarehouse, tv_checkOutInventoryTime, tv_checkOutPersonCharge,
            tv_checkOutDatabaseClass, tv_checkOutdepartment, tv_checkOutRemarks, tv_maker, tv_makedate, tv_handler, tv_handledate, tv_totalCount, tv_totalAmount, tv_totalNoTaxAmount;
    Intent it;
    private int i = 0;
    private CheckInventoryLossesOutHeader ciloh;
    private CheckInventoryLossesOutBody cilob;
    private InventoryLossesOut ilout;
    public HashMap<String, String> map2;
    public ArrayList<HashMap<String, String>> checkoutlistscq = new ArrayList<>();
    public static AdapterCheckLossesOut madapterCheckLossesOut;
    private ListView lv_create;
    public static String FromOuter_hprGuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklossesout);
        CheckInventoryLossesOut = this;
        FromOuter_hprGuid = getIntent().getStringExtra("hprGuid");
        initView();//初始化视图
    }

    //点击回退
    public void back(View view) {
        finish();
    }

    private void initView() {
        tv_totalCount = (TextView) findViewById(R.id.tv_totalCount);//总数量
        tv_totalAmount = (TextView) findViewById(R.id.tv_totalAmount);//合计金额
        tv_totalNoTaxAmount = (TextView) findViewById(R.id.tv_totalNoTaxAmount);//无税金额

        tv_maker = (TextView) findViewById(R.id.tv_maker);//制单人
        tv_makedate = (TextView) findViewById(R.id.tv_makedate);//制单日期
        tv_handler = (TextView) findViewById(R.id.tv_handler);//审核人
        tv_handledate = (TextView) findViewById(R.id.tv_handledate);//审核日期

        tv_checkOutInventoryNumber = (TextView) findViewById(R.id.tv_checkOutInventoryNumber);//盘点单据号
        tv_checkOutWarehouse = (TextView) findViewById(R.id.tv_checkOutWarehouse);//仓库
        tv_checkOutInventoryTime = (TextView) findViewById(R.id.tv_checkOutInventoryTime);//盘点出库日期
        tv_checkOutPersonCharge = (TextView) findViewById(R.id.tv_checkOutPersonCharge);//盘点负责人
        tv_checkOutDatabaseClass = (TextView) findViewById(R.id.tv_checkOutDatabaseClass);//出库类别
        tv_checkOutdepartment = (TextView) findViewById(R.id.tv_checkOutdepartment);//部门
        tv_checkOutRemarks = (TextView) findViewById(R.id.tv_checkOutRemarks);//备注
        lv_create = (ListView) findViewById(R.id.list_checkOutprofit);
        madapterCheckLossesOut = new AdapterCheckLossesOut(ActivityCheckInventoryLossesOut.this, checkoutlistscq);
        lv_create.setAdapter(madapterCheckLossesOut);

        RequestParams params = new RequestParams();
        //接收从出库传过来的主键
        params.put("hprGuid", FromOuter_hprGuid);

        //盘点出库单查询（详细）表头接口
        HttpNetworkRequest.get("goods/rs/hpCheckoutbound/" + FromOuter_hprGuid, params, new BaseHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                ciloh = gson.fromJson(s, CheckInventoryLossesOutHeader.class);
                tv_checkOutInventoryNumber.setText(ciloh.getCCODE());//盘点单据号
                tv_checkOutWarehouse.setText(ciloh.getCWHNAME());//仓库
                tv_checkOutInventoryTime.setText(ciloh.getDKEEPDATE());//盘点出库日期
                tv_checkOutPersonCharge.setText(ciloh.getCPERSONNAME());//盘点负责人
                tv_checkOutDatabaseClass.setText("盘亏出库");//出库类别
                tv_checkOutdepartment.setText(ciloh.getORGNAME());//部门
                tv_checkOutRemarks.setText(ciloh.getCMEMO());//备注

                tv_maker.setText(ciloh.getCMAKER());//制单人
                tv_makedate.setText(ciloh.getCREATEDT());//制单日期
                tv_handler.setText(ciloh.getCHANDLER());//审核人
                tv_handledate.setText(ciloh.getDVERIDAATE());//审核日期
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityMaterialOutboundOrder.materialOutboundOrder, "表头网络请求异常", Toast.LENGTH_SHORT).show();
            }
        });


        RequestParams mparams = new RequestParams();
        mparams.put("hprGuid", FromOuter_hprGuid);//接收从出库传过来的主键

        //盘点出库单查询（详细）表体接口
        HttpNetworkRequest.get("goods/rs/hpCheckoutboundCh", mparams, new BaseHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson mgson = new Gson();
                cilob = mgson.fromJson(s, CheckInventoryLossesOutBody.class);
                if (cilob.getStatusCode().equals("200")) {//判断接口请求是否成功
                    for (i = 0; i < cilob.getJsonData().getList().size(); i++) {
                        map2 = new HashMap<String, String>();
                        //判断返回来的是否为空
                        if (cilob.getJsonData().getList().get(i).getCINVCODE() == null) {
                            map2.put("content1", "");//物资编码
                        } else {
                            map2.put("content1", cilob.getJsonData().getList().get(i).getCINVCODE());//物资编码
                        }
                        if (cilob.getJsonData().getList().get(i).getCBATCH() == null) {
                            map2.put("content2", "");//批号
                        } else {
                            map2.put("content2", cilob.getJsonData().getList().get(i).getCBATCH());//批号
                        }
                        if (cilob.getJsonData().getList().get(i).getCINVNAME() == null) {
                            map2.put("content3", "");//物资名称
                        } else {
                            map2.put("content3", cilob.getJsonData().getList().get(i).getCINVNAME());//物资名称
                        }
                        if (cilob.getJsonData().getList().get(i).getCPARENTID() == null) {
                            map2.put("content4", "");//货位编码
                        } else {
                            map2.put("content4", cilob.getJsonData().getList().get(i).getCPARENTID());//货位编码
                        }
                        if (cilob.getJsonData().getList().get(i).getCINVSTD() == null) {
                            map2.put("content5", "");//规格型号
                        } else {
                            map2.put("content5", cilob.getJsonData().getList().get(i).getCINVSTD());//规格型号
                        }
                        if (cilob.getJsonData().getList().get(i).getCCOMUNITNAME() == null) {
                            map2.put("content6", "");//单位
                        } else {
                            map2.put("content6", cilob.getJsonData().getList().get(i).getCCOMUNITNAME());//单位
                        }
                        if (cilob.getJsonData().getList().get(i).getFQUANTITY() == null) {
                            map2.put("content7", "");//数量
                        } else {
                            map2.put("content7", cilob.getJsonData().getList().get(i).getFQUANTITY());//数量
                        }
                        if (cilob.getJsonData().getList().get(i).getFTAXPRICE() == null) {
                            map2.put("content8", "");//单价
                        } else {
                            map2.put("content8", cilob.getJsonData().getList().get(i).getFTAXPRICE());//单价
                        }
                        if (cilob.getJsonData().getList().get(i).getTAXAMOUNT() == null) {
                            map2.put("content9", "");//合计金额
                        } else {
                            map2.put("content9", cilob.getJsonData().getList().get(i).getTAXAMOUNT());//合计金额
                        }
                        if (cilob.getJsonData().getList().get(i).getFUNITPRICE() == null) {
                            map2.put("content10", "");//无税单价
                        } else {
                            map2.put("content10", cilob.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
                        }
                        if (cilob.getJsonData().getList().get(i).getFMONDEY() == null) {
                            map2.put("content11", "");//无税金额
                        } else {
                            map2.put("content11", cilob.getJsonData().getList().get(i).getFMONDEY());//无税金额
                        }
                        map2.put("content13", "");//备注
                        map2.put("flag", "false");
                        checkoutlistscq.add(map2);
                        madapterCheckLossesOut.notifyDataSetChanged();
                        madapterCheckLossesOut.hind(1);//隐藏复选框
                        SummaryOfTotalValue(checkoutlistscq);
                    }
                } else {
                    Log.e("error", "出错鸟");
                }


            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityCheckInventoryLossesOut.CheckInventoryLossesOut, "表体网络请求异常", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void SummaryOfTotalValue(ArrayList<HashMap<String, String>> list) {
        double count = 0;
        double totalAmount = 0;
        double totalNoTaxAmount = 0;

        for (int i = 0; i < list.size(); i++) {
            count = count + Double.parseDouble(list.get(i).get("content7"));
            totalAmount = totalAmount + Double.parseDouble(list.get(i).get("content9"));
            totalNoTaxAmount = totalNoTaxAmount + Double.parseDouble(list.get(i).get("content11"));
        }

        tv_totalCount.setText(count + "");
        tv_totalAmount.setText(totalAmount + "");
        tv_totalNoTaxAmount.setText(totalNoTaxAmount + "");
    }

    //点击审核
    public void auditing(View view) {
        //点击弹出对话框
        new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("是否通过审核？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String hprguid = getIntent().getStringExtra("hprGuid");
                        Log.i("ii", hprguid);
                        RequestParams params = new RequestParams();
                        params.put("key", "0");//key值写死，虽然不知道为什么，苏州说的
                        params.put("str", hprguid);
                        if (ciloh.getCHANDLER() == null || ciloh.getCHANDLER().equals("")) {//如果审核人为空，那么说明未审核，可以删除
                            HttpNetworkRequest.post("goods/rs/hpPutstorage/" + hprguid, params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, String s, Object o) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);//审核入库单接口
                                        if (jsonObject.getString("statusCode").equals("200")) {
                                            Toast.makeText(ActivityCheckInventoryLossesOut.this, "审核通过", Toast.LENGTH_SHORT).show();
                                            //跳转到物资出库单
                                            startActivity(new Intent(ActivityCheckInventoryLossesOut.this, ActivityInventoryLossesOut.class));
                                            ActivityInventoryLossesOut.inventoryLossesOut.onRefresh();
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                                    Toast.makeText(ActivityCheckInventoryLossesOut.this, "审核请求，服务器请求失败", Toast.LENGTH_SHORT).show();
                                }

                            });
                        } else {
                            Toast.makeText(ActivityCheckInventoryLossesOut.this, "已经审核过，不需要审核了", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityCheckInventoryLossesOut.this, "放弃审核", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }


    //点击删除
    public void delete(View view) {
        String hprguid = getIntent().getStringExtra("hprGuid");
        Log.i("ii", hprguid);
        RequestParams params = new RequestParams();
        params.put("str", hprguid);
        if (ciloh.getCHANDLER() == null) {//如果审核人为空，那么说明未审核，可以删除
            //盘点出库单删除接口
            HttpNetworkRequest.delete("goods/rs/hpCheckoutbound", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    try {
                        JSONObject mjsonObject = new JSONObject(s);
                        if (mjsonObject.getString("message").equals("删除成功")) {

                            Toast.makeText(ActivityCheckInventoryLossesOut.this, "删除成功", Toast.LENGTH_SHORT).show();
                            //跳转到物资出库单
                            startActivity(new Intent(ActivityCheckInventoryLossesOut.this, ActivityInventoryLossesOut.class));
                            ActivityInventoryLossesOut.inventoryLossesOut.onRefresh();
                            finish();
                        }
                    } catch (Exception e) {
                        String msg = e.getMessage();
                        Toast.makeText(ActivityCheckInventoryLossesOut.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityCheckInventoryLossesOut.this, "服务器请求失败，删除不成功", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(ActivityCheckInventoryLossesOut.this, "已审核，删除不成功", Toast.LENGTH_SHORT).show();
        }

    }

}


