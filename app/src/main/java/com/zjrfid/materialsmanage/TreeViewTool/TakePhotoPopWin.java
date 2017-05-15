package com.zjrfid.materialsmanage.TreeViewTool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.zjrfid.materialsmanage.TreeViewWzrk.MainActivity;
import com.zjrfid.materialsmanage.UI.ActivityGoodsAllocation;
import com.zjrfid.materialsmanage.UI.ActivityMaterialsInBound;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;

import org.apache.http.Header;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class TakePhotoPopWin extends PopupWindow {
    private Context mContext;
    private View view;
    private ImageButton plus, minus;
    public EditText et_remark, et_amount, et_hanshui, et_shuilv, et_batch;
    public TextView tv_huowei;
    private int i = 0;
    private LinearLayout lineone, linetwo;
    public static TakePhotoPopWin instance;
    public int j;
    private TextView wsprice, wsmoney, hsprice, hsmoney, tax, tax_rate, tv_number;
    public TextView wzbm, jbm, flbm, wlmc, mrsl, ggxh, sfzc, mrhw, zjldw, mrck, fzjldw;
    public String wsdj, wsje, hsdj, se;//无税单价、无税金额、含税单价、税额
    public MaterialSpecificFilesInfo msfi;
    public String hpiguid;
    public String hwbm;

    public TakePhotoPopWin(final Context mContext, View.OnClickListener itemsOnClick, int flag) {
        instance = this;
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
                Intent intent=new Intent(ActivityMaterialsInBound.materialsInBound, ActivityGoodsAllocation.class);
                intent.putExtra("cwhname",ActivityMaterialsInBound.materialsInBound.tv_warehouse.getText().toString());//仓库名称
                intent.putExtra("cwhcode",ActivityMaterialsInBound.materialsInBound.cwhcode2);//仓库编码
                mContext.startActivity(intent);
            }
        });

        //点击切换数字键盘
