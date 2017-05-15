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
import com.zjrfid.materialsmanage.acdbentity.CheckInventoryProFitIntBody;
import com.zjrfid.materialsmanage.acdbentity.CheckInventoryProFitIntHeader;
import com.zjrfid.materialsmanage.acdbentity.InventoryProFitInt;
import com.zjrfid.materialsmanage.adapter.AdapterCheckProFitInt;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/11.  盘盈入库单（查看详情）界面
 */
public class ActivityCheckInventoryProFitInt extends Activity {
    public static ActivityCheckInventoryProFitInt CheckInventoryProFitInt;
    private TextView tv_checkIntInventoryNumber, tv_checkIntWarehouse, tv_checkIntInventoryTime, tv_checkIntPersonCharge,
            tv_checkIntDatabaseClass, tv_checkIntdepartment, tv_checkIntRemarks, tv_maker, tv_makedate, tv_handler, tv_handledate, tv_totalCount, tv_totalAmount, tv_totalNoTaxAmount;
    public static String FormOuter_hprGuid ="";
    private int i = 0;
    private CheckInventoryProFitIntHeader cipfih;
    private CheckInventoryProFitIntBody cipfib;
    private InventoryProFitInt ipfin;
    public HashMap<String, String> map2;
    public ArrayList<HashMap<String, String>> checkinlistscq = new ArrayList<>();
    public static AdapterCheckProFitInt madapterCheckProFitInt;
    private ListView lv_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkprofitint);
        CheckInventoryProFitInt = this;
        FormOuter_hprGuid = getIntent().getStringExtra("hprGuid");
        initView();//初始化视图
    }

    private void initView() {
        tv_totalCount = (TextView) findViewById(R.id.tv_totalCount);//总数量
        tv_totalAmount = (TextView) findViewById(R.id.tv_totalAmount);//合计金额
        tv_totalNoTaxAmount = (TextView) findViewById(R.id.tv_totalNoTaxAmount);//无税金额
        tv_maker = (TextView) findViewById(R.id.tv_maker);//制单人
        tv_makedate = (TextView) findViewById(R.id.tv_makedate);//制单日期
        tv_handler = (TextView) findViewById(R.id.tv_handler);//审核人
        tv_handledate = (TextView) findViewById(R.id.tv_handledate);//审核日期
        tv_checkIntInventoryNumber = (TextView) findViewById(R.id.tv_checkIntInventoryNumber);//盘点单据号
        tv_checkIntWarehouse = (TextView) findViewById(R.id.tv_checkIntWarehouse);//仓库
        tv_checkIntInventoryTime = (TextView) findViewById(R.id.tv_checkIntInventoryTime);//盘点出库日期
        tv_checkIntPersonCharge = (TextView) findViewById(R.id.tv_checkIntPersonCharge);//盘点负责人
        tv_checkIntDatabaseClass = (TextView) findViewById(R.id.tv_checkIntDatabaseClass);//出库类别
        tv_checkIntdepartment = (TextView) findViewById(R.id.tv_checkIntdepartment);//部门
        tv_checkIntRemarks = (TextView) findViewById(R.id.tv_checkIntRemarks);//备注
        lv_create = (ListView) findViewById(R.id.list_checkIntprofit);


        madapterCheckProFitInt = new AdapterCheckProFitInt(ActivityCheckInventoryProFitInt.this, checkinlistscq);
        lv_create.setAdapter(madapterCheckProFitInt);

        RequestParams params = new RequestParams();
        //接收从盘点入库单列表传过来的主键
        params.put("hprGuid", FormOuter_hprGuid);

        //盘点入库单查询（详细）表头接口
        HttpNetworkRequest.get("goods/rs/hpCheckstorage/" + FormOuter_hprGuid, params, new BaseHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                cipfih = gson.fromJson(s, CheckInventoryProFitIntHeader.class);

                tv_checkIntInventoryNumber.setText(cipfih.getCCODE());//盘点单据号
                tv_checkIntWarehouse.setText(cipfih.getCWHNAME());//仓库
                tv_checkIntInventoryTime.setText(cipfih.getDKEEPDATE());//盘点出库日期
                tv_checkIntPersonCharge.setText(cipfih.getCPERSONNAME());//盘点负责人
                tv_checkIntDatabaseClass.setText("盘盈入库");//入库类别
                tv_checkIntdepartment.setText(cipfih.getORGNAME());//部门
                tv_checkIntRemarks.setText(cipfih.getCMEMO());//备注
                tv_maker.setText(cipfih.getCMAKER());//制单人
                tv_makedate.setText(cipfih.getCREATEDT());
                tv_handler.setText(cipfih.getCHANDLER());
                tv_handledate.setText(cipfih.getDVERIDAATE());

            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityMaterialOutboundOrder.materialOutboundOrder, "盘点入库单表头服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        RequestParams mparams = new RequestParams();
        mparams.put("hprGuid", FormOuter_hprGuid);//接收从出库传过来的主键

        //盘点出库单查询（详细）表体接口
        HttpNetworkRequest.get("goods/rs/hpCheckstorageCh", mparams, new BaseHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, String s, Object o) {
                        Gson mgson = new Gson();
                        cipfib = mgson.fromJson(s, CheckInventoryProFitIntBody.class);
                        try {
                            if (cipfib.getStatusCode().equals("200")) {//判断接口请求是否成功
                                for (i = 0; i < cipfib.getJsonData().getList().size(); i++) {
                                    map2 = new HashMap<String, String>();
                                    //判断返回来的是否为空
                                    if (cipfib.getJsonData().getList().get(i).getCINVCODE() == null) {
                                        map2.put("content1", "");//物资编码
                                        System.out.println("************" + cipfib.getJsonData().getList().get(i).getCINVCODE());
                                    } else {
                                        map2.put("content1", cipfib.getJsonData().getList().get(i).getCINVCODE());//物资编码
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getCBATCH() == null) {
                                        map2.put("content2", "");//批号
                                    } else {
                                        map2.put("content2", cipfib.getJsonData().getList().get(i).getCBATCH());//批号
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getCINVNAME() == null) {
                                        map2.put("content3", "");//物资名称
                                    } else {
                                        map2.put("content3", cipfib.getJsonData().getList().get(i).getCINVNAME());//物资名称
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getCPARENTID() == null) {
                                        map2.put("content4", "");//货位编码
                                    } else {
                                        map2.put("content4", cipfib.getJsonData().getList().get(i).getCPARENTID());//货位编码
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getCINVSTD() == null) {
                                        map2.put("content5", "");//规格型号
                                    } else {
                                        map2.put("content5", cipfib.getJsonData().getList().get(i).getCINVSTD());//规格型号
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getCCOMUNITNAME() == null) {
                                        map2.put("content6", "");//单位
                                    } else {
                                        map2.put("content6", cipfib.getJsonData().getList().get(i).getCCOMUNITNAME());//单位
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getFQUANTITY() == null) {
                                        map2.put("content7", "");//数量
                                    } else {
                                        map2.put("content7", cipfib.getJsonData().getList().get(i).getFQUANTITY());//数量
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getFTAXPRICE() == null) {
                                        map2.put("content8", "");//单价
                                    } else {
                                        map2.put("content8", cipfib.getJsonData().getList().get(i).getFTAXPRICE());//单价
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getTAXAMOUNT() == null) {
                                        map2.put("content9", "");//合计金额
                                    } else {
                                        map2.put("content9", cipfib.getJsonData().getList().get(i).getTAXAMOUNT());//合计金额
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getFUNITPRICE() == null) {
                                        map2.put("content10", "");//无税单价
                                    } else {
                                        map2.put("content10", cipfib.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
                                    }
                                    if (cipfib.getJsonData().getList().get(i).getFMONDEY() == null) {
                                        map2.put("content11", "");//无税金额
                                    } else {
                                        map2.put("content11", cipfib.getJsonData().getList().get(i).getFMONDEY());//无税金额
                                    }
                                    map2.put("content13", "");//备注
                                    map2.put("flag", "false");
                                    checkinlistscq.add(map2);
                                    madapterCheckProFitInt.notifyDataSetChanged();
                                    madapterCheckProFitInt.hind(1);//隐藏复选框
                                    SummaryOfTotalValue(checkinlistscq);
                                }
                            }
                        } catch (Exception ex) {
                            Toast.makeText(ActivityCheckInventoryProFitInt.CheckInventoryProFitInt, "盘点入库单表体，请求数据错误", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, Throwable throwable, String
                            s, Object o) {
                        Toast.makeText(ActivityCheckInventoryProFitInt.CheckInventoryProFitInt, "盘点入库单表体，服务器请求失败", Toast.LENGTH_SHORT).show();
                    }
                }

        );
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

                        if (cipfih.getCHANDLER() == null) {//如果审核人为空，那么说明未审核，可以删除
                            HttpNetworkRequest.post("goods/rs/hpCheckstorage/" + hprguid, params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, String s, Object o) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);//审核入库单接口
                                        if (jsonObject.getString("statusCode").equals("200")) {
                                            Toast.makeText(ActivityCheckInventoryProFitInt.this, "审核通过", Toast.LENGTH_SHORT).show();
                                            //跳转到物资出库单
                                            startActivity(new Intent(ActivityCheckInventoryProFitInt.this, ActivityInventoryProFitInt.class));
                                            ActivityInventoryProFitInt.inventoryProFitInt.onRefresh();
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                                    Log.i("ii", "出错鸟");
                                }

                            });
                        } else {
                            Toast.makeText(ActivityCheckInventoryProFitInt.this, "已经审核过，不需要审核了", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityCheckInventoryProFitInt.this, "放弃审核", Toast.LENGTH_SHORT).show();
                    }
                }).

                show();
    }

    //点击回退
    public void back(View view) {
        finish();
    }

    //点击删除
    public void delete(View view) {
        RequestParams params = new RequestParams();
        params.put("str", FormOuter_hprGuid);
        //如果审核人为空，那么说明未审核，可以删除
        if (cipfih.getCHANDLER() == null||cipfih.getCHANDLER().equals("")) {
            //盘点出库单删除接口
            HttpNetworkRequest.delete("goods/rs/hpCheckstorage", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    try {
                        JSONObject mjsonObject = new JSONObject(s);//审核入库单接口
                        if (mjsonObject.getString("message").equals("删除成功")) {
                            Toast.makeText(ActivityCheckInventoryProFitInt.this, "删除成功", Toast.LENGTH_SHORT).show();
                            //跳转到物资出库单
                            startActivity(new Intent(ActivityCheckInventoryProFitInt.this, ActivityInventoryProFitInt.class));
                            ActivityInventoryProFitInt.inventoryProFitInt.onRefresh();
                            finish();
                        } else {
                            String msg = mjsonObject.getString("message");
                            Toast.makeText(ActivityCheckInventoryProFitInt.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(ActivityCheckInventoryProFitInt.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityCheckInventoryProFitInt.this, "服务器请求失败，删除不成功", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(ActivityCheckInventoryProFitInt.this, "已审核，删除不成功", Toast.LENGTH_SHORT).show();
        }
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

}
