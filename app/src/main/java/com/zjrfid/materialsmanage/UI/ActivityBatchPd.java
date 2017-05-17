package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin3;
import com.zjrfid.materialsmanage.acdbentity.Batch;
import com.zjrfid.materialsmanage.adapter.AdapterPdBatch;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ActivityBatchPd extends Activity {
    public static ActivityBatchPd batch;
    public ArrayList<HashMap<String, String>> listscq = new ArrayList<HashMap<String, String>>();
    EditText rkshuliang;
    TextView total, wzbianma, tv_cinvcode, tv_cwhname;
    private Button btn_inquire;
    AdapterPdBatch adapter;
    public static int i;
    int j;
    public Intent it;
    public Batch mBatch;
    public ListView listView;
    public List<Integer> list_position = new ArrayList<>();

    private boolean bln_check = true;

    //5个加载进来信息
    private String mcinvcode = "";
    private String mcinvname = "";
    private String mcwhcode = "";
    private String mcwhname = "";
    public int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batchpd);
        batch = this;
        SysApplication.getInstance().addActivity(this);
        it = getIntent();
        state = it.getIntExtra("flag", 0);
        mcinvcode = it.getStringExtra("cinvcode");
        mcinvname = it.getStringExtra("cinvname");
        mcwhcode = it.getStringExtra("cwhcode");
        mcwhname = it.getStringExtra("cwhname");

        initView();
        NetworkRequest();

    }


    /**
     * 初始化布局（初始化视图控件等操作）
     */
    private void initView() {
        total = (TextView) findViewById(R.id.total);//总数量
        btn_inquire = (Button) findViewById(R.id.btn_inquire);//全选
        tv_cinvcode = (TextView) findViewById(R.id.tv_cinvcode);//物资名称
        tv_cwhname = (TextView) findViewById(R.id.tv_cwhname);//仓库名称
        listView = (ListView) this.findViewById(R.id.listview);//listview
        tv_cwhname.setText("仓库名称：" + mcwhname);
        tv_cinvcode.setText("物资名称：" + mcinvname);
        adapter = new AdapterPdBatch(this, listscq);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!list_position.contains(position)) {
                    AdapterPdBatch.ViewHold holder = (AdapterPdBatch.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    if (holder.cb.isChecked() == true) {
                        listscq.get(position).put("flag", "true");
                    } else {
                        listscq.get(position).put("flag", "false");
                    }
                }
            }
        });


    }

    //请求批次接口，获取仓库和物资下的批次信息，并保存于mBatch
    public void NetworkRequest() {
        try {
            if (mcinvcode.equals("") || mcinvcode == null || mcwhcode.equals("") || mcwhcode == null) {
                return;
            }
            RequestParams params = new RequestParams();
            params.put("cinvcode", mcinvcode);
            params.put("cwhcode", mcwhcode);
            //批次信息接口
            HttpNetworkRequest.get("goods/rs/hpoutstorageBatch?", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    mBatch = gson.fromJson(s, Batch.class);
                    //添加条数
                    total.setText(mBatch.getJsonData().getList().size() + "");//总数
                    //添加数据到listview
                    addData(mBatch);
                    xx();
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityBatchPd.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //取消
    public void remove(View view) {
        TakePhotoPopWin3.instance.sign = 0;//设置标志位用以区分
        finish();
    }

    //确定
    public void sure(View view) {
        if (listscq.size() == 0) {
            finish();
        } else {
            ActivityInventory.adapterPd.setNewListIndex();//清除适配器上一次选中项
            for (int i = 0; i < listscq.size(); i++) {
                if (listscq.get(i).get("flag").equals("true")) {
                    HashMap<String, String> new_temp = new HashMap<String, String>();
                    new_temp.put("content1", listscq.get(i).get("content4"));//批号
                    new_temp.put("content2", TakePhotoPopWin3.instance.wzbm.getText().toString());//物资编码
                    new_temp.put("content3", TakePhotoPopWin3.instance.mrhw.getText().toString());//货位
                    new_temp.put("content4", TakePhotoPopWin3.instance.wlmc.getText().toString());//物资名称
                    new_temp.put("content5", TakePhotoPopWin3.instance.ggxh.getText().toString());//规格型号
                    new_temp.put("content6", TakePhotoPopWin3.instance.zjldw.getText().toString());//单位
                    new_temp.put("content7", mBatch.getJsonData().getList().get(i).getFQUANTITYS());//账面数量
                    new_temp.put("content8", "");//盘点数量
                    new_temp.put("content9", "");//盈亏数量
                    new_temp.put("content10", mBatch.getJsonData().getList().get(i).getFTAXPRICE());//含税单价
                    new_temp.put("content11", mBatch.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
                    new_temp.put("content12", "");//盈亏含税金额
                    new_temp.put("content13", "");//盈亏无税金额
                    new_temp.put("content14", mBatch.getJsonData().getList().get(i).getTAXAMOUNT());//账面含税金额
                    new_temp.put("content15", mBatch.getJsonData().getList().get(i).getFMONDEY());//账面无税金额
                    new_temp.put("content16", TakePhotoPopWin3.instance.mrsl.getText().toString());//税率
                    new_temp.put("content17", mBatch.getJsonData().getList().get(i).getIPERTAXRATE());//账面税额
                    new_temp.put("content18", "");//盈亏税额
                    new_temp.put("content19", "");//备注
                    new_temp.put("flag", "false");
                    new_temp.put("delFlag", "0");

                    ActivityInventory.inventory.listscq.add(new_temp);
                    ActivityInventory.inventory.list_hpcvguidch.add("");
                    ActivityInventory.inventory.list_hpiguid.add(mBatch.getJsonData().getList().get(i).getHPIGUID().toString());
                    ActivityInventory.inventory.list_Cposcode.add(mBatch.getJsonData().getList().get(i).getCPOSCODE());
                    //添加适配器上的新添加项
                    ActivityInventory.adapterPd.setNewItemBackground(ActivityInventory.inventory.listscq.size()-1,true);
                    ActivityInventory.inventory.adapterPd.HideCheckBox(false);
                }
            }
            ActivityInventory.adapterPd.notifyDataSetChanged();
            ActivityInventory.inventory.lv_create.setSelection(ActivityInventory.inventory.listscq.size()-1);
        }
        //因为有数据，所以让保存按钮可以点击
        ActivityInventory.inventory.btn_save.setClickable(true);
        finish();
    }

    //添加Batch到listscq里，并适配界面
    public void addData(Batch newBatch) {
        for (int i = 0; i < newBatch.getJsonData().getList().size(); i++) {
            HashMap<String, String> new_map = new HashMap<String, String>();
            new_map.put("content1", newBatch.getJsonData().getList().get(i).getCINVCODE());//物资编码
            new_map.put("content2", newBatch.getJsonData().getList().get(i).getCINVNAME());//物资名称
            new_map.put("content3", newBatch.getJsonData().getList().get(i).getFQUANTITYS());//数量
            new_map.put("content4", newBatch.getJsonData().getList().get(i).getCBATCH());//批次
            new_map.put("content5", newBatch.getJsonData().getList().get(i).getCPOSNAME());//货位
            new_map.put("content6", "false");//标志位
            new_map.put("flag", "false");
            listscq.add(new_map);

        }
    }

    //点击全选，全不选
    public void check(View view) {
        if (bln_check) {
            btn_inquire.setText("全不选");
            adapter.checkAll(2);//参数为2表示全选
            for (int k = 0; k < listscq.size(); k++) {
                if (!list_position.contains(k)) {
                    listscq.get(k).put("flag", "true");
                }
            }
            bln_check = false;
        } else {
            btn_inquire.setText("全选");
            adapter.checkAll(3);//参数为3表示全不选
            for (int k = 0; k < listscq.size(); k++) {
                listscq.get(k).put("flag", "false");
            }
            bln_check = true;
        }
        adapter.notifyDataSetChanged();
    }

    //
    public void xx() {

        List<String> list = new ArrayList<>();//批号集合
        String cinvcode = it.getStringExtra("cinvcode");//物资编码
        //这个循环是拿到该物资编码下的所有批次
        for (int j = 0; j < ActivityInventory.inventory.listscq.size(); j++) {
            if(ActivityInventory.inventory.listscq.get(j).get("content2").equals(cinvcode) && ActivityInventory.inventory.listscq.get(j).get("delFlag").equals("0"))
            {
                list.add(ActivityInventory.inventory.listscq.get(j).get("content1"));

            }

        }
        for(int i=0;i<listscq.size();i++)
        {
            if(list.contains(listscq.get(i).get("content4")))
            {
                listscq.get(i).put("content6", "true");
                list_position.add(i);
            }
        }

    }

}
