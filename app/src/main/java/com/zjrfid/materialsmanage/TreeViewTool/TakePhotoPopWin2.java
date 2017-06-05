package com.zjrfid.materialsmanage.TreeViewTool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityBatch;
import com.zjrfid.materialsmanage.UI.ActivityMaterialsOutBound;
import com.zjrfid.materialsmanage.acdbentity.Batch;
import com.zjrfid.materialsmanage.acdbentity.House;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocationRfid;
import com.zjrfid.materialsmanage.adapter.GalleryAdapter;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;

import org.apache.http.Header;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class TakePhotoPopWin2 extends PopupWindow {
    private View view;
    public EditText et_remark;
    private int i = 0;
    private LinearLayout lineone, linetwo;
    public static TakePhotoPopWin2 instance;
    public int j;
    public TextView wsprice, wsmoney, hsprice, hsmoney, tax, tax_rate, tv_number;
    public TextView wzbm, jbm, flbm, wlmc, mrsl, ggxh, sfzc, mrhw, zjldw, mrck, fzjldw;
    public GalleryAdapter mAdapter;
    public List<House> mDatas;
    Batch mBatch;
    House house;
    public int sign = 0;
    public int item;
    public int state = 0;
    public int state2 = 0;
    public boolean is_judge = false;
    public List<String> list;
    public CgoodsAllocationRfid msfi;
    public String hwbm, hpiguid;

    public TakePhotoPopWin2(final Context mContext, View.OnClickListener itemsOnClick, final int flag) {
        instance = this;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_scan_wz2, null);
        et_remark = (EditText) view.findViewById(R.id.et_remark);
        lineone = (LinearLayout) view.findViewById(R.id.lineone);//包含按钮的布局
        linetwo = (LinearLayout) view.findViewById(R.id.linetwo);
        //
        initView();
        wsprice = (TextView) view.findViewById(R.id.wsprice);
        wsmoney = (TextView) view.findViewById(R.id.wsmoney);
        hsprice = (TextView) view.findViewById(R.id.hsprice);
        hsmoney = (TextView) view.findViewById(R.id.hsmoney);
        tax = (TextView) view.findViewById(R.id.tax);
        tax_rate = (TextView) view.findViewById(R.id.tax_rate);
        tv_number = (TextView) view.findViewById(R.id.tv_number);
        mDatas = new ArrayList<>();

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置适配器
        mAdapter = new GalleryAdapter(mContext, mDatas);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
            }
        });
        //标志位判断:设置一个标志位，如果为1，则说明是点修改进入该页面，显示添加和取消按钮
        if (flag == 1) {
            lineone.setVisibility(View.GONE);
            linetwo.setVisibility(View.VISIBLE);
        }
        //点击叉号
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state2 == 11) {//区别是否是从点击修改弹出的
                    //点击弹出对话框
                    new AlertDialog.Builder(mContext).setTitle("温馨提示").setMessage("是否需要保存你的数据？")
                            .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityMaterialsOutBound.materialsOutBound.map2 = new HashMap<String, String>();
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content1", wzbm.getText().toString());//物资编码
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content2", jbm.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content3", flbm.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content4", wlmc.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content5", mrsl.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content6", ggxh.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content7", sfzc.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content8", mrhw.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content9", zjldw.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content10", mrck.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content11", fzjldw.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content12", wsprice.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content13", wsmoney.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content14", hsprice.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content15", hsmoney.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content16", tax.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content17", tax_rate.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content18", ActivityMaterialsOutBound.materialsOutBound.mlist.get(item).getPici());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content19", "" + ActivityMaterialsOutBound.materialsOutBound.mlist.get(item).getWzshuliang());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content20", ActivityMaterialsOutBound.materialsOutBound.mlist.get(item).getHuowei());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content21", "" + ActivityMaterialsOutBound.materialsOutBound.mlist.get(item).getRkshuliang());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("content22", et_remark.getText().toString());
                                    ActivityMaterialsOutBound.materialsOutBound.map2.put("flag", "false");
                                    ActivityMaterialsOutBound.materialsOutBound.listscq.add(ActivityMaterialsOutBound.materialsOutBound.map2);
                                    ActivityMaterialsOutBound.materialsOutBound.iAdapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            })
                            .setNegativeButton("不保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dismiss();
                                }
                            }).show();
                } else {
                    dismiss();
                }
            }
        });


        //点击扫描
        view.findViewById(R.id.sm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rfid = getRfid();
                if (rfid.equals("")) {
                    Toast.makeText(mContext, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
                } else {
                    lineone.setVisibility(View.GONE);//隐藏布局
                    linetwo.setVisibility(View.VISIBLE);//显示布局
                    RequestParams params = new RequestParams();
                    params.put("rfid", rfid);
                    //物资档案接口
                    HttpNetworkRequest.get("goods/rs/rfid", params, new BaseHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, String s, Object o) {
                            Gson mGson = new Gson();
                            msfi = mGson.fromJson(s, CgoodsAllocationRfid.class);
                            if (msfi.getJsonData().size() != 0) {
                                wzbm.setText(msfi.getJsonData().get(0).getCINVCODE());//物资编码
                                jbm.setText(msfi.getJsonData().get(0).getOLDCORD());//旧编码
                                flbm.setText(msfi.getJsonData().get(0).getHPICGUID());//分类编码
                                wlmc.setText(msfi.getJsonData().get(0).getCINVNAME());//物料名称
                                mrsl.setText(msfi.getJsonData().get(0).getFTAXRATE());//默认税率
                                ggxh.setText(msfi.getJsonData().get(0).getCINVSTD());//规格型号
                                sfzc.setText("");//是否资产
                                mrhw.setText(msfi.getJsonData().get(0).getCPARENTID());//默认货位
                                zjldw.setText(msfi.getJsonData().get(0).getOLDUNITNAME());//主计量单位
                                mrck.setText(msfi.getJsonData().get(0).getCWHNAME());//默认仓库
                                fzjldw.setText(msfi.getJsonData().get(0).getCUNITNAME());//辅助计量单位
                                hwbm = msfi.getJsonData().get(0).getCPOSCODE();//货位编码
                                hpiguid = msfi.getJsonData().get(0).getHPIGUID();
                            } else {
                                Toast.makeText(mContext, "此标签未绑定", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                        }
                    });
                }
            }
        });
        //点击批次
        view.findViewById(R.id.bach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityBatch.class);
                intent.putExtra("cinvcode", wzbm.getText().toString());//物资编码
                intent.putExtra("cinvname", wlmc.getText().toString());//物资名称
                intent.putExtra("cwhname", ActivityMaterialsOutBound.materialsOutBound.tv_warehouse.getText().toString());//仓库名称
                intent.putExtra("cwhcode", ActivityMaterialsOutBound.materialsOutBound.cwhcode2);//仓库编码
                intent.putExtra("hwbm", hwbm);//货位编码
                if (wzbm.getText().toString().equals("")) {
                    Toast.makeText(mContext, "请先扫描物资", Toast.LENGTH_SHORT).show();
                } else {
                    mContext.startActivity(intent);
                    dismiss();
                }
            }
        });
        //点击取消
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineone.setVisibility(View.VISIBLE);
                linetwo.setVisibility(View.GONE);
            }
        });
        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);//高
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);//宽

        // 设置弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    public void PociInfo(int i) {
        double hsp;
        double tax_r;
        double nber;
        for (int j = 0; j < mDatas.size(); j++) {
            DecimalFormat df = new DecimalFormat("0.000000 ");//小数点后保留6位小数
            hsprice.setText("含税单价：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());
            tax_rate.setText("税率：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE());
            tv_number.setText("数量：" + mDatas.get(i).getRkshuliang());

            //含税单价
            hsp = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());

            //税率
            tax_r = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE());
            //数量
            nber = mDatas.get(i).getRkshuliang();
            hsmoney.setText("含税金额：" + df.format(hsp * nber));
            wsprice.setText("无税单价：" + df.format((((hsp * nber) / nber)) / (1 + 0.01 * tax_r)));
            wsmoney.setText("无税金额：" + df.format((hsp * nber) / (1 + 0.01 * tax_r)));
            tax.setText("税额：" + df.format((hsp * nber) - (hsp * nber) / (1 + 0.01 * tax_r)));
        }

    }

    //初始化控件
    public void initView() {
        wzbm = (TextView) view.findViewById(R.id.wzbm);
        jbm = (TextView) view.findViewById(R.id.jbm);
        flbm = (TextView) view.findViewById(R.id.flbm);
        wlmc = (TextView) view.findViewById(R.id.wlmc);
        mrsl = (TextView) view.findViewById(R.id.mrsl);
        ggxh = (TextView) view.findViewById(R.id.ggxh);
        sfzc = (TextView) view.findViewById(R.id.sfzc);
        mrhw = (TextView) view.findViewById(R.id.mrhw);
        zjldw = (TextView) view.findViewById(R.id.zjldw);
        mrck = (TextView) view.findViewById(R.id.mrck);
        fzjldw = (TextView) view.findViewById(R.id.fzjldw);
    }


    //扫描获取rfid
    public String getRfid() {
        Result result = RfidOperation.readUnGivenTid((short) 3, (short) 3);
        String str = "";
        if (result.isSuccess() == true) {
            str = result.getReadInfo().toString();
        }
        return str;
    }

}
