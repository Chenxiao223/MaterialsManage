package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin4;
import com.zjrfid.materialsmanage.adapter.IAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/1 0001.
 * 生单
 */
public class ActivityGenerateList extends Activity {
    public static ActivityGenerateList generateList;
    public ListView lv_create;
    public static IAdapter iAdapter;
    public ArrayList<HashMap<String, String>> listscq = new ArrayList<>();
    public HashMap<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list);
        //
        generateList = this;
        initView();
        addData();
    }

    private void addData() {
        for (int i = 0; i < ActivityGenerateListB.generateListB.sdb_Listscq.size(); i++) {
            if (ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("flag").equals("true")) {
                map = new HashMap<String, String>();
                map.put("content1", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content1"));//物资编码
                map.put("content2", "");//旧编码
                map.put("content3", "");//分类编码
                map.put("content4", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content2"));//物料名称
                map.put("content5", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content11"));//默认税率
                map.put("content6", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content3"));//规格型号
                map.put("content7", "");//是否资产
                map.put("content8", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content13"));//默认货位
                map.put("content9", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content4"));//主计量单位
                map.put("content10", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content14"));//默认仓库
                map.put("content11", "");//辅助计量单位
                map.put("content12", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content7"));//无税单价
                map.put("content13", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content9"));//无税金额
                map.put("content14", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content8"));//含税单价
                map.put("content15", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content10"));//含税金额
                map.put("content16", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content11"));//税率
                map.put("content17", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content12"));//税额
                map.put("content18", "");//批号
                map.put("content19", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content5"));//数量
                map.put("content20", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content13"));//货位
                map.put("content21", ActivityGenerateListB.generateListB.sdb_Listscq.get(i).get("content15"));//备注
                map.put("delFlag", "0");
                map.put("flag", "false");
                map.put("content27", "");
                listscq.add(map);
            }
        }
        iAdapter.hind(1);
    }

    private void initView() {
        lv_create = (ListView) findViewById(R.id.lv_create);
        iAdapter = new IAdapter(this, listscq);
        lv_create.setAdapter(iAdapter);
        lv_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TakePhotoPopWin4 takePhotoPopWin = new TakePhotoPopWin4(ActivityGenerateList.this, onClickListener, 0, position);
                takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
                TakePhotoPopWin4.instance.wzbm.setText(listscq.get(position).get("content1"));//物资编码
                TakePhotoPopWin4.instance.jbm.setText(listscq.get(position).get("content2"));//旧编码
                TakePhotoPopWin4.instance.flbm.setText(listscq.get(position).get("content3"));//分类编码
                TakePhotoPopWin4.instance.wlmc.setText(listscq.get(position).get("content4"));//物料名称
                TakePhotoPopWin4.instance.mrsl.setText(listscq.get(position).get("content5"));//默认税率
                TakePhotoPopWin4.instance.ggxh.setText(listscq.get(position).get("content6"));//规格型号
                TakePhotoPopWin4.instance.sfzc.setText(listscq.get(position).get("content7"));//是否资产
                TakePhotoPopWin4.instance.mrhw.setText(listscq.get(position).get("content8"));//默认货位
                TakePhotoPopWin4.instance.zjldw.setText(listscq.get(position).get("content9"));//主计量单位
                TakePhotoPopWin4.instance.mrck.setText(listscq.get(position).get("content10"));//默认仓库
                TakePhotoPopWin4.instance.fzjldw.setText(listscq.get(position).get("content11"));//辅助计量单位
                TakePhotoPopWin4.instance.et_batch.setText(listscq.get(position).get("content18"));//批号
                TakePhotoPopWin4.instance.et_amount.setText(listscq.get(position).get("content19"));//数量
                TakePhotoPopWin4.instance.et_hanshui.setText(listscq.get(position).get("content15"));//含税金额
                TakePhotoPopWin4.instance.et_shuilv.setText(listscq.get(position).get("content16"));//税率
                TakePhotoPopWin4.instance.tv_huowei.setText(listscq.get(position).get("content20"));//货位名称
                TakePhotoPopWin4.instance.et_remark.setText(listscq.get(position).get("content21"));//备注
                TakePhotoPopWin4.instance.hpiguid = listscq.get(position).get("content24");//物资主键
                TakePhotoPopWin4.instance.hwbm = listscq.get(position).get("content23");//货位编码
            }
        });
    }

    //点击加载到入库单按钮
    public void save(View view) {
        //判断是否都填完整了
        if (judge()){//若都填完整了
            for (int i=0;i<listscq.size();i++){
                ActivityMaterialsInBound.materialsInBound.map2 = new HashMap<String, String>();
                ActivityMaterialsInBound.materialsInBound.map2.put("content1", listscq.get(i).get("content1"));//物资编码
                ActivityMaterialsInBound.materialsInBound.map2.put("content2", listscq.get(i).get("content2"));//旧编码
                ActivityMaterialsInBound.materialsInBound.map2.put("content3", listscq.get(i).get("content3"));//分类编码
                ActivityMaterialsInBound.materialsInBound.map2.put("content4", listscq.get(i).get("content4"));//物料名称
                ActivityMaterialsInBound.materialsInBound.map2.put("content5", listscq.get(i).get("content5"));//默认税率
                ActivityMaterialsInBound.materialsInBound.map2.put("content6", listscq.get(i).get("content6"));//规格型号
                ActivityMaterialsInBound.materialsInBound.map2.put("content7", listscq.get(i).get("content7"));//是否资产
                ActivityMaterialsInBound.materialsInBound.map2.put("content8", listscq.get(i).get("content8"));//默认货位
                ActivityMaterialsInBound.materialsInBound.map2.put("content9", listscq.get(i).get("content9"));//主计量单位
                ActivityMaterialsInBound.materialsInBound.map2.put("content10", listscq.get(i).get("content10"));//默认仓库
                ActivityMaterialsInBound.materialsInBound.map2.put("content11", listscq.get(i).get("content11"));//辅助计量单位
                ActivityMaterialsInBound.materialsInBound.map2.put("content12", listscq.get(i).get("content12"));//无税单价
                ActivityMaterialsInBound.materialsInBound.map2.put("content13", listscq.get(i).get("content13"));//无税金额
                ActivityMaterialsInBound.materialsInBound.map2.put("content14", listscq.get(i).get("content14"));//含税单价
                ActivityMaterialsInBound.materialsInBound.map2.put("content15", listscq.get(i).get("content15"));//含税金额
                ActivityMaterialsInBound.materialsInBound.map2.put("content16", listscq.get(i).get("content16"));//税率
                ActivityMaterialsInBound.materialsInBound.map2.put("content17", listscq.get(i).get("content17"));//税额
                ActivityMaterialsInBound.materialsInBound.map2.put("content18", listscq.get(i).get("content18"));//批号
                ActivityMaterialsInBound.materialsInBound.map2.put("content19", listscq.get(i).get("content19"));//数量
                ActivityMaterialsInBound.materialsInBound.map2.put("content20", listscq.get(i).get("content20"));//货位
                ActivityMaterialsInBound.materialsInBound.map2.put("content21", listscq.get(i).get("content21"));//备注
//                ActivityMaterialsInBound.materialsInBound.map2.put("content22", j + "");
                ActivityMaterialsInBound.materialsInBound.map2.put("content23", listscq.get(i).get("content23"));//货位编码
                ActivityMaterialsInBound.materialsInBound.map2.put("content24", listscq.get(i).get("content24"));//物资主键
                ActivityMaterialsInBound.materialsInBound.map2.put("delFlag", "0");
                ActivityMaterialsInBound.materialsInBound.map2.put("flag", "false");
                ActivityMaterialsInBound.materialsInBound.list_HprGuidCh.add("");
                ActivityMaterialsInBound.materialsInBound.listscq.add(ActivityMaterialsInBound.materialsInBound.map2);
                ActivityMaterialsInBound.iAdapter.setNewItemBackground(ActivityMaterialsInBound.materialsInBound.listscq.size() - 1, true);
                try {
                    //显示总价
                    ActivityMaterialsInBound.materialsInBound.dbl_number = ActivityMaterialsInBound.materialsInBound.dbl_number + Double.parseDouble(listscq.get(i).get("content15"));
                    DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
                    ActivityMaterialsInBound.materialsInBound.tv_total_prices.setText(df.format(ActivityMaterialsInBound.materialsInBound.dbl_number) + "");
                    //计算数量
                    ActivityMaterialsInBound.materialsInBound.amount = ActivityMaterialsInBound.materialsInBound.amount + Integer.parseInt(listscq.get(i).get("content19"));
                    ActivityMaterialsInBound.materialsInBound.tv_amount.setText(ActivityMaterialsInBound.materialsInBound.amount + "");
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this, "出错", Toast.LENGTH_SHORT).show();
                }
            }
            ActivityMaterialsInBound.materialsInBound.btn_save.setClickable(true);//保存按钮可以点击
            ActivityMaterialsInBound.dataChanged();
            finish();
        }else{
            Toast.makeText(ActivityGenerateList.this, "请填写完整", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }
    };

    //刷新适配器
    public static void dataChanged() {
        iAdapter.notifyDataSetChanged();
    }

    //点击回退按钮
    public void back(View view) {
        finish();
    }

    //点击置顶按钮
    public void stick(View view) {
        lv_create.setSelection(0);
    }

    public boolean judge() {
        for (int i = 0; i < listscq.size(); i++) {
            if (listscq.get(i).get("content2").equals("")) {
                return false;
            }
        }
        return true;
    }
}
