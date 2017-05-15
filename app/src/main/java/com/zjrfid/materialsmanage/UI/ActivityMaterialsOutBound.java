package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin2;
import com.zjrfid.materialsmanage.acdbentity.House;
import com.zjrfid.materialsmanage.acdbentity.InBoundType;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo2;
import com.zjrfid.materialsmanage.acdbentity.OutboundHeader;
import com.zjrfid.materialsmanage.acdbentity.OutboundInfoTB;
import com.zjrfid.materialsmanage.acdbentity.StockRemovalInfo;
import com.zjrfid.materialsmanage.acdbentity.UPloadOutBody;
import com.zjrfid.materialsmanage.adapter.IAdapter3;
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
 * 物资出库
 */
public class ActivityMaterialsOutBound extends Activity {
    private String HPRGUID = "";
    public String hpwGuid = "";
    public String cwhcode2 = "";
    public String warehouse = "";//仓库

    public static ActivityMaterialsOutBound materialsOutBound;
    private RelativeLayout relative_rukudan;
    private ListView lv_create;
    private boolean bln_is = true;
    private LinearLayout line_rukudan;
    private ImageView img_direction;
    private Spinner spin_category, spin_data_type1, spin_data_type2;
    private ArrayAdapter<String> adapter;
    private List<String> list_category = new ArrayList<>();
    private List<String> list_raw_data = new ArrayList<>();
    public TextView tv_warehouse;
    public static IAdapter3 iAdapter;
    public ArrayList<HashMap<String, String>> listscq = new ArrayList<>();
    private int i = 0;
    public int item;
    public Double dbl_number = 0.0;//总价
    public HashMap<String, String> map2;
    private LinearLayout line1, line2, line3;
    public TextView tv_total_prices;
    public static List<String> list_info = new ArrayList<>();
    public List<House> mlist = new ArrayList<>();
    public House house;
    public int flag = 0;
    public Button btn_save;
    TakePhotoPopWin2 takePhotoPopWin;
    public static String str_amount, str_hanshui, str_shuilv;
    private TextView tv_documentNO, tv_date_time, tv_auditor,
            tv_audit_date, tv_documentMaker, tv_voucher_data, tv_lingliaodan, tv_zibianhao,
            et_cicode1, et_cicode2;
    public TextView tv_acquisition_staff, tv_section;
    public TextView tv_amount;
    Intent it;
    InBoundType inBoundType;//入库和出库通用
    private String param;
    private StockRemovalInfo sri;
    private MaterialSpecificFilesInfo2 msfi;
    private OutboundInfoTB oitb;//出库详细信息（表体）
    private String json_header;//表头json
    private JSONArray jsonArray;//表体json
    int j;
    public String hpiguid;
    public List<String> list_hpiguid = new ArrayList<>();
    private int sign2 = 0;
    public String cwhcode = ActivityHomePage.wareReadhouseBeanString;//仓库编码（权限）
    public List<String> list_HprGuidCh = new ArrayList<>();
    public int amount;
    private String hprguid = "";
    private EditText et_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_out_bound);
        //
        materialsOutBound = this;
        SysApplication.getInstance().addActivity(this);
        initView();//初始化控件
        initSpinner();//初始化Spinner
        it = getIntent();
        flag = it.getIntExtra("get", 0);
        if (flag == 1) {//这里表示是从物资出库单点击列表进入的
            addData();
            hprguid = it.getStringExtra("hprGuid");
            tv_amount.setText(it.getStringExtra("amount"));
            line_rukudan.setVisibility(View.GONE);//隐藏出库单
            clickEnable(false);//出库单控件无法点击
        } else {
            tv_date_time.setText(getTime());
            tv_acquisition_staff.setText("610214|黄晓承");
            tv_section.setText("计划财务部");
            spin_data_type1.setSelection(1);//原始单据类型1
            spin_data_type2.setSelection(1);//原始单据类型2
            bln_is = false;
        }
    }

    //初始化控件
    public void initView() {
        et_remark = (EditText) findViewById(R.id.et_remark);
        relative_rukudan = (RelativeLayout) findViewById(R.id.relative_rukudan);
        lv_create = (ListView) findViewById(R.id.lv_create);
        line_rukudan = (LinearLayout) findViewById(R.id.line_rukudan);
        img_direction = (ImageView) findViewById(R.id.img_direction);
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        tv_total_prices = (TextView) findViewById(R.id.tv_total_prices);
        iAdapter = new IAdapter3(ActivityMaterialsOutBound.this, listscq);
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_documentNO = (TextView) findViewById(R.id.tv_documentNO);//入库单据号
        tv_date_time = (TextView) findViewById(R.id.tv_date_time);//入库日期
        tv_acquisition_staff = (TextView) findViewById(R.id.tv_acquisition_staff);//领料人员
        tv_section = (TextView) findViewById(R.id.tv_section);//部门
        tv_auditor = (TextView) findViewById(R.id.tv_auditor);//审核人
        tv_audit_date = (TextView) findViewById(R.id.tv_audit_date);//审核日期
        tv_documentMaker = (TextView) findViewById(R.id.tv_documentMaker);//制单人
        tv_voucher_data = (TextView) findViewById(R.id.tv_voucher_data);//制单日期
        tv_lingliaodan = (TextView) findViewById(R.id.tv_lingliaodan);//领料单号
        tv_zibianhao = (TextView) findViewById(R.id.tv_zibianhao);//车辆自编号
        et_cicode1 = (TextView) findViewById(R.id.et_cicode1);//单据号1
        et_cicode2 = (TextView) findViewById(R.id.et_cicode2);//单据号2
        tv_amount = (TextView) findViewById(R.id.tv_amount);//数量
        spin_category = (Spinner) findViewById(R.id.spin_category);
        spin_data_type1 = (Spinner) findViewById(R.id.spin_data_type1);
        spin_data_type2 = (Spinner) findViewById(R.id.spin_data_type2);
        list_category.add("请选择出库类别");
        //获取出库类别
        getOutBoundType();
        list_raw_data.add("原始单据");
        list_raw_data.add("发票");
        list_raw_data.add("送货单");

        //点击领料人员
        tv_acquisition_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMaterialsOutBound.this, ActivityPickMaterialPerson.class));
            }
        });

        //点击领料部门
        tv_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMaterialsOutBound.this, ActivityPickMaterialPerson.class));
            }
        });

        //点击出库单
        relative_rukudan = (RelativeLayout) findViewById(R.id.relative_rukudan);
        relative_rukudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bln_is) {
                    //显示入库单布局
                    line_rukudan.setVisibility(View.VISIBLE);
                    img_direction.setImageResource(R.drawable.up);
                    bln_is = false;

                } else {
                    //隐藏入库单布局
                    line_rukudan.setVisibility(View.GONE);
                    img_direction.setImageResource(R.drawable.down);
                    bln_is = true;
                }
            }
        });

        //点击选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMaterialsOutBound.this, Activity_TreeView.class);
                intent.putExtra("flag", 7);
                startActivity(intent);
            }
        });

        iAdapter = new IAdapter3(ActivityMaterialsOutBound.this, listscq);
        lv_create.setAdapter(iAdapter);
        iAdapter.setOnItemEdittext(new IAdapter3.OnItemEdittext() {
            @Override
            public void setText(String s, int p) {
                Log.i("info", "beizhu: " + s);
                listscq.get(p).put("content22", s);
            }
        });
        // 绑定listView的监听器
        lv_create.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                IAdapter3.ViewHold holder = (IAdapter3.ViewHold) arg1.getTag();
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
        //出库库类别
        adapter = new ArrayAdapter<String>(ActivityMaterialsOutBound.this, android.R.layout.simple_spinner_item, list_category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_category.setAdapter(adapter);

        //原始数据
        adapter = new ArrayAdapter<String>(ActivityMaterialsOutBound.this, android.R.layout.simple_spinner_item, list_raw_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_data_type1.setAdapter(adapter);

        //原始数据2
        adapter = new ArrayAdapter<String>(ActivityMaterialsOutBound.this, android.R.layout.simple_spinner_item, list_raw_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_data_type2.setAdapter(adapter);
    }

    //点击出库单中的确定
    public void confirm(View view) {
        if (tv_warehouse.getText().toString().equals("请选择仓库")) {
            Toast.makeText(ActivityMaterialsOutBound.this, "请选择仓库", Toast.LENGTH_SHORT).show();
        } else {
            line_rukudan.setVisibility(View.GONE);//隐藏布局
            //获取表头Json
            getCkJson();
        }
    }

    //获取表头Json
    public void getCkJson() {
        try {
            OutboundHeader outboundHeader = new OutboundHeader();
            if (it.getIntExtra("get", 0) == 1) {//如果等于1说明是从物资入库单点击进入此页面，这是gei才有值
                outboundHeader.setCcode(sri.getCCODE());
                outboundHeader.setIwhpos(sri.getIWHPOS());
                outboundHeader.setCpersoncode(sri.getCPERSONCODE());
                outboundHeader.setCdepcode(sri.getCDEPCODE());
                outboundHeader.setCmemo(sri.getCMEMO());
                outboundHeader.setHprGuidChs("");
                outboundHeader.setCmaker(sri.getCMAKER());
                outboundHeader.setChandler(sri.getCHANDLER());
                outboundHeader.setCreateDt(sri.getCREATEDT());
                outboundHeader.setDveridaate(sri.getDVERIDAATE());
                outboundHeader.setCwhcode(sri.getCWHCODE());//仓库编码
            } else {
                outboundHeader.setCcode("");
                outboundHeader.setIwhpos("");
                outboundHeader.setCpersoncode("");
                outboundHeader.setCdepcode("");
                outboundHeader.setCmemo("");
                outboundHeader.setHprGuidChs("");
                outboundHeader.setCmaker("");
                outboundHeader.setChandler("");
                outboundHeader.setCreateDt("");
                outboundHeader.setDveridaate("");
                outboundHeader.setCwhcode(cwhcode2);//仓库编码
            }
            outboundHeader.setHprGuid(HPRGUID);//主键，如果主键为空就是新增，不为空就是修改
            outboundHeader.setCwhname(tv_warehouse.getText().toString());//仓库名称
            if (spin_category.getSelectedItem().toString().equals("请选择出库类别")) {
                outboundHeader.setCrdcode("");//
                outboundHeader.setCrdcode_("");//
                outboundHeader.setCrdname("");//出库类别
            } else {
                outboundHeader.setCrdcode(spin_category.getSelectedItem().toString().substring(0, spin_category.getSelectedItem().toString().indexOf(" ")));//
                outboundHeader.setCrdcode_("");
                outboundHeader.setCrdname(spin_category.getSelectedItem().toString().substring(spin_category.getSelectedItem().toString().indexOf(" ") + 1));//出库类别
            }
            if (spin_data_type1.getSelectedItem().toString().equals("原始单据")) {
                outboundHeader.setCvouchtype1("");
            } else {
                outboundHeader.setCvouchtype1(spin_data_type1.getSelectedItem().toString());
            }
            if (spin_data_type2.getSelectedItem().toString().equals("原始单据")) {
                outboundHeader.setCvouchtype2("");
            } else {
                outboundHeader.setCvouchtype2(spin_data_type2.getSelectedItem().toString());
            }
            outboundHeader.setCvouchtype("11");
            outboundHeader.setOrgname(tv_section.getText().toString());//部门名称
            outboundHeader.setCpersonname(tv_acquisition_staff.getText().toString());
            outboundHeader.setBrdflag("1");
            outboundHeader.setDkeepdate(tv_date_time.getText().toString());//出库日期
            outboundHeader.setAlid(tv_lingliaodan.getText().toString());
            outboundHeader.setBusno(tv_zibianhao.getText().toString());
            outboundHeader.setCicode1(et_cicode1.getText().toString());
            outboundHeader.setCicode2(et_cicode2.getText().toString());
            outboundHeader.setCmemo(et_remark.getText().toString());//备注
            outboundHeader.setNavTabId("hpRdrecord");
            outboundHeader.setCallbackType("closeCurrent");
            outboundHeader.setCallBackMethod("hpOutStorageSearch");
            Gson gson = new Gson();
            json_header = gson.toJson(outboundHeader);
            System.out.println("表头" + json_header);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsOutBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //添加数据
    public void addData() {
        param = it.getStringExtra("hprGuid");
        HPRGUID = param;
        //从服务器获取出库单信息(表头)
        getStockRemovalInfo(param);
        //获取物资具体档案信息
        getMaterialSpecificFilesInfo(param);
    }

    @Override//销毁静态变量
    protected void onDestroy() {
        super.onDestroy();
        str_amount = null;
        str_hanshui = null;
        str_shuilv = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果有数据，保存按钮可以点击
        if (listscq.size() > 0) {
            btn_save.setClickable(true);
        }
    }

    //点击回退
    public void back(View view) {
        //清空集合
        list_info.clear();
        finish();
        IAdapter3.count = 0;
        if (sign2 == 1) {
            ActivityMaterialOutboundOrder.materialOutboundOrder.onRefresh();
            ActivityMaterialOutboundOrder.materialOutboundOrder.showLayout();
        }
    }

    public void price(int i, int location) {
        Log.i("info", "总价：" + listscq.get(location).get("content15") + ", 数量：" + listscq.get(location).get("content21"));
        //0表示加，1表示减
        if (i == 0) {
            dbl_number = dbl_number + Double.parseDouble(listscq.get(location).get("content15"));
            amount = amount + Integer.parseInt(listscq.get(location).get("content21"));
        } else if (i == 1) {
            dbl_number = dbl_number - Double.parseDouble(listscq.get(location).get("content15"));
            amount = amount - Integer.parseInt(listscq.get(location).get("content21"));
        }
        DecimalFormat df = new DecimalFormat("0.00 ");//小数点后保留两位小数
        tv_total_prices.setText(df.format(dbl_number) + "");
        tv_amount.setText(amount + "");
    }

    //点击单条操作
    public void datashow(View view) {
        if (!tv_warehouse.getText().toString().equals("请选择仓库")) {
            takePhotoPopWin = new TakePhotoPopWin2(this, onClickListener, 0);
            //showAtLocation(View parent, int gravity, int x, int y)
            takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);//125
            TakePhotoPopWin2.instance.state = 10;
        } else {
            Toast.makeText(ActivityMaterialsOutBound.this, "请填写仓库", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }
    };

    //点击单条修改
    public void alter(View view) {
        if (isChandler()) {
            if (IAdapter3.count > 1) {
                Toast.makeText(ActivityMaterialsOutBound.this, "只能修改一条", Toast.LENGTH_SHORT).show();
            } else if (IAdapter3.count == 0) {
                Toast.makeText(ActivityMaterialsOutBound.this, "请选择一项修改", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, ActivityAlterBatch.class);
                intent.putExtra("position", IAdapter3.location);//把选中的位置传过去
                intent.putExtra("cinvcode", listscq.get(IAdapter3.location).get("content1"));//物资编码
                intent.putExtra("cinvname", listscq.get(IAdapter3.location).get("content4"));//物资名称
                intent.putExtra("cwhname", listscq.get(IAdapter3.location).get("content10"));//仓库名称
                intent.putExtra("hwbm", listscq.get(IAdapter3.location).get("content23"));//货位编码
                startActivity(intent);
            }
        } else {
            Toast.makeText(ActivityMaterialsOutBound.this, "该条已审核，无法修改", Toast.LENGTH_SHORT).show();
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
            ActivityMaterialsOutBound.materialsOutBound.btn_save.setClickable(true);
            clickEnable(true);//出库单控件可以点击
        } else {
            Toast.makeText(ActivityMaterialsOutBound.this, "该条已审核，无法修改", Toast.LENGTH_SHORT).show();
        }
    }

    // 刷新listview
    public static void dataChanged() {
        // 通知listView刷新
        iAdapter.notifyDataSetChanged();
    }

    //点击新增
    public void add(View view) {
        finish();
        startActivity(new Intent(this, ActivityMaterialsOutBound.class));
    }

    //点击保存
    public void save(View view) {
        getCkJson();//获取表头Json
        setTableBody();//将表体转换成json
        RequestParams params = new RequestParams();
        params.put("form", json_header);
        params.put("data", jsonArray.toString());
        //新增出库单详细信息单条接口
        HttpNetworkRequest.post("goods/rs/hpoutstorage", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Toast.makeText(ActivityMaterialsOutBound.this, "保存成功", Toast.LENGTH_SHORT).show();
                //刷新当前页面
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    finish();
                    Intent intent = new Intent(ActivityMaterialsOutBound.this, ActivityMaterialsOutBound.class);
                    intent.putExtra("get", 1);
                    intent.putExtra("hprGuid", jsonObject.getString("message"));//把主键传过去
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Log.getStackTraceString(throwable);
                Toast.makeText(ActivityMaterialsOutBound.this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //点击删行
    public void delete(View view) {
        for (int m = 0; m < listscq.size(); m++) {
            //如果选中但还未删除
            if (listscq.get(m).get("flag").equals("true") && listscq.get(m).get("delFlag").equals("0")) {
                listscq.get(m).put("delFlag", "1");
                try {
                    price(1, m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        dataChanged();
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
                            params.put("type", "0");
                            params.put("hppGuid", hprguid);
                            //审核接口
                            HttpNetworkRequest.post("goods/rs/hpoutstorages", params, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(rawResponse);
                                        //如果返回值是success，则说明审核成功
                                        String str = jsonObject.getString("message");
                                        if (str.equals("success")) {
                                            Toast.makeText(ActivityMaterialsOutBound.this, "审核通过", Toast.LENGTH_SHORT).show();
                                            finish();
                                            //刷新出库单的数据
                                            ActivityMaterialOutboundOrder.materialOutboundOrder.onRefresh();
                                            ActivityMaterialOutboundOrder.materialOutboundOrder.showLayout();
                                        } else {
                                            Toast.makeText(ActivityMaterialsOutBound.this, "审核失败:" + str, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                                    Log.e("error", "出错");
                                    Toast.makeText(ActivityMaterialsOutBound.this, "审核失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(ActivityMaterialsOutBound.this, "已审核，无法再次审核", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityMaterialsOutBound.this, "放弃审核", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");//大写的HH是24小时制，小写的hh则是12小时制
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
                spin_category.setSelection(2);//出库类别
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Log.e("error", "出错鸟");
            }
        });
    }

    //给出库单赋值
    public void setStockRemovalInfo(StockRemovalInfo sri) {
        try {
            tv_documentNO.setText(sri.getCCODE());//出库单据号
            tv_date_time.setText(sri.getDKEEPDATE());//领料日期
            tv_warehouse.setText(sri.getCWHNAME());//仓库
            for (int i = 0; i < list_category.size(); i++) {
                if (list_category.get(i).replace(" ", "").replaceAll("\\d+", "").equals(sri.getCRDNAME())) {//去掉数字和空格的字符串再做比较
                    spin_category.setSelection(i);//出库类别
                }
            }
            tv_acquisition_staff.setText(sri.getCPERSONNAME());//领料人员
            tv_section.setText(sri.getORGNAME());//部门
            tv_lingliaodan.setText(sri.getALID());//领料单号
            tv_zibianhao.setText(sri.getBUSNO());//车辆自编号
            int j = list_raw_data.indexOf(sri.getCVOUCHTYPE1());
            spin_data_type1.setSelection(j);//原始单据类型1
            int k = list_raw_data.indexOf(sri.getCVOUCHTYPE2());
            spin_data_type2.setSelection(k);//原始单据类型2
            et_cicode1.setText(sri.getCICODE1());//单据号1
            et_cicode2.setText(sri.getCICODE2());//单据号2
            tv_auditor.setText(sri.getCHANDLER());//审核人
            tv_audit_date.setText(sri.getDVERIDAATE());//审核日期
            tv_documentMaker.setText(sri.getCMAKER());//制单人
            tv_voucher_data.setText(sri.getCREATEDT());//制单日期
            et_remark.setText(sri.getCMEMO());//备注
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
    }

    //获取物资档案信息，他是物资入库的一部分数据
    public void getMaterialSpecificFilesInfo(String param) {
        try {
            RequestParams params = new RequestParams();
            params.put("hprGuid", param);
            //出库单详细信息接口（表体）
            HttpNetworkRequest.get("goods/rs/hpoutstorages", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    oitb = gson.fromJson(rawResponse, OutboundInfoTB.class);
                    if (oitb.getStatusCode().equals("200")) {//判断接口请求是否成功
                        for (int i = 0; i < oitb.getJsonData().size(); i++) {
                            list_HprGuidCh.add(oitb.getJsonData().get(i).getHPRGUIDCH());
                            list_hpiguid.add(oitb.getJsonData().get(i).getHPIGUID());

                            String hpiguid = oitb.getJsonData().get(i).getHPIGUID();
                            //物资档案接口
                            final int finalI = i;
                            HttpNetworkRequest.get("goods/rs/hpInventory/" + hpiguid, new BaseHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                    Gson gson = new Gson();
                                    msfi = gson.fromJson(rawResponse, MaterialSpecificFilesInfo2.class);
                                    map2 = new HashMap<String, String>();
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
                                    if (msfi.getFTAXRATE() == null) {
                                        map2.put("content5", "");//默认税率
                                    } else {
                                        map2.put("content5", msfi.getFTAXRATE());//默认税率
                                    }
                                    if (msfi.getIASSET() == null) {
                                        map2.put("content7", "");//是否资产
                                    } else {
                                        map2.put("content7", msfi.getIASSET());//是否资产
                                    }
                                    if (oitb.getJsonData().get(finalI).getCPARENTID() == null) {
                                        map2.put("content8", "");//默认货位
                                    } else {
                                        map2.put("content8", oitb.getJsonData().get(finalI).getCPARENTID());//默认货位
                                    }
                                    if (msfi.getCWHNAME() == null) {
                                        map2.put("content10", "");//默认仓库
                                    } else {
                                        map2.put("content10", msfi.getCWHNAME());//默认仓库
                                    }

                                    if (oitb.getJsonData().get(finalI).getCINVCODE() == null) {
                                        map2.put("content1", "");//物资编码
                                    } else {
                                        map2.put("content1", oitb.getJsonData().get(finalI).getCINVCODE());//物资编码
                                    }

                                    if (oitb.getJsonData().get(finalI).getCINVNAME() == null) {
                                        map2.put("content4", "");//物料名称
                                    } else {
                                        map2.put("content4", oitb.getJsonData().get(finalI).getCINVNAME());//物料名称
                                    }

                                    if (oitb.getJsonData().get(finalI).getCINVSTD() == null) {
                                        map2.put("content6", "");//规格型号
                                    } else {
                                        map2.put("content6", oitb.getJsonData().get(finalI).getCINVSTD());//规格型号
                                    }

                                    if (oitb.getJsonData().get(finalI).getCCOMUNITNAME() == null) {
                                        map2.put("content9", "");//主计量单位
                                    } else {
                                        map2.put("content9", oitb.getJsonData().get(finalI).getCCOMUNITNAME());//主计量单位
                                    }

                                    map2.put("content11", "null");//辅助计量单位
                                    if (oitb.getJsonData().get(finalI).getFUNITPRICE() == null) {
                                        map2.put("content12", "");//无税单价
                                    } else {
                                        map2.put("content12", oitb.getJsonData().get(finalI).getFUNITPRICE());//无税单价
                                    }
                                    if (oitb.getJsonData().get(finalI).getFMONDEY() == null) {
                                        map2.put("content13", "");//无税金额
                                    } else {
                                        map2.put("content13", oitb.getJsonData().get(finalI).getFMONDEY());//无税金额
                                    }
                                    if (oitb.getJsonData().get(finalI).getFTAXPRICE() == null) {
                                        map2.put("content14", "");//含税单价
                                    } else {
                                        map2.put("content14", oitb.getJsonData().get(finalI).getFTAXPRICE());//含税单价
                                    }
                                    if (oitb.getJsonData().get(finalI).getTAXAMOUNT() == null) {
                                        map2.put("content15", "");//含税金额
                                    } else {
                                        map2.put("content15", oitb.getJsonData().get(finalI).getTAXAMOUNT());//含税金额
                                    }
                                    if (oitb.getJsonData().get(finalI).getFTAXRATE() == null) {
                                        map2.put("content16", "");//税率
                                    } else {
                                        map2.put("content16", oitb.getJsonData().get(finalI).getFTAXRATE());//税率
                                    }
                                    if (oitb.getJsonData().get(finalI).getIPERTAXRATE() == null) {
                                        map2.put("content17", "");//税额
                                    } else {
                                        map2.put("content17", oitb.getJsonData().get(finalI).getIPERTAXRATE());//税额
                                    }
                                    if (oitb.getJsonData().get(finalI).getCBATCH() == null) {
                                        map2.put("content18", "");//批号
                                    } else {
                                        map2.put("content18", oitb.getJsonData().get(finalI).getCBATCH());//批号
                                    }
                                    if (oitb.getJsonData().get(finalI).getFQUANTITY() == null) {
                                        map2.put("content19", "");//库存数量
                                    }
                                    if (oitb.getJsonData().get(finalI).getCPARENTID() == null) {
                                        map2.put("content20", "");//货位
                                    } else {
                                        map2.put("content20", oitb.getJsonData().get(finalI).getCPARENTID());//货位
                                    }
                                    if (oitb.getJsonData().get(finalI).getFQUANTITY() == null) {
                                        map2.put("content21", "");//出库数量
                                    } else {
                                        map2.put("content21", oitb.getJsonData().get(finalI).getFQUANTITY());//出库数量
                                    }
                                    if (oitb.getJsonData().get(finalI).getCDEMO() == null) {
                                        map2.put("content22", "");//备注
                                    } else {
                                        map2.put("content22", oitb.getJsonData().get(finalI).getCDEMO());//备注
                                    }
                                    if (oitb.getJsonData().get(finalI).getCPOSCODE() == null) {
                                        map2.put("content23", "");//货位编码
                                    } else {
                                        map2.put("content23", oitb.getJsonData().get(finalI).getCPOSCODE());//货位编码
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
                                    try {
                                        //计算总价
                                        dbl_number += Double.parseDouble(oitb.getJsonData().get(finalI).getTAXAMOUNT());
                                        tv_total_prices.setText(dbl_number + "");
                                        //计算数量
                                        amount += Integer.parseInt(oitb.getJsonData().get(finalI).getFQUANTITY());
                                        tv_amount.setText(amount + "");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }

    }

    //
    public void getStockRemovalInfo(String param) {
        try {
            RequestParams params = new RequestParams();
            params.put("hprGuid", param);
            //出库单信息接口
            HttpNetworkRequest.get("goods/rs/hpoutstorage", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    sri = gson.fromJson(rawResponse, StockRemovalInfo.class);
                    //赋值方法
                    setStockRemovalInfo(sri);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {

                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
    }

    //将表体转成json
    private void setTableBody() {
        try {
            UPloadOutBody uPloadOutBody = new UPloadOutBody();
            Gson gson = new Gson();
            jsonArray = new JSONArray();
            for (int i = 0; i < listscq.size(); i++) {
                uPloadOutBody.setHprGuidCh(list_HprGuidCh.get(i));
                uPloadOutBody.setCbatch(listscq.get(i).get("content18"));//批号
                uPloadOutBody.setIbatch("1");
                uPloadOutBody.setCinvcode(listscq.get(i).get("content1"));//物资编码
                uPloadOutBody.setCinvname(listscq.get(i).get("content4"));//物资名称
                uPloadOutBody.setCinvstd(listscq.get(i).get("content6"));//规格型号
                uPloadOutBody.setCcomunitname(listscq.get(i).get("content9"));//主计量单位
                uPloadOutBody.setFquantity(listscq.get(i).get("content21"));//出库数量
                uPloadOutBody.setFtaxprice(listscq.get(i).get("content14"));//含税单价
                uPloadOutBody.setTaxamount(listscq.get(i).get("content15"));//含税金额
                uPloadOutBody.setCdemo(listscq.get(i).get("content22"));//备注
                uPloadOutBody.setCparentid(listscq.get(i).get("content8"));//货位名称
                uPloadOutBody.setFunitprice(listscq.get(i).get("content12"));//无税单价
                uPloadOutBody.setFmondey(listscq.get(i).get("content13"));//无税金额
                uPloadOutBody.setIpertaxrate(listscq.get(i).get("content17"));//税额
                uPloadOutBody.setFtaxrate(listscq.get(i).get("content16"));//税率
                uPloadOutBody.setCposcode(listscq.get(i).get("content23"));//货位编码
                uPloadOutBody.setHpiGuid(list_hpiguid.get(i));//

                uPloadOutBody.setHpiaGuid("");
                uPloadOutBody.setHproGuid("");
                uPloadOutBody.setHppGuidCh("");
                uPloadOutBody.setDelFlag(listscq.get(i).get("delFlag"));
                String json_body = gson.toJson(uPloadOutBody);
                try {
                    JSONObject jsonObject = new JSONObject(json_body);
                    jsonArray.put(i, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("表体：" + jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsOutBound.this, "出错", Toast.LENGTH_SHORT).show();
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

    public void show(String str) {
        Toast.makeText(ActivityMaterialsOutBound.this, str, Toast.LENGTH_SHORT).show();
    }

    //删除出库单
    public void deleteCKD(View view) {
        try {
            //点击弹出对话框
            new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("是否要删除该出库单号？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isChandler()) {//如果审核人为空，那么说明未审核，可以删除
                                //调用删除入库单详细信息单条接口
                                HttpNetworkRequest.delete("goods/rs/hpPutstorage?str=" + sri.getHPRGUID(), new BaseHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(rawResponse);
                                            if (jsonObject.getString("message").equals("删除成功")) {
                                                Toast.makeText(ActivityMaterialsOutBound.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                finish();
                                                //刷新出库单的数据
                                                ActivityMaterialOutboundOrder.materialOutboundOrder.onRefresh();
                                                ActivityMaterialOutboundOrder.materialOutboundOrder.showLayout();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(ActivityMaterialsOutBound.this, "网络请求成功，返回数据错误，删除不成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                                        Toast.makeText(ActivityMaterialsOutBound.this, "网络请求失败，删除不成功", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                });
                            } else {
                                Toast.makeText(ActivityMaterialsOutBound.this, "该单据已被审核，不能删除", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ActivityMaterialsOutBound.this, "放弃删除", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityMaterialsOutBound.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickEnable(boolean is) {
        tv_warehouse.setEnabled(is);
        spin_category.setEnabled(is);
        tv_acquisition_staff.setEnabled(is);
        tv_section.setEnabled(is);
        tv_lingliaodan.setEnabled(is);
        tv_zibianhao.setEnabled(is);
        spin_data_type1.setEnabled(is);
        spin_data_type2.setEnabled(is);
        et_cicode1.setEnabled(is);
        et_cicode2.setEnabled(is);
        et_remark.setEnabled(is);
    }
}
