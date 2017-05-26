package com.zjrfid.materialsmanage.TreeViewTool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityBatchPd;
import com.zjrfid.materialsmanage.UI.ActivityInventory;
import com.zjrfid.materialsmanage.acdbentity.Batch;
import com.zjrfid.materialsmanage.acdbentity.House;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo;
import com.zjrfid.materialsmanage.adapter.GalleryAdapter2;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;
import com.zjrfid.materialsmanage.tool.MyRecyclerView;

import org.apache.http.Header;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class TakePhotoPopWin3 extends PopupWindow {
    private View view;
    public EditText et_remark;
    private int i = 0;
    private LinearLayout lineone, linetwo;
    public static TakePhotoPopWin3 instance;
    public int j;
    public TextView wsprice, wsmoney, hsprice, hsmoney, tax, tax_rate, tv_number;
    public TextView tv_pici;
    public TextView wzbm, jbm, flbm, wlmc, mrsl, ggxh, sfzc, mrhw, zjldw, mrck, fzjldw;
    private MyRecyclerView mRecyclerView;
    public GalleryAdapter2 mAdapter;
    public ArrayList<HashMap<String, String>> mDatas;
    Batch mBatch;
    House house;
    public int sign = 0;
    public int item;
    public int state = 0;
    public int state2 = 0;
    public boolean is_judge = false;
    public List<String> list;
    public MaterialSpecificFilesInfo msfi;


    public TakePhotoPopWin3(final Context mContext, View.OnClickListener itemsOnClick, final int flag) {
        instance = this;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_scan_wz3, null);
        et_remark = (EditText) view.findViewById(R.id.et_remark);
        lineone = (LinearLayout) view.findViewById(R.id.lineone);//包含按钮的布局
        linetwo = (LinearLayout) view.findViewById(R.id.linetwo);
        //
        initView();
        ActivityInventory.inventory.sign = 1;
//        tv_pici = (TextView) view.findViewById(R.id.tv_pici);
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
                if (ActivityInventory.inventory.tv_warehouse.getText().toString().equals("请选择仓库")) {
                    Toast.makeText(mContext, "请先在盘点单中填写仓库", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else {
                    String rfid = getRfid();
                    if (rfid.equals("")) {
                        Toast.makeText(mContext, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            lineone.setVisibility(View.GONE);//隐藏布局
                            linetwo.setVisibility(View.VISIBLE);//显示布局
                            RequestParams params = new RequestParams();
                            params.put("rfid", rfid);
                            //物资档案接口
                            HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=&oldcord=&cinvcode=", params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, String s, Object o) {
                                    Gson mGson = new Gson();
                                    msfi = mGson.fromJson(s, MaterialSpecificFilesInfo.class);
                                    Log.i("info", "json: " + s);
                                    if (msfi.getJsonData().getList().size() != 0) {
                                        wzbm.setText(msfi.getJsonData().getList().get(0).getCINVCODE());//物资编码
                                        jbm.setText(msfi.getJsonData().getList().get(0).getOLDCORD());//旧编码
                                        flbm.setText(msfi.getJsonData().getList().get(0).getHPICGUID());//分类编码
                                        wlmc.setText(msfi.getJsonData().getList().get(0).getCINVNAME());//物料名称
                                        mrsl.setText(msfi.getJsonData().getList().get(0).getFTAXRATE());//默认税率
                                        ggxh.setText(msfi.getJsonData().getList().get(0).getCINVSTD());//规格型号
                                        sfzc.setText(msfi.getJsonData().getList().get(0).getIASSET());//是否资产
                                        mrhw.setText(msfi.getJsonData().getList().get(0).getCPARENTID());//默认货位
                                        zjldw.setText(msfi.getJsonData().getList().get(0).getOLDUNITNAME());//主计量单位
                                        mrck.setText(msfi.getJsonData().getList().get(0).getCWHNAME());//默认仓库
                                        fzjldw.setText(msfi.getJsonData().getList().get(0).getCUNITNAME());//辅助计量单位

//                                        ActivityInventory.inventory.list_hpiguid.add(msfi.getJsonData().getList().get(0).getHPIGUID());
//                                        ActivityInventory.inventory.list_Cposcode.add(msfi.getJsonData().getList().get(0).getCPOSCODE());
                                        Log.i("info", ActivityInventory.inventory.list_hpiguid.size() + "<<");
                                    } else {
                                        Toast.makeText(mContext, "此标签没有绑定", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                                    Log.e("error", "出错了");
                                }
                            });
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Toast.makeText(mContext, "此标签没有绑定", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //点击选择批次
        view.findViewById(R.id.tj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityBatchPd.class);
                intent.putExtra("flag", state);
                intent.putExtra("cinvcode", wzbm.getText().toString());//物资编码
                intent.putExtra("cinvname", wlmc.getText().toString());//物资名称
                intent.putExtra("cwhname", ActivityInventory.inventory.inventoryHeader.getCWHNAME());//仓库名称
                intent.putExtra("cwhcode", ActivityInventory.inventory.inventoryHeader.getCWHCODE());//仓库编码

                if (wzbm.getText().toString().equals("")) {
                    Toast.makeText(mContext, "请先扫描物资", Toast.LENGTH_SHORT).show();
                } else {
                    mContext.startActivity(intent);
                    dismiss();
                }
            }
        });
        //点击取消
        view.findViewById(R.id.cancel).
                setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           wzbm.setText("");//物资编码
                                           jbm.setText("");//旧编码
                                           flbm.setText("");//分类编码
                                           wlmc.setText("");//物料名称
                                           mrsl.setText("");//默认税率
                                           ggxh.setText("");//规格型号
                                           sfzc.setText("");//是否资产
                                           mrhw.setText("");//默认货位
                                           zjldw.setText("");//主计量单位
                                           mrck.setText("");//默认仓库
                                           fzjldw.setText("");//辅助计量单位
                                           lineone.setVisibility(View.VISIBLE);
                                           linetwo.setVisibility(View.GONE);
                                       }
                                   }

                );
        // 设置外部可点击
        this.

                setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.

                setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.

                setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);//高

        this.

                setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);//宽

        // 设置弹出窗体可点击
        this.

                setFocusable(true);

        this.

                setOutsideTouchable(false);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.

                setAnimationStyle(R.style.take_photo_anim);

    }

    public void initDatas() {
        mDatas.clear();
        for (int i = 0; i < ActivityBatchPd.batch.listscq.size(); i++) {
            //判断盘点批次那边条目有没有被选中，选中了才可以显示
            if (ActivityBatchPd.batch.listscq.get(i).get("flag").equals("true")) {
                mDatas.add(ActivityBatchPd.batch.listscq.get(i));

                Log.i("info", "false");
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    public void PociInfo(int i) {
        double hsp;
        double tax_r;
        double nber;
        for (int j = 0; j < mDatas.size(); j++) {
            DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
//            hsprice.setText("含税单价：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());
//            tax_rate.setText("税率：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE());
//            tv_number.setText("数量：" + mDatas.get(i).getRkshuliang());
//
//            //含税单价
//            hsp = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());
//
//            //税率
//            tax_r = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE());
//            //数量
//            nber = mDatas.get(i).getRkshuliang();
//            hsmoney.setText("含税金额：" + df.format(hsp * nber));
//            wsprice.setText("无税单价：" + df.format((((hsp * nber) / nber)) / (1 + 0.01 * tax_r)));
//            wsmoney.setText("无税金额：" + df.format((hsp * nber) / (1 + 0.01 * tax_r)));
//            tax.setText("税额：" + df.format((hsp * nber) - (hsp * nber) / (1 + 0.01 * tax_r)));
        }

    }

    //接收来自批次的信息的方法
//    public void PociInfo2(int i) {
//        if (is_judge) {//这里是为了区分盘点和出库,10表示出库
//            double hsm;
//            double wsm;
//            double hsp;
//            double tax_r;
//            double nber;
//            for (int j = 0; j < mDatas.size(); j++) {
//                DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
//                hsprice.setText("含税单价：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());
//                tax_rate.setText("税率：" + ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE() + "%");
//                tv_number.setText("数量：" + mDatas.get(i).getRkshuliang());
//                //含税单价
//                hsp = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXPRICE());
//                //税率
//                tax_r = Double.parseDouble(ActivityBatch.batch.mBatch.getJsonData().getList().get(i).getFTAXRATE());
//                //数量
//                nber = mDatas.get(i).getRkshuliang();
//                hsmoney.setText("含税金额：" + df.format(hsp * nber));
//                wsprice.setText("无税单价：" + df.format((((hsp * nber) / nber)) / (1 + 0.01 * tax_r)));
//                wsmoney.setText("无税金额：" + df.format((hsp * nber) / (1 + 0.01 * tax_r)));
//                tax.setText("税额：" + df.format((hsp * nber) - (hsp * nber) / (1 + 0.01 * tax_r)));
//
//            }
//        } else {
//            switch (i % 4) {
////                case 0:
////                    wsprice.setText("无税单价：12");
////                    wsmoney.setText("无税金额：22");
////                    hsprice.setText("含税单价：18");
////                    hsmoney.setText("含税金额：24");
////                    tax.setText("税额：16");
////                    tax_rate.setText("税率：17%");
////                    tv_number.setText(ActivityAdded.added.listscq.get(item).get("content19"));
////                    break;
////                case 1:
////                    wsprice.setText("无税单价：24");
////                    wsmoney.setText("无税金额：36");
////                    hsprice.setText("含税单价：21");
////                    hsmoney.setText("含税金额：34");
////                    tax.setText("税额：17");
////                    tax_rate.setText("税率：17%");
////                    tv_number.setText(ActivityAdded.added.listscq.get(item).get("content19"));
////                    break;
////                case 2:
////                    wsprice.setText("无税单价：5");
////                    wsmoney.setText("无税金额：14");
////                    hsprice.setText("含税单价：13");
////                    hsmoney.setText("含税金额：25");
////                    tax.setText("税额：8");
////                    tax_rate.setText("税率：17%");
////                    tv_number.setText(ActivityAdded.added.listscq.get(item).get("content19"));
////                    break;
////                case 3:
////                    wsprice.setText("无税单价：35");
////                    wsmoney.setText("无税金额：24");
////                    hsprice.setText("含税单价：15");
////                    hsmoney.setText("含税金额：35");
////                    tax.setText("税额：34");
////                    tax_rate.setText("税率：17%");
////                    tv_number.setText(ActivityAdded.added.listscq.get(item).get("content19"));
////                    break;
//            }
//        }
//    }

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
        return result.getReadInfo().toString();
    }

}
