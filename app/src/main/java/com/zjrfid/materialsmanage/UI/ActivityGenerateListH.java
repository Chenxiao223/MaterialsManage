package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.GenerateListH;
import com.zjrfid.materialsmanage.adapter.AdapterSdh;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxiao on 2017/5/23.
 * 生单（头）
 */
public class ActivityGenerateListH extends Activity {
    public static ActivityGenerateListH activityGenerateListH;
    private ListView lv_create;
    private AdapterSdh adapterSdh;
    private HashMap<String, String> hashMap;
    private List<HashMap<String, String>> sd_Listscq = new ArrayList<>();
    private int record = 0;
    public List<String> list_sld=new ArrayList<>();//加入收料单主键
    private TextView tv_alid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_h);
        //
        activityGenerateListH=this;
        initView();
    }

    public void initView() {
        tv_alid= (TextView) findViewById(R.id.tv_alid);
        lv_create = (ListView) findViewById(R.id.lv_create);
        adapterSdh = new AdapterSdh(ActivityGenerateListH.this, (ArrayList<HashMap<String, String>>) sd_Listscq);
        lv_create.setAdapter(adapterSdh);
        addData();
        //点击listview
        lv_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (record == 0 || judge(position)) {//如果是第一次选中或者供应商相同则可以执行
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    AdapterSdh.ViewHold holder = (AdapterSdh.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    // 将CheckBox的选中状况记录下来
                    // 调整选定条目
                    if (holder.cb.isChecked() == true) {
                        sd_Listscq.get(position).put("flag", "true");
                        list_sld.add(sd_Listscq.get(position).get("content10"));
                        record += 1;
                    } else {
                        sd_Listscq.get(position).put("flag", "false");
                        list_sld.remove(sd_Listscq.get(position).get("content10"));
                        record -= 1;
                    }
                } else {
                    Toast.makeText(ActivityGenerateListH.this, "请选择相同供应商", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addData() {
        try {
            RequestParams params=new RequestParams();
            if (ActivityMaterialsInBound.materialsInBound.tv_supplier.getText().toString().equals("请供货单位")){
                params.put("cvenname", "");//供应商名称
            }else {
                params.put("cvenname", ActivityMaterialsInBound.materialsInBound.tv_supplier.getText().toString());//供应商名称
            }
            params.put("cvencode",ActivityMaterialsInBound.materialsInBound.cvencode);//供应商编码
            params.put("hpsnGuid", ActivityMaterialsInBound.materialsInBound.hpsnguid);//供应商主键
            HttpNetworkRequest.get("goods/rs/hpArrivalvouchRefer",params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    GenerateListH generateListH = gson.fromJson(rawResponse, GenerateListH.class);
                    if (generateListH.getJsonData().getList().size() > 0) {
                        for (int i = 0; i < generateListH.getJsonData().getList().size(); i++) {
                            hashMap = new HashMap<String, String>();
                            hashMap.put("content1", generateListH.getJsonData().getList().get(i).getALID());//单据号
                            hashMap.put("content2", generateListH.getJsonData().getList().get(i).getSDEPMENT());//部门
                            hashMap.put("content3", generateListH.getJsonData().getList().get(i).getSSALESMAN());//业务员
                            hashMap.put("content4", generateListH.getJsonData().getList().get(i).getDREQUIRDATE());//请购日期
                            hashMap.put("content5", generateListH.getJsonData().getList().get(i).getCPTNAME());//采购类型
                            hashMap.put("content6", generateListH.getJsonData().getList().get(i).getCVENNAME());//供应商
                            hashMap.put("content7", generateListH.getJsonData().getList().get(i).getSCURRENC());//币种
                            hashMap.put("content8", generateListH.getJsonData().getList().get(i).getCDEMO());//备注
                            hashMap.put("content9", generateListH.getJsonData().getList().get(i).getTRANSPORTATION());//运输方式
                            hashMap.put("content10",generateListH.getJsonData().getList().get(i).getHPALPID());//收料单主键
                            hashMap.put("content11",generateListH.getJsonData().getList().get(i).getCVENCODE());//供应商编码
                            hashMap.put("content12",generateListH.getJsonData().getList().get(i).getHPSNGUID());//供应商主键
                            hashMap.put("flag", "false");
                            sd_Listscq.add(hashMap);
                        }
                        dataChange();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //点击确定按钮
    public void determine(View view) {
        if (record==0){
            Toast.makeText(ActivityGenerateListH.this, "请勾选一项", Toast.LENGTH_SHORT).show();
        }else {
            for (int i=0;i<sd_Listscq.size();i++){
                if (sd_Listscq.get(i).get("flag").equals("true")){
                    ActivityMaterialsInBound.materialsInBound.tv_supplier.setText(sd_Listscq.get(i).get("content6"));
                    ActivityMaterialsInBound.materialsInBound.cvencode = sd_Listscq.get(i).get("content11");
                    ActivityMaterialsInBound.materialsInBound.hpsnguid = sd_Listscq.get(i).get("content12");
                    break;
                }
            }
            startActivity(new Intent(ActivityGenerateListH.this, ActivityGenerateListB.class));
            finish();
        }
    }

    //点击箭头
    public void back(View view) {
        finish();
    }

    //刷新适配器
    public void dataChange() {
        adapterSdh.notifyDataSetChanged();
    }


    //判断供应商是否相同
    public boolean judge(int posision) {
        String cvenname = "";
        try {
            for (int i = 0; i < sd_Listscq.size(); i++) {
                if (sd_Listscq.get(i).get("flag").equals("true")) {
                    cvenname = sd_Listscq.get(i).get("content6");
                }
            }
            for (int j=0;j<sd_Listscq.size();j++){
                if (cvenname.equals(sd_Listscq.get(posision).get("content6"))){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //点击置顶按钮
    public void stick(View view){
        lv_create.setSelection(0);
    }

    //点击查询按钮
    public void check(View view){
        sd_Listscq.clear();//清空集合
        RequestParams params=new RequestParams();
        if (ActivityMaterialsInBound.materialsInBound.tv_supplier.getText().toString().equals("请供货单位")){
            params.put("cvenname", "");//供应商名称
        }else {
            params.put("cvenname", ActivityMaterialsInBound.materialsInBound.tv_supplier.getText().toString());//供应商名称
        }
        params.put("cvencode",ActivityMaterialsInBound.materialsInBound.cvencode);//供应商编码
        params.put("hpsnGuid",ActivityMaterialsInBound.materialsInBound.hpsnguid);//供应商主键
        params.put("alid",tv_alid.getText().toString());
        HttpNetworkRequest.get("goods/rs/hpArrivalvouchRefer", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                Gson gson = new Gson();
                GenerateListH generateListH = gson.fromJson(rawResponse, GenerateListH.class);
                if (generateListH.getJsonData().getList().size() > 0) {
                    for (int i = 0; i < generateListH.getJsonData().getList().size(); i++) {
                        hashMap = new HashMap<String, String>();
                        hashMap.put("content1", generateListH.getJsonData().getList().get(i).getALID());//单据号
                        hashMap.put("content2", generateListH.getJsonData().getList().get(i).getSDEPMENT());//部门
                        hashMap.put("content3", generateListH.getJsonData().getList().get(i).getSSALESMAN());//业务员
                        hashMap.put("content4", generateListH.getJsonData().getList().get(i).getDREQUIRDATE());//请购日期
                        hashMap.put("content5", generateListH.getJsonData().getList().get(i).getCPTNAME());//采购类型
                        hashMap.put("content6", generateListH.getJsonData().getList().get(i).getCVENNAME());//供应商
                        hashMap.put("content7", generateListH.getJsonData().getList().get(i).getSCURRENC());//币种
                        hashMap.put("content8", generateListH.getJsonData().getList().get(i).getCDEMO());//备注
                        hashMap.put("content9", generateListH.getJsonData().getList().get(i).getTRANSPORTATION());//运输方式
                        hashMap.put("content10",generateListH.getJsonData().getList().get(i).getHPALPID());//收料单主键
                        hashMap.put("content11",generateListH.getJsonData().getList().get(i).getCVENCODE());//供应商编码
                        hashMap.put("content12",generateListH.getJsonData().getList().get(i).getHPSNGUID());//供应商主键
                        hashMap.put("flag", "false");
                        sd_Listscq.add(hashMap);
                    }
                    dataChange();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Toast.makeText(ActivityGenerateListH.this, "服务器连接异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