//        et_shuilv.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //标志位判断:设置一个标志位，如果为1，则说明是点修改进入该页面，显示添加和取消按钮
        if (flag == 1) {
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
                if (getRfid().equals("")) {
                    Toast.makeText(mContext, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
                } else {
                    lineone.setVisibility(View.GONE);//隐藏布局
                    linetwo.setVisibility(View.VISIBLE);//显示布局
                    RequestParams params = new RequestParams();
                    params.put("rfid", getRfid());
                    //物资档案接口
                    HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=&oldcord=&cinvcode=", params, new BaseHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                            Gson gson = new Gson();
                            msfi = gson.fromJson(rawResponse, MaterialSpecificFilesInfo.class);
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
                                tv_huowei.setText(msfi.getJsonData().getList().get(0).getCPARENTID());//将默认货位传进去
                                hwbm = msfi.getJsonData().getList().get(0).getCPOSCODE();//货位编码
                                hpiguid=msfi.getJsonData().getList().get(0).getHPIGUID();//物资主键

//                        number.setText(ed_batch.getText().toString());//批号
                                et_shuilv.setText(0.01 * Double.parseDouble(msfi.getJsonData().getList().get(0).getFTAXRATE()) + "");//税率,在这里写成小数的形式
                            } else {
                                Toast.makeText(mContext, "此标签未绑定", Toast.LENGTH_SHORT).show();
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
                //判断数据是否填写完整
                if (et_amount.getText().toString().equals("") ||
                        et_hanshui.getText().toString().equals("") || et_shuilv.getText().toString().equals("") ||
                        tv_huowei.getText().equals("点击选择货位")) {
                    Toast.makeText(mContext, "请将数据填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityMaterialsInBound.materialsInBound.map2 = new HashMap<String, String>();
                    ActivityMaterialsInBound.materialsInBound.map2.put("content1", wzbm.getText().toString());//物资编码
                    ActivityMaterialsInBound.materialsInBound.map2.put("content2", jbm.getText().toString());//旧编码
                    ActivityMaterialsInBound.materialsInBound.map2.put("content3", flbm.getText().toString());//分类编码
                    ActivityMaterialsInBound.materialsInBound.map2.put("content4", wlmc.getText().toString());//物料名称
                    ActivityMaterialsInBound.materialsInBound.map2.put("content5", mrsl.getText().toString());//默认税率
                    ActivityMaterialsInBound.materialsInBound.map2.put("content6", ggxh.getText().toString());//规格型号
                    ActivityMaterialsInBound.materialsInBound.map2.put("content7", sfzc.getText().toString());//是否资产
                    ActivityMaterialsInBound.materialsInBound.map2.put("content8", mrhw.getText().toString());//默认货位
                    ActivityMaterialsInBound.materialsInBound.map2.put("content9", zjldw.getText().toString());//主计量单位
                    ActivityMaterialsInBound.materialsInBound.map2.put("content10", mrck.getText().toString());//默认仓库
                    ActivityMaterialsInBound.materialsInBound.map2.put("content11", fzjldw.getText().toString());//辅助计量单位
                    ActivityMaterialsInBound.materialsInBound.map2.put("content12", wsdj);//无税单价
                    ActivityMaterialsInBound.materialsInBound.map2.put("content13", wsje);//无税金额
                    ActivityMaterialsInBound.materialsInBound.map2.put("content14", hsdj);//含税单价
                    ActivityMaterialsInBound.materialsInBound.map2.put("content15", et_hanshui.getText().toString());//含税金额
                    ActivityMaterialsInBound.materialsInBound.map2.put("content16", et_shuilv.getText().toString());//税率
                    ActivityMaterialsInBound.materialsInBound.map2.put("content17", se);//税额
                    ActivityMaterialsInBound.materialsInBound.map2.put("content18", et_batch.getText().toString());//批号
                    ActivityMaterialsInBound.materialsInBound.map2.put("content19", et_amount.getText().toString());//数量
                    ActivityMaterialsInBound.materialsInBound.map2.put("content20", tv_huowei.getText().toString());//货位
                    ActivityMaterialsInBound.materialsInBound.map2.put("content21", et_remark.getText().toString());//备注
                    ActivityMaterialsInBound.materialsInBound.map2.put("content22", j + "");
                    ActivityMaterialsInBound.materialsInBound.map2.put("content23", hwbm);//货位编码
                    ActivityMaterialsInBound.materialsInBound.map2.put("content24", hpiguid);//物资主键
                    ActivityMaterialsInBound.materialsInBound.map2.put("delFlag", "0");
                    ActivityMaterialsInBound.materialsInBound.map2.put("flag", "false");

                    //这里需要判断，只有当修改是才能做删除操作
                    if (ActivityMaterialsInBound.materialsInBound.m_jt == 1) {
                        //显示总价
                        ActivityMaterialsInBound.materialsInBound.dbl_number = ActivityMaterialsInBound.materialsInBound.dbl_number - Double.parseDouble(ActivityMaterialsInBound.materialsInBound.listscq.get(ActivityMaterialsInBound.materialsInBound.item).get("content15"));
                        DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
                        ActivityMaterialsInBound.materialsInBound.tv_total_prices.setText(df.format(ActivityMaterialsInBound.materialsInBound.dbl_number) + "");
                        //计算数量
                        ActivityMaterialsInBound.materialsInBound.amount = ActivityMaterialsInBound.materialsInBound.amount - Integer.parseInt(ActivityMaterialsInBound.materialsInBound.listscq.get(ActivityMaterialsInBound.materialsInBound.item).get("content19"));
                        ActivityMaterialsInBound.materialsInBound.tv_amount.setText(ActivityMaterialsInBound.materialsInBound.amount + "");
                        //删除原有那条
                        ActivityMaterialsInBound.materialsInBound.listscq.remove(ActivityMaterialsInBound.materialsInBound.item);
                        //在原有位置上增加达到替换目的
                        ActivityMaterialsInBound.materialsInBound.listscq.add(ActivityMaterialsInBound.materialsInBound.item, ActivityMaterialsInBound.materialsInBound.map2);
                    } else {//新增
                        ActivityMaterialsInBound.materialsInBound.list_HprGuidCh.add("");
                        ActivityMaterialsInBound.materialsInBound.listscq.add(ActivityMaterialsInBound.materialsInBound.map2);
                    }
                    ActivityMaterialsInBound.dataChanged();
                    try {
                        //显示总价
                        ActivityMaterialsInBound.materialsInBound.dbl_number = ActivityMaterialsInBound.materialsInBound.dbl_number + Double.parseDouble(et_hanshui.getText().toString());
                        DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
                        ActivityMaterialsInBound.materialsInBound.tv_total_prices.setText(df.format(ActivityMaterialsInBound.materialsInBound.dbl_number) + "");
                        //计算数量
                        ActivityMaterialsInBound.materialsInBound.amount = ActivityMaterialsInBound.materialsInBound.amount + Integer.parseInt(et_amount.getText().toString());
                        ActivityMaterialsInBound.materialsInBound.tv_amount.setText(ActivityMaterialsInBound.materialsInBound.amount + "");
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(mContext, "出错", Toast.LENGTH_SHORT).show();
                        //删除原有那条
                        ActivityMaterialsInBound.materialsInBound.listscq.remove(ActivityMaterialsInBound.materialsInBound.item);
                        dismiss();
                    }
                    //因为有数据，所以让保存按钮可以点击
                    ActivityMaterialsInBound.materialsInBound.btn_save.setClickable(true);
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
                    ActivityMaterialsInBound.materialsInBound.i = 0;//记录清零，包括删除和修改的记录
                }
                //如果是修改，点击添加就让它隐藏
                if (ActivityMaterialsInBound.materialsInBound.m_jt == 1) {
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
