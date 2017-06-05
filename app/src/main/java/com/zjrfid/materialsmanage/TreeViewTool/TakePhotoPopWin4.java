package com.zjrfid.materialsmanage.TreeViewTool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityGenerateList;
import com.zjrfid.materialsmanage.UI.ActivityGoodsAllocation;
import com.zjrfid.materialsmanage.UI.ActivityGenerateList;
import com.zjrfid.materialsmanage.UI.ActivityGoodsAllocationRFIDBind;
import com.zjrfid.materialsmanage.UI.ActivityMaterialsInBound;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocationRfid;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;

import org.apache.http.Header;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class TakePhotoPopWin4 extends PopupWindow {
    private Context mContext;
    private View view;
    private ImageButton plus, minus;
    public EditText et_remark, et_amount, et_hanshui, et_shuilv, et_batch;
    public TextView tv_huowei;
    private int i = 0;
    private LinearLayout lineone, linetwo;
    public static TakePhotoPopWin4 instance;
    public int j;
    private TextView wsprice, wsmoney, hsprice, hsmoney, tax, tax_rate, tv_number;
    public TextView wzbm, jbm, flbm, wlmc, mrsl, ggxh, sfzc, mrhw, zjldw, mrck, fzjldw;
    public String wsdj, wsje, hsdj, se;//无税单价、无税金额、含税单价、税额
    public CgoodsAllocationRfid msfi;
    public String hpiguid;
    public String hwbm;
    private int position;

    public TakePhotoPopWin4(final Context mContext, View.OnClickListener itemsOnClick, int flag, int pisition) {
        instance = this;
        this.position = position;
        this.mContext = mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_scan_wz, null);
        plus = (ImageButton) view.findViewById(R.id.plus);
        minus = (ImageButton) view.findViewById(R.id.minus);
        et_remark = (EditText) view.findViewById(R.id.et_remark);
        lineone = (LinearLayout) view.findViewById(R.id.lineone);//包含按钮的布局
        linetwo = (LinearLayout) view.findViewById(R.id.linetwo);
        //
        initView();
        tv_huowei = (TextView) view.findViewById(R.id.tv_huowei);
        et_batch = (EditText) view.findViewById(R.id.et_batch);
        wsprice = (TextView) view.findViewById(R.id.wsprice);
        wsmoney = (TextView) view.findViewById(R.id.wsmoney);
        hsprice = (TextView) view.findViewById(R.id.hsprice);
        hsmoney = (TextView) view.findViewById(R.id.hsmoney);
        tax = (TextView) view.findViewById(R.id.tax);
        tax_rate = (TextView) view.findViewById(R.id.tax_rate);
        tv_number = (TextView) view.findViewById(R.id.tv_number);
        et_amount = (EditText) view.findViewById(R.id.et_amount);
        et_hanshui = (EditText) view.findViewById(R.id.et_hanshui);
        et_hanshui.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_hanshui.getText().toString().indexOf(".") >= 0) {
                    if (et_hanshui.getText().toString().indexOf(".", et_hanshui.getText().toString().indexOf(".") + 1) > 0) {
                        try {
                            Toast.makeText(mContext, "已经输入\".\"不能重复输入", Toast.LENGTH_SHORT).show();
                            et_hanshui.setText(et_hanshui.getText().toString().substring(0, et_hanshui.getText().toString().length() - 1));
                            et_hanshui.setSelection(et_hanshui.getText().toString().length());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_shuilv = (EditText) view.findViewById(R.id.et_shuilv);
        et_shuilv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_shuilv.getText().toString().indexOf(".") >= 0) {
                    if (et_shuilv.getText().toString().indexOf(".", et_shuilv.getText().toString().indexOf(".") + 1) > 0) {
                        try {
                            Toast.makeText(mContext, "已经输入\".\"不能重复输入", Toast.LENGTH_SHORT).show();
                            et_shuilv.setText(et_shuilv.getText().toString().substring(0, et_shuilv.getText().toString().length() - 1));
                            et_shuilv.setSelection(et_shuilv.getText().toString().length());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //点击请选择货位
        tv_huowei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGenerateList.generateList, ActivityGoodsAllocation.class);
//                intent.putExtra("cwhname", ActivityGenerateList.generateList.tv_warehouse.getText().toString());//仓库名称
//                intent.putExtra("cwhcode", ActivityGenerateList.generateList.cwhcode2);//仓库编码
                mContext.startActivity(intent);
            }
        });

        //点击切换数字键盘
//        et_shuilv.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //标志位判断:设置一个标志位，如果为1，则说明是点修改进入该页面，显示添加和取消按钮
        if (flag == 1) {
            view.findViewById(R.id.cancel).setVisibility(View.GONE);
            lineone.setVisibility(View.GONE);
            linetwo.setVisibility(View.VISIBLE);
        }


        //点击加号
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count(0);
            }
        });
        //点击减号
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count(1);
            }
        });
        //点击叉号
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有数据，则在退出时提示，否则直接退出
                if (et_batch.getText().toString().equals("") && et_amount.getText().toString().equals("") &&
                        et_hanshui.getText().toString().equals("") && et_shuilv.getText().toString().equals("") &&
                        tv_huowei.getText().equals("点击选择货位")) {
                    dismiss();
                } else {
                    //点击弹出对话框
                    new AlertDialog.Builder(mContext).setTitle("温馨提示").setMessage("您有数据未填写完，是否退出？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dismiss();
                                }
                            })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

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
                        public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                            Gson gson = new Gson();
                            msfi = gson.fromJson(rawResponse, CgoodsAllocationRfid.class);
                            if (msfi.getJsonData().size() != 0) {
                                //如果扫出来的物资编码和获取到的物资编码相同才能执行下一步操作
//                                if (ActivityGenerateList.generateList.listscq.get(0).get("content1").equals(msfi.getJsonData().get(0).getCINVCODE())) {
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
                                    tv_huowei.setText(msfi.getJsonData().get(0).getCPARENTID());//将默认货位传进去
                                    hwbm = msfi.getJsonData().get(0).getCPOSCODE();//货位编码
                                    hpiguid = msfi.getJsonData().get(0).getHPIGUID();//物资主键
//                                } else {
//                                    Toast.makeText(mContext, "扫描有误，请重新扫描", Toast.LENGTH_SHORT).show();
//                                }

                                et_shuilv.setText(0.01 * Double.parseDouble(msfi.getJsonData().get(0).getFTAXRATE()) + "");//税率,在这里写成小数的形式
                            } else {
                                Toast.makeText(mContext, "此标签未绑定", Toast.LENGTH_SHORT).show();
                                //跳转到绑定界面做绑定
                                Intent intent = new Intent(mContext, ActivityGoodsAllocationRFIDBind.class);
                                intent.putExtra("flag", 1);
                                mContext.startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                            Toast.makeText(mContext, "扫描失败", Toast.LENGTH_SHORT).show();
                        }

                    });

                }
            }
        });
        //点击添加
        view.findViewById(R.id.tj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityGenerateList.iAdapter.setNewListIndex();
                //判断数据是否填写完整
                if (et_amount.getText().toString().equals("") ||
                        et_hanshui.getText().toString().equals("") || et_shuilv.getText().toString().equals("") ||
                        tv_huowei.getText().equals("点击选择货位")) {
                    Toast.makeText(mContext, "请将数据填写完整", Toast.LENGTH_SHORT).show();
                } else if (jbm.getText().toString().equals("")) {
                    Toast.makeText(mContext, "请扫描", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityGenerateList.iAdapter.changeFlag(1);
                    ActivityGenerateList.generateList.map = new HashMap<String, String>();
                    ActivityGenerateList.generateList.map.put("content1", wzbm.getText().toString());//物资编码
                    ActivityGenerateList.generateList.map.put("content2", jbm.getText().toString());//旧编码
                    ActivityGenerateList.generateList.map.put("content3", flbm.getText().toString());//分类编码
                    ActivityGenerateList.generateList.map.put("content4", wlmc.getText().toString());//物料名称
                    ActivityGenerateList.generateList.map.put("content5", mrsl.getText().toString());//默认税率
                    ActivityGenerateList.generateList.map.put("content6", ggxh.getText().toString());//规格型号
                    ActivityGenerateList.generateList.map.put("content7", sfzc.getText().toString());//是否资产
                    ActivityGenerateList.generateList.map.put("content8", mrhw.getText().toString());//默认货位
                    ActivityGenerateList.generateList.map.put("content9", zjldw.getText().toString());//主计量单位
                    ActivityGenerateList.generateList.map.put("content10", mrck.getText().toString());//默认仓库
                    ActivityGenerateList.generateList.map.put("content11", fzjldw.getText().toString());//辅助计量单位
                    ActivityGenerateList.generateList.map.put("content12", wsdj);//无税单价
                    ActivityGenerateList.generateList.map.put("content13", wsje);//无税金额
                    ActivityGenerateList.generateList.map.put("content14", hsdj);//含税单价
                    ActivityGenerateList.generateList.map.put("content15", et_hanshui.getText().toString());//含税金额
                    ActivityGenerateList.generateList.map.put("content16", et_shuilv.getText().toString());//税率
                    ActivityGenerateList.generateList.map.put("content17", se);//税额
                    ActivityGenerateList.generateList.map.put("content18", et_batch.getText().toString());//批号
                    ActivityGenerateList.generateList.map.put("content19", et_amount.getText().toString());//数量
                    ActivityGenerateList.generateList.map.put("content20", tv_huowei.getText().toString());//货位
                    ActivityGenerateList.generateList.map.put("content21", et_remark.getText().toString());//备注
                    ActivityGenerateList.generateList.map.put("content22", j + "");
                    ActivityGenerateList.generateList.map.put("content23", hwbm);//货位编码
                    ActivityGenerateList.generateList.map.put("content24", hpiguid);//物资主键
                    ActivityGenerateList.generateList.map.put("delFlag", "0");
                    ActivityGenerateList.generateList.map.put("flag", "false");
                    ActivityGenerateList.generateList.map.put("content27", "change");
                    //删除原有那条
                    ActivityGenerateList.generateList.listscq.remove(position);
                    //在原有位置上增加达到替换目的
                    ActivityGenerateList.generateList.listscq.add(ActivityGenerateList.generateList.map);
                    ActivityGenerateList.dataChanged();
                    //隐藏布局
                    linetwo.setVisibility(View.GONE);
                    lineone.setVisibility(View.VISIBLE);
                    //清空数据
                    wzbm.setText("");
                    jbm.setText("");
                    flbm.setText("");
                    wlmc.setText("");
                    mrsl.setText("");
                    ggxh.setText("");
                    sfzc.setText("");
                    mrhw.setText("");
                    zjldw.setText("");
                    mrck.setText("");
                    fzjldw.setText("");

                    et_batch.setText("");
                    et_remark.setText("");
                    et_amount.setText("");
                    et_hanshui.setText("");
                    et_shuilv.setText("");
                    tv_huowei.setText("点击选择货位");
                    i = 0;
                    wsmoney.setText("无税金额：");
                    hsmoney.setText("含税金额：");
                    tax.setText("税额：");
                    wsprice.setText("无税单价：");
                    hsprice.setText("含税单价：");
                    tax_rate.setText("税率：");
                    tv_number.setText("数量：");
                    ActivityGenerateList.generateList.lv_create.setSelection(ActivityGenerateList.generateList.listscq.size() - 1);
//                    ActivityGenerateList.iAdapter.setNewItemBackground(ActivityGenerateList.generateList.listscq.size() - 1, true);
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
        //数量的内容变化监听
        et_amount.addTextChangedListener(new TextWatcher() {

            @Override//内容变化前
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override//内容变化中
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override//内容变化后
            public void afterTextChanged(Editable s) {
                //如果数量和含税金额都不为空，则计算
                calculate();
            }
        });

        //含税金额的内容变化监听
        et_hanshui.addTextChangedListener(new TextWatcher() {
            @Override//内容变化前
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override//内容变化中
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override//内容变化后
            public void afterTextChanged(Editable s) {
                //如果数量和含税金额都不为空，则计算
                calculate();
            }
        });
        //税率的内容变化监听
        et_shuilv.addTextChangedListener(new TextWatcher() {
            @Override//内容变化前
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override//内容变化中
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override//内容变化后
            public void afterTextChanged(Editable s) {
                //如果数量和含税金额都不为空，则计算
                calculate();
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
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置弹出窗体的背景
//        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    //数量计数
    public void count(int j) {
        //如果j=0，就做加法操作
        if (j == 0) {
            if (et_amount.getText().toString().equals("")) {
                i += 1;
            } else {
                i = Integer.parseInt(et_amount.getText().toString());
                i += 1;
            }
        } else {
            if (i != 0) {
                if (et_amount.getText().toString().equals("")) {
                    i -= 1;
                } else {
                    i = Integer.parseInt(et_amount.getText().toString());
                    i -= 1;
                }
            }
        }
        try {
            et_amount.setText(i + "");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //计算显示数据
    public void showdata() {
        DecimalFormat df = new DecimalFormat("0.000000 ");//小数点后保留6位小数
        wsmoney.setText("无税金额：" + df.format(Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString()))));
        hsmoney.setText("含税金额：" + et_hanshui.getText().toString());
        tax.setText("税额：" + df.format(Double.parseDouble(et_hanshui.getText().toString()) - Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString()))));
        wsprice.setText("无税单价：" + df.format(Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString())) / Double.parseDouble(et_amount.getText().toString())));
        hsprice.setText("含税单价：" + df.format(Double.parseDouble(et_hanshui.getText().toString()) / Double.parseDouble(et_amount.getText().toString())));
        tax_rate.setText("税率：" + et_shuilv.getText().toString());
        tv_number.setText("数量：" + et_amount.getText().toString());
        //无税单价、无税金额、含税单价、税额
        wsdj = df.format(Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString())) / Double.parseDouble(et_amount.getText().toString()));
        wsje = df.format(Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString())));
        hsdj = df.format(Double.parseDouble(et_hanshui.getText().toString()) / Double.parseDouble(et_amount.getText().toString()));
        se = df.format(Double.parseDouble(et_hanshui.getText().toString()) - Double.parseDouble(et_hanshui.getText().toString()) / (1 + Double.parseDouble(et_shuilv.getText().toString())));
    }

    //计算
    public void calculate() {
        try {
            if (!et_amount.getText().toString().equals("") && !et_hanshui.getText().toString().equals("")) {
                DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
                hsprice.setText("含税单价：" + df.format(Double.parseDouble(et_hanshui.getText().toString()) / Double.parseDouble(et_amount.getText().toString())));
            }
            if (!et_amount.getText().toString().equals("") && !et_hanshui.getText().toString().equals("") && !et_shuilv.getText().toString().equals("")) {
                showdata();
            }
            if (et_amount.getText().toString().equals("") || et_hanshui.getText().toString().equals("") || et_shuilv.getText().toString().equals("")) {
                wsmoney.setText("无税金额：");
                hsmoney.setText("含税金额：");
                tax.setText("税额：");
                wsprice.setText("无税单价：");
                hsprice.setText("含税单价：");
                tax_rate.setText("税率：");
                tv_number.setText("数量：");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "出错", Toast.LENGTH_SHORT).show();
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
        return result.getReadInfo().toString();
    }

}
