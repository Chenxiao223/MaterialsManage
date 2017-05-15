package com.zjrfid.materialsmanage.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin3;
import com.zjrfid.materialsmanage.acdbentity.InBoundType;
import com.zjrfid.materialsmanage.acdbentity.InventoryHeader;
import com.zjrfid.materialsmanage.acdbentity.InventoryList;
import com.zjrfid.materialsmanage.acdbentity.InventoryTB;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo;
import com.zjrfid.materialsmanage.acdbentity.ZmInventoryHeader;
import com.zjrfid.materialsmanage.acdbentity.ZmInventoryTB;
import com.zjrfid.materialsmanage.adapter.AdapterPd;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxiao on 2017/4/8.
 * 物资盘点
 */
public class ActivityInventory extends AppCompatActivity {

    private String pdNoStringSave = "";//保存的盘点单号

    public static ActivityInventory inventory;
    private RelativeLayout relative_pandiandan;
    private ListView lv_create;
    private boolean bln_is = true;
    private LinearLayout linear_pandiandan;
    private ImageView img_direction;
    private List<String> list_category = new ArrayList<>();
    private List<String> list_raw_data = new ArrayList<>();
    public TextView tv_warehouse;
    public static AdapterPd adapterPd;

    public ArrayList<HashMap<String, String>> listscq = new ArrayList<>();
    public List<String> list_hpiguid = new ArrayList<>();
    public List<String> list_Cposcode = new ArrayList<>();
    public List<String> list_hpcvguidch = new ArrayList<>();

