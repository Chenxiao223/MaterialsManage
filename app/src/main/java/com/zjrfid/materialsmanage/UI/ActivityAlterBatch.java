package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.Batch;
import com.zjrfid.materialsmanage.acdbentity.House2;
import com.zjrfid.materialsmanage.adapter.HouseAlterAdapter;
import com.zjrfid.materialsmanage.adapter.IAdapter3;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ActivityAlterBatch extends Activity {
    public static ActivityAlterBatch batch;
    ListView listview;
    public List<House2> mlist = new ArrayList<>();
    EditText rkshuliang;
    TextView total, wzbianma, tv_cinvcode, tv_cwhname;
    public House2 house;
    HouseAlterAdapter adapter;
    public int state = 0;
    public static int p = 0, i;
    int j;
    public Intent it;
    public Batch mBatch;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_batch);
        //
        batch = this;

        SysApplication.getInstance().addActivity(this);
        wzbianma = (TextView) this.findViewById(R.id.tv_wzbianma);
        total = (TextView) findViewById(R.id.total);
        it = getIntent();
        state = it.getIntExtra("flag", 0);
        initView();
        initData();
    }

    /**
     * 初始化数据（只有初始化完 控件以后 才能给相应滴控件设置添加数据）
     */
    private void initData() {
        adapter = new HouseAlterAdapter(this, mlist);
        listView.setAdapter(adapter);
        addData();
        adapter.setOnItemEditText(new HouseAlterAdapter.OnItemEditText() {
            @Override
            public void setText(String s, int p) {
                int num = Integer.parseInt(s);
                mlist.get(p).setRkshuliang(num);
            }
        });
    }

    /**
     * 初始化布局（初始化视图控件等操作）
     */
    private void initView() {
        rkshuliang = (EditText) findViewById(R.id.rkshuliang);
        listView = (ListView) this.findViewById(R.id.listview);
        tv_cinvcode = (TextView) findViewById(R.id.tv_cinvcode);
        tv_cinvcode.setText("物资名称：" + it.getStringExtra("cinvname"));
        tv_cwhname = (TextView) findViewById(R.id.tv_cwhname);
        tv_cwhname.setText("仓库名称：" + it.getStringExtra("cwhname"));

    }


    public void addData() {
        //批次接口
        RequestParams params = new RequestParams();
        params.put("cinvcode", getIntent().getStringExtra("cinvcode"));
        params.put("cwhname", getIntent().getStringExtra("cwhname"));
        params.put("cinvname", getIntent().getStringExtra("cinvname"));
        params.put("fquantity", "1");
        params.put("cwhcode", getIntent().getStringExtra("cwhcode"));
        if (getIntent().getStringExtra("cinvcode").equals("")) {
        } else {
            //批次信息接口
            HttpNetworkRequest.get("goods/rs/hpoutstorageBatch", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    mBatch = gson.fromJson(s, Batch.class);
                    p = 0;
                    for (j = 0; j < mBatch.getJsonData().getList().size(); j++) {
                        //物资数量、物资单价、入库数量、
                        house = new House2((int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFQUANTITYS()),
                                (int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFTAXPRICE()), 0,
                                mBatch.getJsonData().getList().get(j).getCINVCODE(), mBatch.getJsonData().getList().get(j).getCINVNAME(),
                                mBatch.getJsonData().getList().get(j).getCPOSCODE(), mBatch.getJsonData().getList().get(j).getCBATCH(),
                                "false", false);
                        mBatch.getJsonData().getList().get(j).getHPROGUID();
                        i = (int) Double.parseDouble(mBatch.getJsonData().getList().get(j).getFQUANTITYS());
                        p += i;
                        mlist.add(house);
                    }
                    total.setText("总数：" + p);
                    adapter.notifyDataSetChanged();
                    xx();
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Log.e("error", "出错了");
                }
            });
        }
    }


    //取消
    public void remove(View view) {
        finish();
    }

    //确定
    public void sure(View view) throws Exception {
        try {
            IAdapter3.count = 0;//归零
            //将所有选中的条目清空
            for (int i = 0; i < ActivityMaterialsOutBound.materialsOutBound.listscq.size(); i++) {
                ActivityMaterialsOutBound.materialsOutBound.listscq.get(i).put("flag", "false");
            }
            DecimalFormat df = new DecimalFormat("0.000000 ");//小数点后保留6位小数
            int position = it.getIntExtra("position", 0);
            //如果没被勾选且数量都为0则提示
            if (judge()) {
                for (int i = 0; i < mlist.size(); i++) {//有多少条批号就循环多少次，然后在判断哪些是勾选了的
                    if (mlist.get(i).getIs().equals("true") && mlist.get(i).getRkshuliang() != 0) {//如果勾选了就加入集合且数量不为零
                        ActivityMaterialsOutBound.materialsOutBound.map2 = new HashMap<String, String>();
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content1", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content1"));//物资编码
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content2", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content2"));//旧编码
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content3", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content3"));//分类编码
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content4", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content4"));//物料名称
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content5", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content5"));//默认税率
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content6", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content6"));//规格型号
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content7", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content7"));//是否资产
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content8", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content8"));//默认货位
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content9", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content9"));//主计量单位
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content10", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content10"));//默认仓库
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content11", ActivityMaterialsOutBound.materialsOutBound.listscq.get(position).get("content11"));//辅助计量单位
                        int cksl = mlist.get(i).getRkshuliang();//出库数量
                        double hsdj = Double.valueOf(mBatch.getJsonData().getList().get(i).getFTAXPRICE());//含税单价
                        double sl = Double.valueOf(mBatch.getJsonData().getList().get(i).getFTAXRATE());//税率
                        double hsje = hsdj * cksl;//含税金额
                        double wsdj = hsdj / (1 + sl);//无税单价
                        double wsje = hsje / (1 + sl);//无税金额
                        double se = hsje - wsje;//税额
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content12", "" + df.format(wsdj));//无税单价
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content13", "" + df.format(wsje));//无税金额
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content14", String.valueOf(df.format(hsdj)));//含税单价
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content15", "" + df.format(hsje));//含税金额
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content16", "" + sl);//税率
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content17", "" + df.format(se));//税额
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content18", mlist.get(i).getPici());//批号
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content19", "" + mlist.get(i).getWzshuliang());//数量
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content20", mlist.get(i).getHuowei());//货位
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content21", "" + cksl);//出库数量
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content22", "");//备注
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("content23", it.getStringExtra("hwbm"));//货位编码
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("delFlag", "0");//删除标志位
                        ActivityMaterialsOutBound.materialsOutBound.map2.put("flag", "false");
                        ActivityMaterialsOutBound.materialsOutBound.listscq.add(ActivityMaterialsOutBound.materialsOutBound.map2);
                        ActivityMaterialsOutBound.dataChanged();
                        //显示总价
                        ActivityMaterialsOutBound.materialsOutBound.dbl_number = ActivityMaterialsOutBound.materialsOutBound.dbl_number + Double.parseDouble(df.format(hsje) + "");
                        ActivityMaterialsOutBound.materialsOutBound.tv_total_prices.setText(df.format(ActivityMaterialsOutBound.materialsOutBound.dbl_number) + "");
                        //计算数量
                        ActivityMaterialsOutBound.materialsOutBound.amount = ActivityMaterialsOutBound.materialsOutBound.amount + mlist.get(i).getRkshuliang();
                        ActivityMaterialsOutBound.materialsOutBound.tv_amount.setText(ActivityMaterialsOutBound.materialsOutBound.amount + "");
                    }
                }
                //显示总价
                ActivityMaterialsOutBound.materialsOutBound.dbl_number = ActivityMaterialsOutBound.materialsOutBound.dbl_number - Double.parseDouble(ActivityMaterialsOutBound.materialsOutBound.listscq.get(ActivityMaterialsOutBound.materialsOutBound.item).get("content15"));
                ActivityMaterialsOutBound.materialsOutBound.tv_total_prices.setText(df.format(ActivityMaterialsOutBound.materialsOutBound.dbl_number) + "");
                //计算数量
                ActivityMaterialsOutBound.materialsOutBound.amount = ActivityMaterialsOutBound.materialsOutBound.amount - mlist.get(i).getRkshuliang();
                ActivityMaterialsOutBound.materialsOutBound.tv_amount.setText(ActivityMaterialsOutBound.materialsOutBound.amount + "");
                //只有点击修改进来才删除原先那条
                ActivityMaterialsOutBound.materialsOutBound.listscq.remove(position);
                ActivityMaterialsOutBound.dataChanged();
                finish();
                p = 0;
            } else {
                Toast.makeText(ActivityAlterBatch.this, "没勾选或出库数量为0", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityAlterBatch.this, "出错", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean judge() {
        boolean is = false;
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).getIs().equals("true") && mlist.get(i).getRkshuliang() != 0) {
                is = true;
            }
        }
        return is;
    }

    //
    public void xx() {
        for (int j = 0; j < ActivityMaterialsOutBound.materialsOutBound.listscq.size(); j++) {
            String cinvcode = ActivityMaterialsOutBound.materialsOutBound.listscq.get(j).get("content1");//物资编码
            String batch = ActivityMaterialsOutBound.materialsOutBound.listscq.get(j).get("content18");//批号
            for (int i = 0; i < mlist.size(); i++) {
                //如果物资编码和批号都相同则不能被选中
                if (mlist.get(i).getPici().equals(batch) && mlist.get(i).getBianma().equals(cinvcode)) {
                    mlist.get(i).setCbisEnable(true);
                }
            }
        }
    }

}
