package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.GenerateListB;
import com.zjrfid.materialsmanage.adapter.AdapterSdb;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxiao on 2017/5/23.
 * 生单（体）
 */
public class ActivityGenerateListB extends Activity {
    public static ActivityGenerateListB generateListB;
    private ListView lv_create;
    private AdapterSdb adapterSdb;
    private HashMap<String, String> hashMap;
    public List<HashMap<String, String>> sdb_Listscq = new ArrayList<>();
    private int record = 0;
    private List<String> list_sld = new ArrayList<>();//加入收料单主键
    private Boolean judge = true;
    private Button btn_select_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list_b);
        //
        generateListB = this;
        initView();
    }

    public void initView() {
        btn_select_all = (Button) findViewById(R.id.btn_select_all);
        lv_create = (ListView) findViewById(R.id.lv_create);
        adapterSdb = new AdapterSdb(ActivityGenerateListB.this, (ArrayList<HashMap<String, String>>) sdb_Listscq);
        lv_create.setAdapter(adapterSdb);
        addData();
        //点击listview
        lv_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    AdapterSdb.ViewHold holder = (AdapterSdb.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    // 将CheckBox的选中状况记录下来
                    // 调整选定条目
                    if (holder.cb.isChecked() == true) {
                        sdb_Listscq.get(position).put("flag", "true");
                        list_sld.add(sdb_Listscq.get(position).get("content10"));
                        record += 1;
                    } else {
                        sdb_Listscq.get(position).put("flag", "false");
                        list_sld.remove(sdb_Listscq.get(position).get("content10"));
                        record -= 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addData() {
        try {
            for (int i = 0; i < ActivityGenerateListH.activityGenerateListH.list_sld.size(); i++) {
                RequestParams params = new RequestParams();
                params.put("str", "'" + ActivityGenerateListH.activityGenerateListH.list_sld.get(i) + "'");
                //入库单生单查询收料单子表信息接口
                HttpNetworkRequest.put("goods/rs/hpArrivalvouchChRefer", params, new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                        Gson gson = new Gson();
                        GenerateListB generateListB = gson.fromJson(rawResponse, GenerateListB.class);
                        if (generateListB.getJsonData().getList().size() > 0) {
                            for (int i = 0; i < generateListB.getJsonData().getList().size(); i++) {
                                hashMap = new HashMap<String, String>();
                                hashMap.put("content1", generateListB.getJsonData().getList().get(i).getCINVCODE());//物资编码
                                hashMap.put("content2", generateListB.getJsonData().getList().get(i).getCINVNAME());//物资名称
                                hashMap.put("content3", generateListB.getJsonData().getList().get(i).getCINVSTD());//规格型号
                                hashMap.put("content4", generateListB.getJsonData().getList().get(i).getCCOMUNITNAME());//主计量单位
                                hashMap.put("content5", generateListB.getJsonData().getList().get(i).getFQUANTITY());//数量
                                hashMap.put("content6", generateListB.getJsonData().getList().get(i).getORDERNUM());//剩余数量
                                hashMap.put("content7", generateListB.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
                                hashMap.put("content8", generateListB.getJsonData().getList().get(i).getFTAXPRICE());//含税单价
                                hashMap.put("content9", generateListB.getJsonData().getList().get(i).getFMONDEY());//无税金额
                                hashMap.put("content10", generateListB.getJsonData().getList().get(i).getTAXAMOUNT());//含税金额
                                hashMap.put("content11", generateListB.getJsonData().getList().get(i).getFTAXRATE());//税率
                                hashMap.put("content12", generateListB.getJsonData().getList().get(i).getIPERTAXRATE());//税额
                                hashMap.put("content13", generateListB.getJsonData().getList().get(i).getCPARENTID());//默认货位
                                hashMap.put("content14", generateListB.getJsonData().getList().get(i).getCWHNAME());//默认仓库
                                hashMap.put("content15", generateListB.getJsonData().getList().get(i).getCDEMO());//备注
                                hashMap.put("flag", "false");
                                sdb_Listscq.add(hashMap);
                            }
                            dataChange();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //点击确定按钮
    public void determine(View view) {
//        startActivity(new Intent(this, ));
        if (record == 0) {
            Toast.makeText(ActivityGenerateListB.this, "请选中一条", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, ActivityGenerateList.class));
            finish();
        }
    }

    //点击箭头
    public void back(View view) {
        finish();
    }

    //刷新适配器
    public void dataChange() {
        adapterSdb.notifyDataSetChanged();
    }

    //全选和全不选
    public void select_All(View view) {
        if (judge) {
            btn_select_all.setText("全不选");
            adapterSdb.checkAll(1);
            for (int i = 0; i < sdb_Listscq.size(); i++) {
                sdb_Listscq.get(i).put("flag", "true");
            }
            judge = false;
            record += 1;
        } else {
            btn_select_all.setText("全选");
            adapterSdb.checkAll(2);
            for (int i = 0; i < sdb_Listscq.size(); i++) {
                sdb_Listscq.get(i).put("flag", "false");
            }
            judge = true;
            record -= 1;
        }
        dataChange();
    }

}
