package com.zjrfid.materialsmanage.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin;
import com.zjrfid.materialsmanage.acdbentity.GodownEntryInfo;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo2;
import com.zjrfid.materialsmanage.acdbentity.TableBody;
import com.zjrfid.materialsmanage.acdbentity.UPloadGodownEntryInfo;
import com.zjrfid.materialsmanage.acdbentity.UploadTableBody;
import com.zjrfid.materialsmanage.adapter.IAdapter;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 物资入库
 */
public class ActivityMaterialsInBound extends AppCompatActivity {
    public String warehouse = "";
    public String hpwGuid = "";
    public String cwhcode2 = "";

    public static ActivityMaterialsInBound materialsInBound;
    private RelativeLayout relative_rukudan;
    public ListView lv_create;
    private boolean bln_is = true;
    private LinearLayout linear_rukudan;
    private ImageView img_direction;
    private Spinner spin_people, spin_raw_data1, spin_raw_data2;
    private ArrayAdapter<String> adapter;
    private List<String> list_raw_data = new ArrayList<>();
    public TextView tv_warehouse, tv_supplier;
    public static IAdapter iAdapter;

    public HashMap<String, String> map2;
    private LinearLayout line1, line2, line3;
    public Double dbl_number = 0.0;//总价
    public TextView tv_total_prices;//总价
    public Button btn_save;
    TakePhotoPopWin takePhotoPopWin;
    public int i = 0;
    public int item;
    public static List<String> list_info = new ArrayList<>();
    public static String str_amount, str_hanshui, str_shuilv;
    private TextView tv_documentNO, tv_date_time, tv_acquisition_staff, tv_section, tv_auditor,
            tv_audit_date, tv_documentMaker, tv_voucher_data;
    public TextView tv_amount;
    GodownEntryInfo gei;
    private EditText et_cicode1, et_cicode2, et_remark;
    Intent it;
    TableBody tableBody;
    MaterialSpecificFilesInfo2 msfi = new MaterialSpecificFilesInfo2();
    private String json_header;//表头json
    private JSONArray jsonArray;//表体json
    private int flag = 0;//标记
    public String cvencode;
    public String hpsnguid;
    private int sign2 = 0;
    private String hprguid = "";

    public ArrayList<HashMap<String, String>> listscq;
    public List<String> list_HprGuidCh = new ArrayList<>();