    public ArrayList<HashMap<String, String>> listscq_isdel = new ArrayList<>();
    public List<String> list_hpiguid_isdel = new ArrayList<>();
    public List<String> list_Cposcode_isdel = new ArrayList<>();
    public List<String> list_hpcvguidch_isdel = new ArrayList<>();
    private int i = 0;
    private LinearLayout line1, line2, line3;
    public static List<String> list_info = new ArrayList<>();
    public int flag = 0;
    public Button btn_save;
    TakePhotoPopWin3 takePhotoPopWin;
    private TextView tv_text9,tv_text12,tv_text13,tv_text18,tv_documentNO, tv_date_time, tv_supplier, tv_section, tv_outbound_category, tv_inbound_category;
    private EditText et_remark;
    Intent it;
    InBoundType inBoundType;//入库和出库通用
    private MaterialSpecificFilesInfo msfi;
    int j;
    public String hpiguid;
    public int sign = 0;
    private int sign2 = 0;
    private String HpwGuid, Cmaker, Chandler, CreateDt, Dveridaate;
    private String HpcvGuid = "";
    public String cwhcode = ActivityHomePage.wareReadhouseBeanString;//仓库编码（权限）
    public String cwhcode2;//正宗的仓库编码
    public String warehouse;//仓库名称
    public String hpwGuid;
    private TextView tv_date_time_show, tv_documentNO_show;//显示简单盘点信息
    private TextView tv_Cmaker, tv_CreateDt, tv_Chandler, tv_Dveridaate;
    private InventoryHeader original_inventoryHeader = new InventoryHeader();//原始盘点单查询（详细）表头
    public InventoryTB original_inventoryTB = new InventoryTB();//原始盘点单查询（详细）表体
    public InventoryHeader inventoryHeader = new InventoryHeader();//收集修改的表头信息
    public InventoryTB inventoryTB = new InventoryTB();//收集修改的表体信息
    private String json_header;//表头json
    private JSONArray jsonArray;//表体json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        //
        inventory = this;
        initView();//初始化控件
        it = getIntent();
        if (it.getIntExtra("get", 0) == 1) {//这里表示是从物资盘点单点击列表进入的
            //盘点单主键,
            getHeaderAndTableBody(it.getStringExtra("hpcvGuid"));
            adapterPd.EditTextIsEditable(false);
            dataChanged();//刷新适配器
            et_remark.setEnabled(false);
            tv_date_time.setEnabled(false);//首次加载，还未点击修改前，时间不可更改
            tv_warehouse.setEnabled(false);//首次加载，还未点击修改前，仓库不可更改
            bln_is = true;

        } else {
            //如果是点击新增，则先进入选择仓库的页面
            Intent intent = new Intent(ActivityInventory.this, Activity_TreeView.class);
            intent.putExtra("flag", 2);
            startActivity(intent);
            //初始化盘点单
            initNewPDD();
        }

    }

    //初始化控件
    public void initView() {

        tv_text9 = (TextView) findViewById(R.id.tv_text9);   //合计 盈亏数量
        tv_text12 = (TextView) findViewById(R.id.tv_text12);//合计 盈亏含税金额
        tv_text13 = (TextView) findViewById(R.id.tv_text13);//合计 盈亏无税金额
        tv_text18 = (TextView) findViewById(R.id.tv_text18);//合计 盈亏税额

        tv_date_time_show = (TextView) findViewById(R.id.tv_date_time_show);//盘点单 盘点单号
        tv_documentNO_show = (TextView) findViewById(R.id.tv_documentNO_show);//盘点单 盘点日期
        tv_documentNO = (TextView) findViewById(R.id.tv_documentNO);//盘点单号
        tv_date_time = (TextView) findViewById(R.id.tv_date_time);//盘点日期
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);//盘点仓库
        tv_section = (TextView) findViewById(R.id.tv_section);//部门
        tv_supplier = (TextView) findViewById(R.id.tv_supplier);//盘点负责人
        et_remark = (EditText) findViewById(R.id.et_remark);//备注
        tv_outbound_category = (TextView) findViewById(R.id.tv_outbound_category);//出库类别
        tv_inbound_category = (TextView) findViewById(R.id.tv_inbound_category);//入库类别
        tv_Cmaker = (TextView) findViewById(R.id.tv_Cmaker);//盘点单制单人
        tv_CreateDt = (TextView) findViewById(R.id.tv_CreateDt);//盘点单制单日期
        tv_Chandler = (TextView) findViewById(R.id.tv_Chandler);//盘点单审核人
        tv_Dveridaate = (TextView) findViewById(R.id.tv_Dveridaate);//盘点单号审核日期

        relative_pandiandan = (RelativeLayout) findViewById(R.id.relative_pandiandan);
        lv_create = (ListView) findViewById(R.id.lv_create);
        linear_pandiandan = (LinearLayout) findViewById(R.id.linear_pandiandan);
        img_direction = (ImageView) findViewById(R.id.img_direction);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        btn_save = (Button) findViewById(R.id.btn_save);
        relative_pandiandan = (RelativeLayout) findViewById(R.id.relative_pandiandan);//盘点单

        list_category.add("请选择出库类别");
        //获取出库类别
        getOutBoundType();
        list_raw_data.add("原始单据");
        list_raw_data.add("发票");
        list_raw_data.add("送货单");

        tv_documentNO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_documentNO_show.setText(tv_documentNO.getText().toString());
                inventoryHeader.setCCVCODE(tv_documentNO.getText().toString());
            }
        });

        tv_date_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_date_time_show.setText(s.toString());
                inventoryHeader.setDCVDATE(s.toString());//接收修改的盘点单时间

            }
        });

        relative_pandiandan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bln_is) {
                    //显示入库单布局
                    linear_pandiandan.setVisibility(View.VISIBLE);
                    img_direction.setImageResource(R.drawable.up);
                    bln_is = false;
                } else {
                    confirm(v);
                }
            }
        });

        //点击选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_remark.clearFocus();
                Intent intent = new Intent(ActivityInventory.this, Activity_TreeView.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
            }
        });


        adapterPd = new AdapterPd(ActivityInventory.this, listscq);
        lv_create.setAdapter(adapterPd);

        adapterPd.setOnItemEditText(new AdapterPd.OnItemEditText() {
            @Override
            public void setText(String s, int p) {
                try {
                    double text9 = 0;//盈亏数量
                    double text12 = 0;//盈亏含税金额
                    double text13 = 0;//盈亏无税金额
                    double text18 = 0;//盈亏含税金额-盈亏无税金额//盈亏税额
                    DecimalFormat df = new DecimalFormat("0.000000");//小数点后保留6位小数

                    if(s.equals(""))
                    {

                        listscq.get(p).put("content8", "");
                        listscq.get(p).put("content9", "");
                        listscq.get(p).put("content12", "");
                        listscq.get(p).put("content13", "");
                        listscq.get(p).put("content18", "");

                    }else{

                        text9 = Double.parseDouble(s.toString()) - Double.parseDouble(listscq.get(p).get("content7"));//盈亏数量
                        text12 = text9 * Double.parseDouble(listscq.get(p).get("content10"));//盈亏含税金额=盈亏数量*含税单价
                        text13 = text9 * Double.parseDouble(listscq.get(p).get("content11"));//盈亏无税金额=盈亏数量*无税单价
                        text18 = text12 - text13;//盈亏含税金额-盈亏无税金额=盈亏税额
                        listscq.get(p).put("content8", s);
                        listscq.get(p).put("content9", df.format(text9) + "");
                        listscq.get(p).put("content12", df.format(text12) + "");
                        listscq.get(p).put("content13", df.format(text13) + "");
                        listscq.get(p).put("content18", df.format(text18) + "");

                    }

                    summaryFourValue();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityInventory.this, "出错", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击盘点日期
        tv_date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_remark.clearFocus();
                final Calendar c = Calendar.getInstance();
                //显示日期选择器
                DatePickerDialog dialog = new DatePickerDialog(ActivityInventory.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        tv_date_time.setText(DateFormat.format("yyyy-MM-dd", c));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //设置备注填写监听
        et_remark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                inventoryHeader.setCDEMO(et_remark.getText().toString());
            }
        });


    }


    public void cancel_et_remark(View view) {
        et_remark.clearFocus();
        InputMethodManager imm = (InputMethodManager) ActivityInventory.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /*
      新盘点表头数据和界面初始化
     */
    private void initNewPDD() {


        //显示入库单布局的时候加载信息
        inventoryHeader.setCDEMO("");
        inventoryHeader.setCPERSONNAME(ActivityHomePage.whay.getUsername());
        getPdNo();//获取盘点单
        inventoryHeader.setHPCVGUID("");
        inventoryHeader.setCMAKER("");
        inventoryHeader.setDCVDATE(getTime());
        inventoryHeader.setCPERSONNAME(ActivityHomePage.whay.getUsername());
        inventoryHeader.setCWHCODE("");
        inventoryHeader.setDVERIDAATE("");
        inventoryHeader.setCDEPCODE(ActivityHomePage.whay.getDeptid());
        inventoryHeader.setCREATEDT("");
        inventoryHeader.setCWHNAME("");
        inventoryHeader.setHPWGUID("");
        inventoryHeader.setCHANDLER("");
        inventoryHeader.setCIRDCODE("盘盈入库");
        inventoryHeader.setCORDCODE("盘亏出库");
        inventoryHeader.setORGNAME(ActivityHomePage.whay.getDeptname());

        setInventoryHeaderWindows(inventoryHeader);

    }


    //确定盘点单 表头信息，并隐藏盘点单表头，获取jsonheader
    public void confirm(View view) {
        if (tv_warehouse.getText().toString().equals("请选择仓库")) {
            Toast.makeText(ActivityInventory.this, "请先选择仓库", Toast.LENGTH_SHORT).show();
        } else {
            //隐藏布局
            et_remark.clearFocus();
            linear_pandiandan.setVisibility(View.GONE);
            img_direction.setImageResource(R.drawable.down);
            bln_is = true;
            //获得盘点单Json
            getNewJsonHeader(inventoryHeader);
            //让软键盘隐藏
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    //从服务器获取 盘点单详细 表头和表体 信息，并界面适配
    public void getHeaderAndTableBody(String str_hpcvguid) {

        //从服务器获取盘点单信息(表头)
        getInventoryHeaderInfoFromServer(str_hpcvguid);
        //从服务器获取盘点单信息(表体)
        getInventoryTableBodyInfoFromServer(str_hpcvguid);

    }


    @Override//销毁静态变量
    protected void onDestroy() {
        super.onDestroy();
        list_info.clear();
        original_inventoryHeader = null;
        HpcvGuid = "";
        Cmaker = "";
        Chandler = "";
        CreateDt = "";
        Dveridaate = "";
        HpwGuid = "";//仓库主键
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果flag等于1，表示是从批次页面点击确定进来的
        if (flag == 1) {
            TakePhotoPopWin3.instance.initDatas();
        }
    }

    //点击回退
    public void back(View view) {
        //清空集合
        list_info.clear();
        finish();
        if (sign2 == 1) {
            ActivityInventoryList.inventoryList.onRefresh();
            ActivityInventoryList.inventoryList.showLayout();
        }
    }

    //点击单条操作
    public void dataShow(View view) {
        if (tv_warehouse.getText().toString().equals("请选择仓库")) {
            takePhotoPopWin = new TakePhotoPopWin3(this, onClickListener, 0);
            //showAtLocation(View parent, int gravity, int x, int y)
            //需要给此activity的布局设置android:id="@+id/main_view"
            takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
            TakePhotoPopWin3.instance.state = 10;
        }else{
            Toast.makeText(ActivityInventory.this, "请填写仓库", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }
    };


    //点击修改
    public void revise(View view) {
        //如果未审核通过就执行
        if (isChandler()) {
            tv_date_time.setEnabled(true);//点击修改后，时间可更改
            tv_warehouse.setEnabled(true);//点击修改后，仓库可更改
            et_remark.setEnabled(true);
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.GONE);
            adapterPd.EditTextIsEditable(true);
            adapterPd.HideCheckBox(false);
            dataChanged();//刷新适配器
            line3.setVisibility(View.VISIBLE);//显示总数那个布局
            //因为有数据，所以让保存按钮可以点击
            ActivityInventory.inventory.btn_save.setClickable(true);
        } else {
            Toast.makeText(ActivityInventory.this, "已审核，无法修改", Toast.LENGTH_SHORT).show();
        }
    }

    // 刷新listview
    private void dataChanged() {
        // 通知listView刷新
        adapterPd.notifyDataSetChanged();
    }

    //点击新增
    public void add(View view) {
        finish();
        startActivity(new Intent(this, ActivityInventory.class));
    }

    //点击保存
    public void save(View view) {
        if (judge()) {
            Toast.makeText(this, "盘点数量不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (listscq.size() == 0) {
            Toast.makeText(this, "无盘点项", Toast.LENGTH_SHORT).show();
            return;
        }
        if (inventoryHeader.getCWHNAME().equals("") || inventoryHeader.getCWHNAME() == null) {
            Toast.makeText(this, "仓库名称未选择", Toast.LENGTH_SHORT).show();
            return;
        }
        json_header = getNewJsonHeader(inventoryHeader);//获取表头Json
        jsonArray = getNewJsonTableBody();//获取表体json

        RequestParams params = new RequestParams();
        params.put("form", json_header);
        params.put("datas", jsonArray.toString());
        //盘点单保存接口
        HttpNetworkRequest.post("goods/rs/hpCheckvouch", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String result_hpcvGuid = jsonObject.getString("hpcvGuid");
                    if (!result_hpcvGuid.equals("") || result_hpcvGuid != null) {
                        Toast.makeText(ActivityInventory.this, "保存成功", Toast.LENGTH_SHORT).show();
                        //这里需要 把返回的S 去拿到HPCVGUID 值
                        finish();
                        Intent intent = new Intent(ActivityInventory.this, ActivityInventory.class);
                        intent.putExtra("get", 1);
                        intent.putExtra("position", "0");
                        intent.putExtra("hpcvGuid", result_hpcvGuid);//盘点单主键
                        intent.putExtra("chandler", "");//审核人
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(ActivityInventory.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityInventory.this, "服务器请求失败，保存不成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //点击删行
    public void delete(View view) {
        //获取详细盘点：单盘点项目主键
        int del_count = 0;
        for (int m = 0; m < listscq.size(); m++) {
            if (listscq.get(m).get("flag").equals("true") && listscq.get(m).get("delFlag").equals("0")) {
                listscq.get(m).put("delFlag", "1");
                del_count++;
            }
        }
        if (del_count == 0) {

            Toast.makeText(ActivityInventory.this, "无删除项", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(ActivityInventory.this, "已删除" + del_count + "项", Toast.LENGTH_SHORT).show();

        }
        dataChanged();
        summaryFourValue();

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
                            RequestParams params = new RequestParams();
                            params.put("key", "0");
                            params.put("str", inventoryHeader.getHPCVGUID());
                            //审核接口
                            HttpNetworkRequest.post("goods/rs/hpCheckvouch/" + inventoryHeader.getHPCVGUID(), params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                    Toast.makeText(ActivityInventory.this, "审核通过", Toast.LENGTH_SHORT).show();
                                    finish();
                                    ActivityInventoryList.inventoryList.onRefresh();
                                    ActivityInventoryList.inventoryList.showLayout();
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                                    Toast.makeText(ActivityInventory.this, "审核失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(ActivityInventory.this, "已审核，无法再次审核", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityInventory.this, "放弃审核", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    //获取出库类别
    public void getOutBoundType() {
        HttpNetworkRequest.get("goods/rs/hpRdtype", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                Gson gson = new Gson();
                inBoundType = gson.fromJson(rawResponse, InBoundType.class);
                for (int j = 0; j < inBoundType.getJsonData().getList().size(); j++) {
                    if (!inBoundType.getJsonData().getList().get(j).getAlias().substring(0, 2).equals("01")) {
                        list_category.add(inBoundType.getJsonData().getList().get(j).getAlias());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Log.e("error", "出错鸟");
            }
        });
    }

    //请求盘点单查询（详细）表体
    public void getInventoryTableBodyInfoFromServer(String str_hpcvguid) {
        RequestParams params = new RequestParams();

        params.put("hpcvGuid", str_hpcvguid);

        //盘点单查询（详细）表体接口
        HttpNetworkRequest.get("goods/rs/hpCheckvouchCh", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                Gson gson = new Gson();
                InventoryTB temp_inventoryTB = gson.fromJson(rawResponse, InventoryTB.class);
                if (temp_inventoryTB.getStatusCode().equals("200")) {//判断接口请求是否成功
                    original_inventoryTB = temp_inventoryTB;
                    inventoryTB = original_inventoryTB;
                    for (int k = 0; k < original_inventoryTB.getJsonData().getList().size(); k++) {
                        list_hpiguid.add(original_inventoryTB.getJsonData().getList().get(k).getHPIGUID());
                        list_Cposcode.add(original_inventoryTB.getJsonData().getList().get(k).getCPOSCODE());
                        list_hpcvguidch.add(original_inventoryTB.getJsonData().getList().get(k).getHPCVGUIDCH());
                        HashMap<String, String> map = new HashMap<String, String>();
                        if (original_inventoryTB.getJsonData().getList().get(k).getCBATCH() == null) {
                            map.put("content1", "");//批号
                        } else {
                            map.put("content1", original_inventoryTB.getJsonData().getList().get(k).getCBATCH());//批号
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCINVCODE() == null) {
                            map.put("content2", "");//物资编码
                        } else {
                            map.put("content2", original_inventoryTB.getJsonData().getList().get(k).getCINVCODE());//物资编码
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCPARENTID() == null) {
                            map.put("content3", "");//货位
                        } else {
                            map.put("content3", original_inventoryTB.getJsonData().getList().get(k).getCPARENTID());//货位
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCINVNAME() == null) {
                            map.put("content4", "");//物资名称
                        } else {
                            map.put("content4", original_inventoryTB.getJsonData().getList().get(k).getCINVNAME());//物资名称
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCINVSTD() == null) {
                            map.put("content5", "");//规格型号
                        } else {
                            map.put("content5", original_inventoryTB.getJsonData().getList().get(k).getCINVSTD());//规格型号
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCCOMUNITNAME() == null) {
                            map.put("content6", "");//单位
                        } else {
                            map.put("content6", original_inventoryTB.getJsonData().getList().get(k).getCCOMUNITNAME());//单位
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getFQUANTITY() == null) {
                            map.put("content7", "");//账面数量
                        } else {
                            map.put("content7", original_inventoryTB.getJsonData().getList().get(k).getFQUANTITY());//账面数量
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getPQUANTITY() == null) {
                            map.put("content8", "");//盘点数量
                        } else {
                            map.put("content8", original_inventoryTB.getJsonData().getList().get(k).getPQUANTITY());//盘点数量
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getYQUANTITY() == null) {
                            map.put("content9", "");//盈亏数量
                        } else {
                            map.put("content9", original_inventoryTB.getJsonData().getList().get(k).getYQUANTITY());//盈亏数量
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getFTAXPRICE() == null) {
                            map.put("content10", "");//含税单价
                        } else {
                            map.put("content10", original_inventoryTB.getJsonData().getList().get(k).getFTAXPRICE());//含税单价
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getFUNITPRICE() == null) {
                            map.put("content11", "");//无税单价
                        } else {
                            map.put("content11", original_inventoryTB.getJsonData().getList().get(k).getFUNITPRICE());//无税单价
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getYTAXPRICE() == null) {
                            map.put("content12", "");//盈亏含税金额
                        } else {
                            map.put("content12", original_inventoryTB.getJsonData().getList().get(k).getYTAXPRICE());//盈亏含税金额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getUTAXPRICE() == null) {
                            map.put("content13", "");//盈亏无税金额
                        } else {
                            map.put("content13", original_inventoryTB.getJsonData().getList().get(k).getUTAXPRICE());//盈亏无税金额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getTAXAMOUNT() == null) {
                            map.put("content14", "");//账面含税金额
                        } else {
                            map.put("content14", original_inventoryTB.getJsonData().getList().get(k).getTAXAMOUNT());//账面含税金额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getFMONDEY() == null) {
                            map.put("content15", "");//账面无税金额
                        } else {
                            map.put("content15", original_inventoryTB.getJsonData().getList().get(k).getFMONDEY());//账面无税金额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getFTAXRATE() == null) {
                            map.put("content16", "");//税率
                        } else {
                            map.put("content16", original_inventoryTB.getJsonData().getList().get(k).getFTAXRATE());//税率
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getIPERTAXRATE() == null) {
                            map.put("content17", "");//账面税额
                        } else {
                            map.put("content17", original_inventoryTB.getJsonData().getList().get(k).getIPERTAXRATE());//账面税额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getUPERTAXRATE() == null) {
                            map.put("content18", "");//盈亏税额
                        } else {
                            map.put("content18", original_inventoryTB.getJsonData().getList().get(k).getUPERTAXRATE());//盈亏税额
                        }
                        if (original_inventoryTB.getJsonData().getList().get(k).getCDEMO() == null) {
                            map.put("content19", "");//备注
                        } else {
                            map.put("content19", original_inventoryTB.getJsonData().getList().get(k).getCDEMO());//备注
                        }
                        map.put("flag", "false");
                        map.put("delFlag", "0");
                        listscq.add(map);
                        adapterPd.HideCheckBox(true);
                    }
                    line1.setVisibility(View.GONE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.GONE);
                    adapterPd.notifyDataSetChanged();
                    //计算
                    summaryFourValue();
                } else {
                    String msg = temp_inventoryTB.getStatusCode().toString();
                    Toast.makeText(ActivityInventory.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Toast.makeText(ActivityInventory.this, "查询盘点单详细表体失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取并适配界面：盘点单查询（详细）表头
    public void getInventoryHeaderInfoFromServer(String str_hpcvguid) {

        RequestParams params = new RequestParams();

        params.put("hpcvGuid", str_hpcvguid);
        //盘点单查询（详细）表头
        HttpNetworkRequest.get("goods/rs/hpCheckvouch/" + str_hpcvguid, params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {

                try {
                    Gson gson = new Gson();
                    original_inventoryHeader = gson.fromJson(rawResponse, InventoryHeader.class);
                    inventoryHeader = original_inventoryHeader;

                    HpcvGuid = original_inventoryHeader.getHPCVGUID();
                    Cmaker = original_inventoryHeader.getCMAKER();
                    Chandler = original_inventoryHeader.getCHANDLER();
                    CreateDt = original_inventoryHeader.getCREATEDT();
                    Dveridaate = original_inventoryHeader.getDVERIDAATE();
                    HpwGuid = original_inventoryHeader.getHPWGUID();//仓库主键
                    cwhcode2 = original_inventoryHeader.getCWHCODE();//仓库编码

                    setInventoryHeaderWindows(original_inventoryHeader);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityInventory.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Toast.makeText(ActivityInventory.this, "查询盘点单详细表头失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //设置盘点单表头界面
    public void setInventoryHeaderWindows(InventoryHeader setInventoryHeader) {
        tv_documentNO.setText(setInventoryHeader.getCCVCODE());//盘点单号
        tv_date_time.setText(setInventoryHeader.getDCVDATE());//盘点日期
        tv_warehouse.setText(setInventoryHeader.getCWHNAME());//盘点仓库
        tv_outbound_category.setText(setInventoryHeader.getCORDCODE());//出库类别
        tv_inbound_category.setText(setInventoryHeader.getCIRDCODE());//入库类别
        tv_section.setText(setInventoryHeader.getORGNAME());//部门
        tv_supplier.setText(setInventoryHeader.getCPERSONNAME());//盘点负责人
        et_remark.setText(setInventoryHeader.getCDEMO());//备注
        tv_Cmaker.setText(setInventoryHeader.getCMAKER());//制单人
        tv_CreateDt.setText(setInventoryHeader.getCREATEDT());//制单日期
        tv_Chandler.setText(setInventoryHeader.getCHANDLER());//审核人
        tv_Dveridaate.setText(setInventoryHeader.getDVERIDAATE());//审核日期
    }

    //收集的表体类转换为jsonTableBody
    private JSONArray getNewJsonTableBody() {

        ZmInventoryTB zmInventoryTB = new ZmInventoryTB();
        Gson gson = new Gson();
        JSONArray temp_jsonArray = new JSONArray();
        try {
            for (int i = 0; i < listscq.size(); i++) {
                zmInventoryTB.setHpcvGuidCh(list_hpcvguidch.get(i));
                zmInventoryTB.setCbatch(listscq.get(i).get("content1"));//批号
                zmInventoryTB.setCinvcode(listscq.get(i).get("content2"));//物资编码
                zmInventoryTB.setCparentid(listscq.get(i).get("content3"));//货位
                zmInventoryTB.setCinvname(listscq.get(i).get("content4"));//物资名称
                zmInventoryTB.setCinvstd(listscq.get(i).get("content5"));//规格型号
                zmInventoryTB.setCcomunitname(listscq.get(i).get("content6"));//单位
                zmInventoryTB.setFquantity(listscq.get(i).get("content7"));//账面数量
                zmInventoryTB.setPquantity(listscq.get(i).get("content8"));//盘点数量
                zmInventoryTB.setYquantity(listscq.get(i).get("content9"));//盈亏数量
                zmInventoryTB.setFtaxprice(listscq.get(i).get("content10"));//含税单价
                zmInventoryTB.setFunitprice(listscq.get(i).get("content11"));//无税单价
                zmInventoryTB.setYtaxprice(listscq.get(i).get("content12"));//盈亏含税金额
                zmInventoryTB.setUtaxprice(listscq.get(i).get("content13"));//盈亏无税金额
                zmInventoryTB.setTaxamount(listscq.get(i).get("content14"));//账面含税金额
                zmInventoryTB.setFmondey(listscq.get(i).get("content15"));//账面无税金额
                zmInventoryTB.setFtaxrate(listscq.get(i).get("content16"));//税率
                zmInventoryTB.setIpertaxrate(listscq.get(i).get("content17"));//账面税额
                zmInventoryTB.setUpertaxrate(listscq.get(i).get("content18"));//盈亏税额
                zmInventoryTB.setCdemo(listscq.get(i).get("content19"));//备注
                zmInventoryTB.setHpiGuid(list_hpiguid.get(i));//hpiguid
                zmInventoryTB.setCposcode(list_Cposcode.get(i));//货位编码
                zmInventoryTB.setDelFlag(listscq.get(i).get("delFlag"));
                String json_body = gson.toJson(zmInventoryTB);
                try {
                    JSONObject jsonObject = new JSONObject(json_body);
                    temp_jsonArray.put(i, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityInventory.this, "出错", Toast.LENGTH_SHORT).show();
        }
        return temp_jsonArray;
    }

    //判断字符串最后一个字母出现的index
    public int estimate(String str) {
        String s = str;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if ('A' < s.charAt(i) && s.charAt(i) < 'z') {
                count++;
                break;
            }
        }
        if (count == 0) {
            Log.i("info", "没有字母存在！");
        }
        return (str.length() - count + 1) / 3;
    }

    //请求获取盘点单号,赋予给界面和修改盘点表头类
    public void getPdNo() {
        //盘点单号接口
        HttpNetworkRequest.get("goods/rs/hpNumRefer", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                try {
                    JSONObject jsonObject = new JSONObject(rawResponse);
                    String PdNo = "PD" + jsonObject.getString("id");
                    tv_documentNO.setText(PdNo);//盘点单号
                    inventoryHeader.setCCVCODE(PdNo);// 保存新建盘点单号

                } catch (JSONException e) {
                    e.printStackTrace();
                    tv_documentNO.setText("");
                    inventoryHeader.setCCVCODE("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                inventoryHeader.setCCVCODE("");
            }
        });
    }

    //获取盘点单-表头的jsonHeader
    public String getNewJsonHeader(InventoryHeader newHeader) {
            //登录人员信息接口
            String temp_JsonHeader = "";
            try {
                ZmInventoryHeader head = new ZmInventoryHeader();
                head.setCmaker(newHeader.getCMAKER());
                head.setChandler(newHeader.getCHANDLER());
                head.setCreateDt(newHeader.getCREATEDT());
                head.setDveridaate(newHeader.getDVERIDAATE());
                head.setHpwGuid(newHeader.getHPWGUID());//仓库主键
                head.setHpcvGuid(newHeader.getHPCVGUID());//盘点单主键
                head.setCcvcode(newHeader.getCCVCODE());//盘点单号
                head.setNavTabId("hpCheckvouch"); //下载的保存
                head.setCallbackType("closeCurrent");
                head.setCallBackMethod("hpCheckvouchSearch");
                head.setDcvdate(newHeader.getDCVDATE());
                head.setCordcode(newHeader.getCORDCODE());
                head.setCirdcode(newHeader.getCIRDCODE());
                head.setOrgname(newHeader.getORGNAME());
                head.setCpersonname(newHeader.getCPERSONNAME());
                head.setCdemo(newHeader.getCDEMO());
                head.setIassets("");
                head.setHpcvGuidChs("");
                head.setChandler(newHeader.getCHANDLER());
                head.setCwhname(newHeader.getCWHNAME());
                head.setCwhcode(newHeader.getCWHCODE());//仓库编码
                head.setCpersoncode(newHeader.getCPERSONCODE());//人员主键
                head.setCdepcode(newHeader.getCDEPCODE());//部门主键
                Gson gson = new Gson();
                temp_JsonHeader = gson.toJson(head);
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
        return temp_JsonHeader;
    }

    //判断盘点数量是否为空的方法
    public boolean judge() {
        boolean is = true;
        for (int i = 0; i < listscq.size(); i++) {
            if (listscq.get(i).get("content8").equals("") && listscq.get(i).get("delFlag").equals("0")) {//为空时true
                is = true;
                break;
            } else {
                is = false;
            }
        }
        return is;
    }


    //盘点审核人是否为空,为空就是true
    public boolean isChandler() {
        boolean is = false;
        if (inventoryHeader.getCHANDLER() == null || inventoryHeader.getCHANDLER().equals("")) {
            is = true;
        }
        return is;
    }



    public void summaryFourValue()
    {
        double dtext9=0.0;
        double dtext12=0.0;
        double dtext13=0.0;
        double dtext18=0.0;
        DecimalFormat df = new DecimalFormat("0.000000");//小数点后保留6位小数
        for(int i=0;i<listscq.size();i++)
        {
            if(listscq.get(i).get("delFlag").equals("0")
                    &&(!listscq.get(i).get("content8").equals("")
                    ||!listscq.get(i).get("content9").equals("")
                    ||!listscq.get(i).get("content12").equals("")
                    ||!listscq.get(i).get("content13").equals("")
            ||!listscq.get(i).get("content18").equals("")))
            {
                dtext9 =dtext9+ Double.parseDouble(listscq.get(i).get("content9"));
                dtext12 = dtext12 +Double.parseDouble(listscq.get(i).get("content12"));
                dtext13 = dtext13 +Double.parseDouble(listscq.get(i).get("content13"));
                dtext18 = dtext18 +Double.parseDouble(listscq.get(i).get("content18"));
            }
        }
        tv_text9.setText(df.format(dtext9) + "");
        tv_text12.setText(df.format(dtext12) + "");
        tv_text13.setText(df.format(dtext13) + "");
        tv_text18.setText(df.format(dtext18) + "");

    }



}