    public int m_jt = 0;
    public int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_in_bound);
        //
        materialsInBound = this;
        initView();//初始化视图
        initSpinner();//初始化Spinner
        SysApplication.getInstance().addActivity(this);

        it = getIntent();
        flag = it.getIntExtra("get", 0);
        if (flag == 1) {//等于1表示是从物资入库点击列表进入此页面的
            addData();
            linear_rukudan.setVisibility(View.GONE);
            hprguid = getIntent().getStringExtra("hprGuid");
            clickEnable(false);//入库单中控件无法点击
        } else {
            //显示入库单布局的时候加载信息
            tv_date_time.setText(getTime());//显示当前时间
            tv_acquisition_staff.setText(ActivityLogin.login.getJobnumber()+"|"+ActivityLogin.login.getFullname());
            tv_section.setText(ActivityLogin.login.getOrgname());
            spin_people.setSelection(2);//入库类别
            spin_raw_data1.setSelection(0);//原始单据类型1
            spin_raw_data2.setSelection(0);//原始单据类型2
            bln_is = false;
        }
    }

    //初始化视图
    public void initView() {
        relative_rukudan = (RelativeLayout) findViewById(R.id.relative_rukudan);
        lv_create = (ListView) findViewById(R.id.lv_create);
        linear_rukudan = (LinearLayout) findViewById(R.id.linear_rukudan);
        img_direction = (ImageView) findViewById(R.id.img_direction);
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);
        tv_supplier = (TextView) findViewById(R.id.tv_supplier);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        tv_total_prices = (TextView) findViewById(R.id.tv_total_prices);
        listscq = new ArrayList<HashMap<String, String>>();
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_documentNO = (TextView) findViewById(R.id.tv_documentNO);//入库单据号
        tv_date_time = (TextView) findViewById(R.id.tv_date_time);//入库日期
        tv_acquisition_staff = (TextView) findViewById(R.id.tv_acquisition_staff);//领料人员
        tv_section = (TextView) findViewById(R.id.tv_section);//部门
        tv_auditor = (TextView) findViewById(R.id.tv_auditor);//审核人
        tv_audit_date = (TextView) findViewById(R.id.tv_audit_date);//审核日期
        tv_documentMaker = (TextView) findViewById(R.id.tv_documentMaker);//制单人
        tv_voucher_data = (TextView) findViewById(R.id.tv_voucher_data);//制单日期
        et_cicode1 = (EditText) findViewById(R.id.et_cicode1);
        et_cicode2 = (EditText) findViewById(R.id.et_cicode2);
        et_remark = (EditText) findViewById(R.id.et_remark);
        tv_amount = (TextView) findViewById(R.id.tv_amount);

        iAdapter = new IAdapter(ActivityMaterialsInBound.this, listscq);
        lv_create.setAdapter(iAdapter);
        //点击入库单
        relative_rukudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bln_is) {
                    //显示入库单布局
                    linear_rukudan.setVisibility(View.VISIBLE);
                    img_direction.setImageResource(R.drawable.up);
                    bln_is = false;
                } else {
                    //隐藏入库单布局
                    linear_rukudan.setVisibility(View.GONE);
                    img_direction.setImageResource(R.drawable.down);
                    bln_is = true;
                }
            }
        });
        //点击选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMaterialsInBound.this, Activity_TreeView.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
            }
        });
        //点击供货单位
        tv_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMaterialsInBound.this, Activity_TreeView.class);
                intent.putExtra("flag", 13);
                startActivity(intent);
            }
        });
        // 绑定listView的监听器
        lv_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                IAdapter.ViewHold holder = (IAdapter.ViewHold) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    listscq.get(arg2).put("flag", "true");
                    item = arg2;//把选中listview的项告诉item
                    i += 1;
                } else {
                    listscq.get(arg2).put("flag", "false");
                    i -= 1;
                }
            }
        });
    }

    //初始化Spinner
    public void initSpinner() {
        spin_people = (Spinner) findViewById(R.id.spin_people);
        spin_raw_data1 = (Spinner) findViewById(R.id.spin_raw_data1);
        spin_raw_data2 = (Spinner) findViewById(R.id.spin_raw_data2);

        list_raw_data.add("发票");
        list_raw_data.add("送货单");
        //入库类别
        adapter = new ArrayAdapter<String>(ActivityMaterialsInBound.this, android.R.layout.simple_spinner_item, ActivityGoodsReceipt.goodsReceipt.list_people);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_people.setAdapter(adapter);

        //原始数据
        adapter = new ArrayAdapter<String>(ActivityMaterialsInBound.this, android.R.layout.simple_spinner_item, list_raw_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_raw_data1.setAdapter(adapter);
        //原始数据2
        adapter = new ArrayAdapter<String>(ActivityMaterialsInBound.this, android.R.layout.simple_spinner_item, list_raw_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_raw_data2.setAdapter(adapter);
    }

    //点击入库单中的确定
    public void confirm(View view) {
        if (tv_warehouse.getText().toString().equals("请选择仓库") || tv_supplier.getText().toString().equals("请供货单位")) {
            Toast.makeText(ActivityMaterialsInBound.this, "请把仓库和供货单位填写完整", Toast.LENGTH_SHORT).show();
        } else {
            linear_rukudan.setVisibility(View.GONE);//隐藏布局
        }
    }

    //json表头
    public UPloadGodownEntryInfo getRkHeaderJson() {
        UPloadGodownEntryInfo godownEntryInfo = new UPloadGodownEntryInfo();
        try {
            //需要上传的入库单信息实体类
            if (flag == 1) {//如果等于1说明是从物资入库单点击进入此页面，这时gei才有值
                godownEntryInfo.setHprGuid(gei.getHPRGUID());//主键，如果主键为空就是新增，不为空就是修改
                godownEntryInfo.setHpwGuid(gei.getHPWGUID());//仓库主键
                godownEntryInfo.setCpersoncode(gei.getCPERSONCODE());
                godownEntryInfo.setCdepcode(gei.getCDEPCODE());
//                godownEntryInfo.setCvencode();//供货单位编码,
//                godownEntryInfo.setHpsnGuid();//供应商主键,
            } else {
                godownEntryInfo.setHprGuid("");//主键，如果主键为空就是新增，不为空就是修改
                godownEntryInfo.setHpwGuid("");//仓库主键
                godownEntryInfo.setCpersoncode("");
                godownEntryInfo.setCdepcode("");
            }
            godownEntryInfo.setCvencode(cvencode);//供货单位编码
            godownEntryInfo.setHpsnGuid(hpsnguid);//供应商主键
            godownEntryInfo.setCwhcode(cwhcode2);//仓库编码
            godownEntryInfo.setCvenname(tv_supplier.getText().toString());//供货单位名称
            godownEntryInfo.setNavTabId("hpRdrecord");
            godownEntryInfo.setCallbackType("closeCurrent");
            godownEntryInfo.setCallBackMethod("hpRdrecordSearch");
            godownEntryInfo.setCvouchtype("01");//
            godownEntryInfo.setBrdflag("0");//
            godownEntryInfo.setParentCode("01");
            godownEntryInfo.setCcode(tv_documentNO.getText().toString());//入库单据号
            godownEntryInfo.setDkeepdate(tv_date_time.getText().toString());//入库日期
            godownEntryInfo.setCwhname(tv_warehouse.getText().toString());//仓库
            godownEntryInfo.setIassets("0");//是否资产
            spin_people.getSelectedItem().toString().indexOf(" ");

            godownEntryInfo.setCrdcode_("");//
            godownEntryInfo.setCrdcode__("");//
            godownEntryInfo.setCrdcode(spin_people.getSelectedItem().toString().substring(0, spin_people.getSelectedItem().toString().indexOf(" ")));//入库类别编码
            godownEntryInfo.setCrdname(spin_people.getSelectedItem().toString().substring(spin_people.getSelectedItem().toString().indexOf(" ") + 1));//入库类别名字
            godownEntryInfo.setCpersonname(tv_acquisition_staff.getText().toString());//收料员
//        godownEntryInfo.setCRDCODE(spin_people.getSelectedItem().toString());//入库类别
            godownEntryInfo.setOrgname(tv_section.getText().toString());//部门
            if (tv_supplier.getText().toString().equals("请供货单位")) {
                godownEntryInfo.setCvenname("");//供货单位
            } else {
                godownEntryInfo.setCvenname(tv_supplier.getText().toString());//供货单位
            }
            godownEntryInfo.setCvouchtype1(spin_raw_data1.getSelectedItem().toString());//原始单据类型1
            godownEntryInfo.setCicode1(et_cicode1.getText().toString());//单据号1
            godownEntryInfo.setCvouchtype2(spin_raw_data2.getSelectedItem().toString());//原始单据类型2
            godownEntryInfo.setCicode2(et_cicode2.getText().toString());//单据号2
            godownEntryInfo.setCmemo(et_remark.getText().toString());//备注
            godownEntryInfo.setCmaker(tv_documentMaker.getText().toString());//制单人
            godownEntryInfo.setChandler(tv_auditor.getText().toString());//审核人
            godownEntryInfo.setCreateDt(tv_voucher_data.getText().toString());//制单日期
            godownEntryInfo.setDveridaate(tv_audit_date.getText().toString());//审核日期
            Gson gson = new Gson();
            json_header = gson.toJson(godownEntryInfo);
            System.out.println("表头" + json_header);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错了", Toast.LENGTH_SHORT).show();
        }
        return godownEntryInfo;
    }

    //点击回退
    public void back(View view) {
        //清空集合
        list_info.clear();
        finish();
        //如果保存了就让盘点单刷新
        if (sign2 == 1) {
            ActivityGoodsReceipt.goodsReceipt.onRefresh();
            ActivityGoodsReceipt.goodsReceipt.showLayout();
        }
    }

    //添加数据
    public void addData() {
        String param = it.getStringExtra("hprGuid");
        //从服务器获取入库单信息(表头)
        getGodownEntryInfo(param);
        //获取物资具体档案信息
        getMaterialSpecificFilesInfo(param);
    }

    //点击单条添加
    public void dataShow(View view) {
        if (!tv_warehouse.getText().toString().equals("请选择仓库")) {
            m_jt = 0;//设置为0
            takePhotoPopWin = new TakePhotoPopWin(this, onClickListener, 0);
            takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
        } else {
            Toast.makeText(ActivityMaterialsInBound.this, "请填写仓库", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.tv_huowei:
//                    System.out.println("fewafwaefwea--------------------------------------");
//                    break;
            }
        }
    };

    //计算总价
    public void price(int i) {
        //0表示加，1表示减
        if (i == 0) {
            dbl_number = dbl_number + Double.parseDouble(listscq.get(item).get("content15"));
            amount = amount + Integer.parseInt(listscq.get(item).get("content19"));
        } else if (i == 1) {
            dbl_number = dbl_number - Double.parseDouble(listscq.get(item).get("content15"));
            amount = amount - Integer.parseInt(listscq.get(item).get("content19"));
        }
        DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
        tv_total_prices.setText(df.format(dbl_number) + "");
        tv_amount.setText(amount + "");
    }

    // 刷新listview
    public static void dataChanged() {
        // 通知listView刷新
        iAdapter.notifyDataSetChanged();
    }

    @Override//销毁静态变量
    protected void onDestroy() {
        super.onDestroy();
        str_amount = null;
        str_hanshui = null;
        str_shuilv = null;
    }

    //点击修改
    public void alter(View view) {
        if (i > 1) {
            Toast.makeText(ActivityMaterialsInBound.this, "只能修改一条", Toast.LENGTH_SHORT).show();
        } else if (i == 0) {
            Toast.makeText(ActivityMaterialsInBound.this, "请选择一项修改", Toast.LENGTH_SHORT).show();
        } else {
            takePhotoPopWin = new TakePhotoPopWin(this, onClickListener, 1);
            takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
            m_jt = 1;//只有单修改是才设置标志位
            TakePhotoPopWin.instance.wzbm.setText(listscq.get(item).get("content1"));//物资编码
            TakePhotoPopWin.instance.jbm.setText(listscq.get(item).get("content2"));//旧编码
            TakePhotoPopWin.instance.flbm.setText(listscq.get(item).get("content3"));//分类编码
            TakePhotoPopWin.instance.wlmc.setText(listscq.get(item).get("content4"));//物料名称
            TakePhotoPopWin.instance.mrsl.setText(listscq.get(item).get("content5"));//默认税率
            TakePhotoPopWin.instance.ggxh.setText(listscq.get(item).get("content6"));//规格型号
            TakePhotoPopWin.instance.sfzc.setText(listscq.get(item).get("content7"));//是否资产
            TakePhotoPopWin.instance.mrhw.setText(listscq.get(item).get("content8"));//默认货位
            TakePhotoPopWin.instance.zjldw.setText(listscq.get(item).get("content9"));//主计量单位
            TakePhotoPopWin.instance.mrck.setText(listscq.get(item).get("content10"));//默认仓库
            TakePhotoPopWin.instance.fzjldw.setText(listscq.get(item).get("content11"));//辅助计量单位
            TakePhotoPopWin.instance.et_batch.setText(listscq.get(item).get("content18"));//批号
            TakePhotoPopWin.instance.et_amount.setText(listscq.get(item).get("content19"));//数量
            TakePhotoPopWin.instance.et_hanshui.setText(listscq.get(item).get("content15"));//含税金额
            TakePhotoPopWin.instance.et_shuilv.setText(listscq.get(item).get("content16"));//税率
            TakePhotoPopWin.instance.tv_huowei.setText(listscq.get(item).get("content20"));//货位名称
            TakePhotoPopWin.instance.et_remark.setText(listscq.get(item).get("content21"));//备注
            TakePhotoPopWin.instance.hpiguid = listscq.get(item).get("content24");//物资主键
            TakePhotoPopWin.instance.hwbm = listscq.get(item).get("content23");//货位编码
        }
    }

    //点击保存
    public void save(View view) {
        try {
            getRkHeaderJson();//json表头
            getTableBodyJson();//将表体转换成json

            if (listscq.size() == 0) {
                Toast.makeText(ActivityMaterialsInBound.this, "无数据", Toast.LENGTH_SHORT).show();
                return;
            }

            RequestParams params = new RequestParams();
            params.put("form", json_header);
            params.put("datas", jsonArray.toString());
            //新增入库单详细信息单条接口
            HttpNetworkRequest.post("goods/rs/hpPutstorage", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Toast.makeText(ActivityMaterialsInBound.this, "保存成功", Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObject = new JSONObject(rawResponse);
                        finish();
                        Intent intent = new Intent(ActivityMaterialsInBound.this, ActivityMaterialsInBound.class);
                        intent.putExtra("get", 1);
                        intent.putExtra("hprGuid", jsonObject.getString("hprGuid"));//把主键传过去
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    Log.i("Failure", "出错了");
                    Toast.makeText(ActivityMaterialsInBound.this, "保存失败", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //点击新增
    public void add(View view) {
        finish();
        startActivity(new Intent(this, ActivityMaterialsInBound.class));
    }

    //点击删行
    public void delete(View view) {
        if (i == 0) {
            Toast.makeText(ActivityMaterialsInBound.this, "请选择一项删除", Toast.LENGTH_SHORT).show();
        } else {
            //减去删掉那行的总数，这一段必须放在前面，如果放在后面就会出现索引越界异常，因为它已经被删除了
            try {
                price(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int del_count = 0;
            for (int m = 0; m < listscq.size(); m++) {
                //如果选中但还未删除
                if (listscq.get(m).get("flag").equals("true") && listscq.get(m).get("delFlag").equals("0")) {
                    listscq.get(m).put("delFlag", "1");
                    del_count++;
                }
            }
            // 通知列表数据修改
            dataChanged();
            i = 0;//记录清零
        }
    }

    //点击修改
    public void revise(View view) {
        if (isChandler()) {
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.GONE);
            //显示扫描物资
            iAdapter.hind(0);//设置标志位，如果为1，怎隐藏复选框
            dataChanged();//刷新适配器
            line3.setVisibility(View.VISIBLE);//显示总数那个布局
            //因为有数据，所以让保存按钮可以点击
            ActivityMaterialsInBound.materialsInBound.btn_save.setClickable(true);
            clickEnable(true);//入库单中控件可以点击
        } else {
            Toast.makeText(ActivityMaterialsInBound.this, "该条已审核，无法修改", Toast.LENGTH_SHORT).show();
        }
    }

    //判断出库审核人是否为空,为空就是true
    public boolean isChandler() {
        boolean is = false;
        if (it.getStringExtra("chandler") == null) {
            is = true;
        }
        return is;
    }

    //点击审核
    public void audit(View view) {
        //点击弹出对话框
        new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("是否通过审核？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //如果未审核通过就执行
                        if (isChandler()) {
                            try {
                                RequestParams params = new RequestParams();
                                params.put("key", "0");//key值写死，虽然不知道为什么，苏州说的
                                params.put("str", hprguid);
                                //审核入库单接口
                                HttpNetworkRequest.post("goods/rs/hpPutstorage/" + hprguid, params, new BaseHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int i, Header[] headers, String s, Object o) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            String str = jsonObject.getString("message");
                                            if (str.equals("success")) {
                                                Toast.makeText(ActivityMaterialsInBound.this, "审核通过", Toast.LENGTH_SHORT).show();
                                                finish();
                                                //刷新入库单中的数据
                                                ActivityGoodsReceipt.goodsReceipt.onRefresh();
                                                ActivityGoodsReceipt.goodsReceipt.showLayout();
                                            } else {
                                                Toast.makeText(ActivityMaterialsInBound.this, "审核失败:" + str, Toast.LENGTH_SHORT).show();
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
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityMaterialsInBound.this, "已审核，无法再次审核", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityMaterialsInBound.this, "放弃审核", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    //给入库单赋值
    public void setGodownEntryInfo(GodownEntryInfo gei) {

        try {
            tv_documentNO.setText(gei.getCCODE());//入库单据号
            tv_date_time.setText(gei.getDKEEPDATE());//入库日期
            tv_warehouse.setText(gei.getCWHNAME());//仓库
            for (int i = 0; i < ActivityGoodsReceipt.goodsReceipt.list_people.size(); i++) {
                if (ActivityGoodsReceipt.goodsReceipt.list_people.get(i).replace(" ", "").replaceAll("\\d+", "").equals(gei.getCRDNAME())) {//去掉数字和空格的字符串再做比较
                    spin_people.setSelection(i);//入库类别
                }
            }
            tv_acquisition_staff.setText(gei.getCPERSONNAME());//收料员
            tv_section.setText(gei.getORGNAME());//部门
            tv_supplier.setText(gei.getCVENNAME());//供货单位
            int j = list_raw_data.indexOf(gei.getCVOUCHTYPE1());
            spin_raw_data1.setSelection(j);//原始单据类型1
            int k = list_raw_data.indexOf(gei.getCVOUCHTYPE2());
            spin_raw_data2.setSelection(k);//原始单据类型2
            et_cicode1.setText(gei.getCICODE1());//单据号1
            et_cicode2.setText(gei.getCICODE2());//单据号2
            tv_auditor.setText(gei.getCHANDLER());//审核人
            tv_audit_date.setText(gei.getDVERIDAATE());//审核日期
            tv_documentMaker.setText(gei.getCMAKER());//制单人
            tv_voucher_data.setText(gei.getCREATEDT());//制单日期
            et_remark.setText(gei.getCMEMO());//备注
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //获取物资档案信息，他是物资入库的一部分数据
    public void getMaterialSpecificFilesInfo(String str_hprGuid) {

        try {
            RequestParams params = new RequestParams();
            params.put("hprGuid", str_hprGuid);
            //入库单详细信息接口表体
            HttpNetworkRequest.get("goods/rs/hpPutstorageCh", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    tableBody = gson.fromJson(rawResponse, TableBody.class);
                    if (tableBody.getStatusCode().equals("200")) {//判断接口请求是否成功
                        for (int i = 0; i < tableBody.getJsonData().getList().size(); i++) {
                            list_HprGuidCh.add(tableBody.getJsonData().getList().get(i).getHPRGUIDCH());
                            String hpiguid = tableBody.getJsonData().getList().get(i).getHPIGUID();
                            //物资档案接口
                            final int finalI = i;
                            HttpNetworkRequest.get("goods/rs/hpInventory/" + hpiguid, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                    Gson gson = new Gson();
                                    msfi = gson.fromJson(rawResponse, MaterialSpecificFilesInfo2.class);
                                    map2 = new HashMap<String, String>();
                                    if (tableBody.getJsonData().getList().get(finalI).getCINVCODE() == null) {
                                        map2.put("content1", "");//物资编码
                                    } else {
                                        map2.put("content1", tableBody.getJsonData().getList().get(finalI).getCINVCODE());//物资编码
                                    }
                                    if (msfi.getOLDCORD() == null) {
                                        map2.put("content2", "");//旧编码
                                    } else {
                                        map2.put("content2", msfi.getOLDCORD());//旧编码
                                    }
                                    if (msfi.getHPICGUID() == null) {
                                        map2.put("content3", "");//分类编码
                                    } else {
                                        map2.put("content3", msfi.getHPICGUID());//分类编码
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCINVNAME() == null) {
                                        map2.put("content4", "");//物资名称
                                    } else {
                                        map2.put("content4", tableBody.getJsonData().getList().get(finalI).getCINVNAME());//物资名称
                                    }
                                    if (msfi.getFTAXRATE() == null) {
                                        map2.put("content5", "");//默认税率
                                    } else {
                                        map2.put("content5", msfi.getFTAXRATE());//默认税率
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCINVSTD() == null) {
                                        map2.put("content6", "");//规格型号
                                    } else {
                                        map2.put("content6", tableBody.getJsonData().getList().get(finalI).getCINVSTD());//规格型号
                                    }
                                    if (msfi.getIASSET() == null) {
                                        map2.put("content7", "");//是否资产
                                    } else {
                                        map2.put("content7", msfi.getIASSET());//是否资产
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCPARENTID() == null) {
                                        map2.put("content8", "");//默认货位
                                    } else {
                                        map2.put("content8", tableBody.getJsonData().getList().get(finalI).getCPARENTID());//默认货位
                                    }
                                    if (msfi.getCWHNAME() == null) {
                                        map2.put("content10", "");//默认仓库
                                    } else {
                                        map2.put("content10", msfi.getCWHNAME());//默认仓库
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCCOMUNITNAME() == null) {
                                        map2.put("content9", "");//主计量单位
                                    } else {
                                        map2.put("content9", tableBody.getJsonData().getList().get(finalI).getCCOMUNITNAME());//主计量单位
                                    }
                                    map2.put("content11", "null");//辅助计量单位
                                    if (tableBody.getJsonData().getList().get(finalI).getFUNITPRICE() == null) {
                                        map2.put("content12", "");//无税单价
                                    } else {
                                        map2.put("content12", tableBody.getJsonData().getList().get(finalI).getFUNITPRICE());//无税单价
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getFMONDEY() == null) {
                                        map2.put("content13", "");//无税金额
                                    } else {
                                        map2.put("content13", tableBody.getJsonData().getList().get(finalI).getFMONDEY());//无税金额
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getFTAXPRICE() == null) {
                                        map2.put("content14", "");//含税单价
                                    } else {
                                        map2.put("content14", tableBody.getJsonData().getList().get(finalI).getFTAXPRICE());//含税单价
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getTAXAMOUNT() == null) {
                                        map2.put("content15", "");//含税金额
                                    } else {
                                        map2.put("content15", tableBody.getJsonData().getList().get(finalI).getTAXAMOUNT());//含税金额
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getFTAXRATE() == null) {
                                        map2.put("content16", "");//税率
                                    } else {
                                        map2.put("content16", tableBody.getJsonData().getList().get(finalI).getFTAXRATE());//税率
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getIPERTAXRATE() == null) {
                                        map2.put("content17", "");//税额
                                    } else {
                                        map2.put("content17", tableBody.getJsonData().getList().get(finalI).getIPERTAXRATE());//税额
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCBATCH() == null) {
                                        map2.put("content18", "");//批号
                                    } else {
                                        map2.put("content18", tableBody.getJsonData().getList().get(finalI).getCBATCH());//批号
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getFQUANTITY() == null) {
                                        map2.put("content19", "");//数量
                                    } else {
                                        map2.put("content19", tableBody.getJsonData().getList().get(finalI).getFQUANTITY());//数量
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCPARENTID() == null) {
                                        map2.put("content20", "");//货位
                                    } else {
                                        map2.put("content20", tableBody.getJsonData().getList().get(finalI).getCPARENTID());//货位
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCDEMO() == null) {
                                        map2.put("content21", "");//备注
                                    } else {
                                        map2.put("content21", tableBody.getJsonData().getList().get(finalI).getCDEMO());//备注
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getCPOSCODE() == null) {
                                        map2.put("content23", "");//货位编码
                                    } else {
                                        map2.put("content23", tableBody.getJsonData().getList().get(finalI).getCPOSCODE());//货位编码
                                    }
                                    if (tableBody.getJsonData().getList().get(finalI).getHPIGUID() == null) {
                                        map2.put("content24", "");//物资主键
                                    } else {
                                        map2.put("content24", tableBody.getJsonData().getList().get(finalI).getHPIGUID());//物资主键
                                    }
                                    map2.put("delFlag", "0");
                                    map2.put("flag", "false");
                                    listscq.add(map2);
                                    iAdapter.notifyDataSetChanged();
                                    //
                                    iAdapter.hind(1);//隐藏复选框
                                    line1.setVisibility(View.GONE);
                                    line2.setVisibility(View.VISIBLE);
                                    line3.setVisibility(View.GONE);
                                    //计算总价
                                    dbl_number += Double.parseDouble(tableBody.getJsonData().getList().get(finalI).getTAXAMOUNT());
                                    tv_total_prices.setText(dbl_number + "");
                                    //计算数量
                                    Log.i("info", "hpiguid:" + tableBody.getJsonData().getList().get(finalI).getFQUANTITY());
                                    amount += Integer.parseInt(tableBody.getJsonData().getList().get(finalI).getFQUANTITY());
                                    tv_amount.setText(amount + "");
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                                    Log.e("error", "网络异常");
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    Log.e("error", "网络异常");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }

    }

    //从服务器获取入库单信息
    public void getGodownEntryInfo(String param) {
        try {
            RequestParams params = new RequestParams();
            params.put("hprGuid", param);
            //入库单信息接口
            HttpNetworkRequest.get("goods/rs/hpPutstorage/" + param, params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    gei = gson.fromJson(rawResponse, GodownEntryInfo.class);
                    cwhcode2 = gei.getCWHCODE();
                    cvencode = gei.getCVENCODE();//供货单位编码
                    hpsnguid = gei.getHPSNGUID();//供应商主键
                    //赋值方法
                    setGodownEntryInfo(gei);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    Log.e("error", "出错鸟");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //将表体类转换成json
    public void getTableBodyJson() {
        UploadTableBody uptableBody = new UploadTableBody();
        Gson gson = new Gson();
        jsonArray = new JSONArray();

        try {
            for (int i = 0; i < listscq.size(); i++) {
                uptableBody.setHpiGuid(listscq.get(i).get("content24"));//物资主键
                uptableBody.setCinvname(listscq.get(i).get("content4"));//物资名称
                uptableBody.setHprGuidCh(list_HprGuidCh.get(i));
                uptableBody.setIbatch("");
                uptableBody.setCposcode("");
                uptableBody.setHpalGuid("");
                uptableBody.setHppfGuidCh("");
                uptableBody.setHpArrivalvouCh("");
                uptableBody.setCinvcode(listscq.get(i).get("content1"));//物资编码
                uptableBody.setCinvstd(listscq.get(i).get("content6"));//规格型号
                uptableBody.setCcomunitname(listscq.get(i).get("content9"));//主计量单位
                uptableBody.setFunitprice(listscq.get(i).get("content12"));//无税单价
                uptableBody.setFmondey(listscq.get(i).get("content13"));//无税金额
                uptableBody.setFtaxprice(listscq.get(i).get("content14"));//含税单价
                uptableBody.setTaxamount(listscq.get(i).get("content15"));//含税金额
                uptableBody.setFtaxrate(listscq.get(i).get("content16"));//税率
                uptableBody.setIpertaxrate(listscq.get(i).get("content17"));//税额
                uptableBody.setCbatch(listscq.get(i).get("content18"));//批号
                uptableBody.setFquantity(listscq.get(i).get("content19"));//数量
                uptableBody.setCparentid(listscq.get(i).get("content20"));//货位名称
                uptableBody.setCdemo(listscq.get(i).get("content21"));//备注
                uptableBody.setCposcode(listscq.get(i).get("content23"));//货位编码
                uptableBody.setDelFlag(listscq.get(i).get("delFlag"));//删除标志
                String json_body = gson.toJson(uptableBody);
                try {
                    JSONObject jsonObject = new JSONObject(json_body);
                    jsonArray.put(i, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //删除出库单
    public void deleteRKD(View view) {
        try {
            //点击弹出对话框
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("是否要删除该入库单号？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isChandler()) {//如果审核人为空，那么说明未审核，可以删除
                                //调用删除入库单详细信息单条接口
                                HttpNetworkRequest.delete("goods/rs/hpPutstorage?str=" + gei.getHPRGUID(), new BaseHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(rawResponse);
                                            if (jsonObject.getString("message").equals("删除成功")) {
                                                Toast.makeText(ActivityMaterialsInBound.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                finish();
                                                //刷新出库单的数据
                                                ActivityGoodsReceipt.goodsReceipt.onRefresh();
                                                ActivityGoodsReceipt.goodsReceipt.showLayout();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(ActivityMaterialsInBound.this, "网络请求成功，返回数据错误，删除不成功", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                                        Toast.makeText(ActivityMaterialsInBound.this, "网络请求失败，删除不成功", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                });
                            } else {
                                Toast.makeText(ActivityMaterialsInBound.this, "该单据已被审核，不能删除", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ActivityMaterialsInBound.this, "放弃删除", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsInBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickEnable(boolean is) {
        tv_warehouse.setEnabled(is);
        spin_people.setEnabled(is);
        tv_supplier.setEnabled(is);
        spin_raw_data1.setEnabled(is);
        spin_raw_data2.setEnabled(is);
        et_cicode1.setEnabled(is);
        et_cicode2.setEnabled(is);
        et_remark.setEnabled(is);
    }
}
