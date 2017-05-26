package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.GodownEntryList;
import com.zjrfid.materialsmanage.acdbentity.InBoundType;
import com.zjrfid.materialsmanage.acdbentity.WarehouseName;
import com.zjrfid.materialsmanage.adapter.AdapterWzrk;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;
import com.zjrfid.materialsmanage.xListView.XListView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 物资入库单
 */
public class ActivityGoodsReceipt extends Activity implements XListView.IXListViewListener {
    public static ActivityGoodsReceipt goodsReceipt;
    private RelativeLayout relative_screen;
    private LinearLayout Linear_screenUI;
    private XListView list_wzrk;
    public Button btn_delete, btn_cancel, btn_redact, btn_added, btn_inquire;
    private ImageView img_direction;
    private boolean bln_is = true;
    private Spinner spin_huowei, spin_wzmc, spin_gys, spin_state, spin_people;
    private List<String> list_huowei = new ArrayList<>();
    private List<String> list_wzmc = new ArrayList<>();
    private List<String> list_gys = new ArrayList<>();
    private ArrayAdapter<String> adapter_huowei, adapter_wzmc, adapter_gys, adapter_state;
    public TextView tv_time, tv_time2, tv_warehouse;
    private List<String> list_state = new ArrayList<>();
    public HashMap<String, String> hm_goodsreceipt;
    public ArrayList<HashMap<String, String>> listscq = new ArrayList<HashMap<String, String>>();
    public static AdapterWzrk adapterWzrk;
    boolean bln_judge = true;
    private boolean bln_check = true;
    private EditText et_goods_allocation, et_materialname, et_suppliername, et_models, et_document;
    public GodownEntryList gel;//入库单实体类
    private String cparentid, cinvname, cinvstd, ccode, cvenname, dkeepdate1, dkeepdate2, cwhname, crdcode, exam, cinvcode, oldcode, cposcode, cbatch, cvencode;
    private ArrayAdapter<String> adapter;
    public List<String> list_people = new ArrayList<>();
    InBoundType inBoundType;
    private Handler mHandler;
    private String pageN;
    private int page = 2;
    private List<GodownEntryList> list_gel = new ArrayList<>();//存放入库实体类的集合
    private List<String> list = new ArrayList<>();//也是存放主键的集合
    private List<String> list_hprguidch = new ArrayList<>();//也是存放主键的集合
    private List<String> list_cinvcode = new ArrayList<>();//存放物资编码
    private List<String> list_cinvname = new ArrayList<>();//存放物资编码
    private List<String> list_hpiguid = new ArrayList<>();//存放物资编码
    private List<String> list_chandler2 = new ArrayList<>();//审核人,此集合专门用来传值的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_receipt);
        //
        goodsReceipt = this;
        initView();//初始化控件
        initSpinner();//初始化spinner
        NetworkRequest("1");//初始化时显示第一页的数据
        SysApplication.getInstance().addActivity(this);
    }

    //初始化控件
    public void initView() {
        relative_screen = (RelativeLayout) findViewById(R.id.relative_screen);
        Linear_screenUI = (LinearLayout) findViewById(R.id.Linear_screenUI);
        list_wzrk = (XListView) findViewById(R.id.list_wzrk);
        list_wzrk.setPullLoadEnable(true);//设置让它上拉，FALSE为不让上拉，便不加载更多数据
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_cancel = (Button) findViewById(R.id.btn_redact);
        btn_redact = (Button) findViewById(R.id.btn_redact);
        btn_added = (Button) findViewById(R.id.btn_added);
        btn_inquire = (Button) findViewById(R.id.btn_inquire);
        img_direction = (ImageView) findViewById(R.id.img_direction);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        et_goods_allocation = (EditText) findViewById(R.id.et_goods_allocation);
        et_materialname = (EditText) findViewById(R.id.et_materialname);
        et_suppliername = (EditText) findViewById(R.id.et_suppliername);
        et_models = (EditText) findViewById(R.id.et_models);
        et_document = (EditText) findViewById(R.id.et_document);
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);
        //点击筛选
        relative_screen.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   if (bln_is) {
                                                       Linear_screenUI.setVisibility(View.VISIBLE);
                                                       img_direction.setImageResource(R.drawable.up);
                                                       bln_is = false;
                                                       //加载数据
                                                       getWarehouseName();
                                                   } else {
                                                       Linear_screenUI.setVisibility(View.GONE);
                                                       img_direction.setImageResource(R.drawable.down);
                                                       bln_is = true;
                                                   }
                                               }
                                           }
        );
        //点击单据日期
        tv_time.setOnClickListener(new View.OnClickListener()

                                   {
                                       @Override
                                       public void onClick(View v) {
                                           final Calendar c = Calendar.getInstance();
                                           //显示日期选择器
                                           DatePickerDialog dialog = new DatePickerDialog(ActivityGoodsReceipt.this, new DatePickerDialog.OnDateSetListener() {
                                               @Override
                                               public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                   c.set(year, monthOfYear, dayOfMonth);
                                                   tv_time.setText(DateFormat.format("yyy-MM-dd", c));
                                               }
                                           }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                                           dialog.show();
                                       }
                                   }

        );
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        //点击单据日期
        tv_time2.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            final Calendar c = Calendar.getInstance();
                                            //显示日期选择器
                                            DatePickerDialog dialog = new DatePickerDialog(ActivityGoodsReceipt.this, new DatePickerDialog.OnDateSetListener() {
                                                @Override
                                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                    c.set(year, monthOfYear, dayOfMonth);
                                                    tv_time2.setText(DateFormat.format("yyy-MM-dd", c));
                                                }
                                            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                                            dialog.show();
                                        }
                                    }

        );
        //点击请选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGoodsReceipt.this, Activity_TreeView.class);
                intent.putExtra("flag", 0);
                startActivity(intent);
            }
        });
        adapterWzrk = new AdapterWzrk(ActivityGoodsReceipt.this, listscq);
        list_wzrk.setAdapter(adapterWzrk);
        list_wzrk.setXListViewListener(this);
        mHandler = new Handler();

        //点击listview
        list_wzrk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 如果没有点编辑，则点击item就直接跳转，如果点击了编辑，则点击item只能选中CheckBox
                 */
                if (bln_judge) {
                    Intent intent = new Intent(ActivityGoodsReceipt.this, ActivityMaterialsInBound.class);
                    intent.putExtra("get", 1);
                    intent.putExtra("position", position);
                    intent.putExtra("amount", "");
                    intent.putExtra("cinvname", list_cinvname.get(position - 1));//物资名称
                    intent.putExtra("hpiguid", list_hpiguid.get(position - 1));//hpiguid
                    intent.putExtra("hprGuid", list.get(position - 1));//把主键传过去
                    intent.putExtra("hprGuidch", list_hprguidch.get(position - 1));//
                    intent.putExtra("cinvcode", list_cinvcode.get(position - 1));//把物资编码传过去
                    intent.putExtra("chandler", list_chandler2.get(position - 1));//审核人
                    startActivity(intent);
                } else {
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    AdapterWzrk.ViewHold holder = (AdapterWzrk.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    // 将CheckBox的选中状况记录下来
                    // 调整选定条目
                    if (holder.cb.isChecked() == true) {
                        listscq.get(position - 1).put("flag", "true");
                        change(true, position);
                    } else {
                        listscq.get(position - 1).put("flag", "false");
                        change(false, position);
                    }

                }
            }
        });
    }

    //初始化spinner
    public void initSpinner() {
        spin_huowei = (Spinner) findViewById(R.id.spin_huowei);
        spin_wzmc = (Spinner) findViewById(R.id.spin_wzmc);
        spin_gys = (Spinner) findViewById(R.id.spin_gys);
        spin_state = (Spinner) findViewById(R.id.spin_state);
        spin_people = (Spinner) findViewById(R.id.spin_people);//入库类别

        list_huowei.add("货位名称");
        list_huowei.add("货位编码");

        list_wzmc.add("物资名称");
        list_wzmc.add("物资编码");
        list_wzmc.add("旧编码");

        list_gys.add("供应商名称");
        list_gys.add("供应商编码");

        list_state.add("请选择审核状态");
        list_state.add("已审核");
        list_state.add("未审核");

        list_people.add("请选择入库类别");
        //获取入库类别
        getInBoundType();

        //入库类别
        adapter = new ArrayAdapter<String>(ActivityGoodsReceipt.this, android.R.layout.simple_spinner_item, list_people);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_people.setAdapter(adapter);
        //货位
        adapter_huowei = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_huowei);
        //为适配器设置下拉列表下拉时的菜单样式。
        adapter_huowei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到下拉列表上
        spin_huowei.setAdapter(adapter_huowei);
        //物资名称
        adapter_wzmc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_wzmc);
        adapter_wzmc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_wzmc.setAdapter(adapter_wzmc);
        //供应商
        adapter_gys = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_gys);
        adapter_gys.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_gys.setAdapter(adapter_gys);
        //审核状态
        adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list_state);
        //为适配器设置下拉列表下拉时的菜单样式。
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_state.setAdapter(adapter_state);
    }


    //点击回退
    public void back(View view) {
        finish();
    }

    //点击查询
    public void query(View view) {
        onRefresh();
    }

    //给集合添加数据
    public void addData(int n) {
        for (int i = 0; i < n; i++) {
            hm_goodsreceipt = new HashMap<String, String>();
            hm_goodsreceipt.put("content1", gel.getJsonData().getList().get(i).getDKEEPDATE());//入库日期
            hm_goodsreceipt.put("content2", gel.getJsonData().getList().get(i).getCCODE());//入库单据号
            hm_goodsreceipt.put("content3", gel.getJsonData().getList().get(i).getCWHNAME());//仓库
            hm_goodsreceipt.put("content4", gel.getJsonData().getList().get(i).getCRDNAME());//入库类别
            hm_goodsreceipt.put("content5", gel.getJsonData().getList().get(i).getCPERSONNAME());//收料员
            hm_goodsreceipt.put("content6", gel.getJsonData().getList().get(i).getORGNAME());//部门
            hm_goodsreceipt.put("content7", gel.getJsonData().getList().get(i).getCVENNAME());//供货单位
            hm_goodsreceipt.put("content8", gel.getJsonData().getList().get(i).getCMEMO());//备注（表头）
            hm_goodsreceipt.put("content9", gel.getJsonData().getList().get(i).getCICODE1());//单据号1
            hm_goodsreceipt.put("content10", gel.getJsonData().getList().get(i).getCPTNAME1());//原始单据类型1
            hm_goodsreceipt.put("content11", gel.getJsonData().getList().get(i).getCICODE2());//单据号2
            hm_goodsreceipt.put("content12", gel.getJsonData().getList().get(i).getCPTNAME2());//原始单据类型2
            hm_goodsreceipt.put("content13", gel.getJsonData().getList().get(i).getCMAKER());//制单人
            hm_goodsreceipt.put("content14", gel.getJsonData().getList().get(i).getCHANDLER());//审核人
            hm_goodsreceipt.put("content15", gel.getJsonData().getList().get(i).getCREATEDT());//制单日期
            hm_goodsreceipt.put("content16", gel.getJsonData().getList().get(i).getDVERIDAATE());//审核日期
            hm_goodsreceipt.put("content17", gel.getJsonData().getList().get(i).getCINVNAME());//物资名称
            hm_goodsreceipt.put("content18", gel.getJsonData().getList().get(i).getCINVCODE());//物资编码
            hm_goodsreceipt.put("content19", gel.getJsonData().getList().get(i).getCBATCH());//批号
            hm_goodsreceipt.put("content20", gel.getJsonData().getList().get(i).getCINVSTD());//规格型号
            hm_goodsreceipt.put("content21", gel.getJsonData().getList().get(i).getCCOMUNITNAME());//单位
            hm_goodsreceipt.put("content22", gel.getJsonData().getList().get(i).getFQUANTITY());//数量
            hm_goodsreceipt.put("content23", gel.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
            hm_goodsreceipt.put("content24", gel.getJsonData().getList().get(i).getFMONDEY());//无税金额
            hm_goodsreceipt.put("content25", gel.getJsonData().getList().get(i).getFTAXPRICE());//含税单价
            hm_goodsreceipt.put("content26", gel.getJsonData().getList().get(i).getTAXAMOUNT());//含税金额
            hm_goodsreceipt.put("content27", gel.getJsonData().getList().get(i).getIPERTAXRATE());//税额
            hm_goodsreceipt.put("content28", gel.getJsonData().getList().get(i).getFTAXRATE());//税率
            hm_goodsreceipt.put("content29", gel.getJsonData().getList().get(i).getCDEMO());//备注（表体）
            hm_goodsreceipt.put("content30", gel.getJsonData().getList().get(i).getCPARENTID());//货位
            hm_goodsreceipt.put("content31", 3 + "");
            hm_goodsreceipt.put("flag", "false");
            hm_goodsreceipt.put("hprguid", gel.getJsonData().getList().get(i).getHPRGUID());
            listscq.add(hm_goodsreceipt);
            dataChanged();
            //隐藏复选框
            adapterWzrk.hind(1);
        }
    }

    //刷新适配器
    public void dataChanged() {
        adapterWzrk.notifyDataSetChanged();
    }

    //点击删除
    public void delete(View view) {
        try {

            int count = 0;
            List<String> list_chandler_selected = new ArrayList<>();
            List<String> list_hprguid_selected = new ArrayList<>();

            for (int i = 0; i < listscq.size(); i++) {
                if (listscq.get(i).get("flag").equals("true")) {
                    count++;
                    if (!list_hprguid_selected.contains(listscq.get(i).get("hprguid"))) {
                        list_chandler_selected.add(listscq.get(i).get("content14"));
                        list_hprguid_selected.add(listscq.get(i).get("hprguid"));
                    }
                }
            }
            if (count == 0) {
                Toast.makeText(this, "请选择一项删除", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int i = 0; i < list_hprguid_selected.size(); i++) {
                if (list_chandler_selected.get(i) == null || list_chandler_selected.get(i).equals("")) {//如果审核人为空，那么说明未审核，可以删除
                    //调用删除入库单详细信息单条接口
                    HttpNetworkRequest.delete("goods/rs/hpPutstorage?str=" + list_hprguid_selected.get(i), new BaseHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                            try {
                                JSONObject jsonObject = new JSONObject(rawResponse);
                                if (jsonObject.getString("message").equals("删除成功")) {
                                    Toast.makeText(ActivityGoodsReceipt.this, "删除成功", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //显示布局
                            showLayout();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                            Toast.makeText(ActivityGoodsReceipt.this, "该单据删除请求失败，不能删除", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    });
                } else {
                    Toast.makeText(ActivityGoodsReceipt.this, "该单据已被审核，不能删除", Toast.LENGTH_SHORT).show();
                }
            }
            //刷新数据，for循环执行完再执行
            onRefresh();
            // 通知列表数据修改，刷新适配器
            dataChanged();
            //将所有选中的条目删除
            for (int i = 0; i < listscq.size(); i++) {
                listscq.get(i).put("flag", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityGoodsReceipt.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //点击新增
    public void Added(View view) {
        startActivity(new Intent(this, ActivityMaterialsInBound.class));
    }

    //点击清空
    public void clear(View view) {
        et_goods_allocation.setText("");
        et_materialname.setText("");
        et_suppliername.setText("");
        et_models.setText("");
        tv_time.setText("点击选择日期");
        tv_time2.setText("点击选择日期");
        et_document.setText("");
        spin_people.setSelection(0);
        tv_warehouse.setText("请选择仓库");
        spin_state.setSelection(0);
    }

    public void btn_clear(View view) {
        tv_time.setText("点击选择日期");
        tv_time2.setText("点击选择日期");
    }

    public void tv_clear(View view) {
        tv_warehouse.setText("请选择仓库");
    }

    //点击编辑
    public void redact(View view) {
        //点击编辑显示checkbox
        if (bln_judge) {
            btn_redact.setText("取消");
            adapterWzrk.hind(0);//显示复选框
            btn_delete.setVisibility(View.VISIBLE);//删除按钮显示
            btn_added.setVisibility(View.GONE);//新增按钮隐藏
            btn_inquire.setVisibility(View.VISIBLE);//查看按钮显示
            bln_judge = false;
        } else {
            btn_redact.setText("编辑");
            btn_inquire.setText("全选");
            adapterWzrk.hind(1);//隐藏复选框
            btn_delete.setVisibility(View.GONE);//删除按钮隐藏
            btn_added.setVisibility(View.VISIBLE);//新增按钮显示
            btn_inquire.setVisibility(View.GONE);//查看按钮隐藏
            bln_judge = true;
            //点击取消后，把所有选中状态清除
            for (int j = 0; j < listscq.size(); j++) {
                listscq.get(j).put("flag", "false");
            }
        }
        dataChanged();//因为判断里有显示或隐藏复选框，所以要刷新适配器
    }

    //点击全选
    public void check(View view) {
        if (bln_check) {
            btn_inquire.setText("全不选");
            adapterWzrk.checkAll(2);//参数为2表示全选
            for (int k = 0; k < listscq.size(); k++) {
                listscq.get(k).put("flag", "true");

            }
            bln_check = false;
        } else {
            btn_inquire.setText("全选");
            adapterWzrk.checkAll(3);//参数为3表示全不选
            for (int k = 0; k < listscq.size(); k++) {
                listscq.get(k).put("flag", "false");

            }
            bln_check = true;
        }
        dataChanged();
    }

    //获取仓库名称
    public void getWarehouseName() {
        try {
            HttpNetworkRequest.get("goods/rs/hpWarehouseRefer", new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    if (statusCode == 200) {
                        Gson gson = new Gson();
                        gson.fromJson(rawResponse, WarehouseName.class);
                    } else {
                        Toast.makeText(ActivityGoodsReceipt.this, "出错了", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    Toast.makeText(ActivityGoodsReceipt.this, "出错了，请检查网络", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityGoodsReceipt.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //获取参数的方法
    public void getParams() {
        switch (spin_huowei.getSelectedItem().toString()) {
            case "货位名称":
                if (et_goods_allocation.getText().toString().equals("")) {
                    cparentid = "";
                    cposcode="";
                } else {
                    cparentid = et_goods_allocation.getText().toString().trim();
                    cposcode="";
                }
                break;
            case "货位编码":
                if (et_goods_allocation.getText().toString().equals("")) {
                    cposcode = "";
                    cparentid="";
                } else {
                    cposcode = et_goods_allocation.getText().toString().trim();
                    cparentid="";
                }
                break;
        }
        switch (spin_wzmc.getSelectedItem().toString()) {
            case "物资名称":
                if (et_materialname.getText().toString().equals("")) {
                    cinvname = "";
                    cinvcode = "";
                    oldcode = "";
                    cbatch = "";
                } else {
                    cinvname = et_materialname.getText().toString().trim();
                    cinvcode = "";
                    oldcode = "";
                    cbatch = "";
                }
                break;
            case "物资编码":
                if (et_materialname.getText().toString().equals("")) {
                    cinvname = "";
                    cinvcode = "";
                    oldcode = "";
                    cbatch = "";
                } else {
                    cinvcode = et_materialname.getText().toString().trim();
                    cinvname = "";
                    oldcode = "";
                    cbatch = "";
                }
                break;
            case "旧编码":
                if (et_materialname.getText().toString().equals("")) {
                    cinvname = "";
                    cinvcode = "";
                    oldcode = "";
                    cbatch = "";
                } else {
                    oldcode = et_materialname.getText().toString().trim();
                    cinvname = "";
                    cinvcode = "";
                    cbatch = "";
                }
                break;
            case "批次":
                if (et_materialname.getText().toString().equals("")) {
                    cinvname = "";
                    cinvcode = "";
                    oldcode = "";
                    cbatch = "";
                } else {
                    cbatch = et_materialname.getText().toString().trim();
                    cinvname = "";
                    cinvcode = "";
                    oldcode = "";
                }
                break;
        }
        switch (spin_gys.getSelectedItem().toString()) {
            case "供应商名称":
                //供应商名称
                if (et_suppliername.getText().toString().equals("")) {
                    cvenname = "";
                    cposcode = "";
                } else {
                    cvenname = et_suppliername.getText().toString().trim();
                    cposcode = "";
                }
                break;
            case "供应商编码":
                if (et_suppliername.getText().toString().equals("")) {
                    cposcode = "";
                    cvenname = "";
                } else {
                    cposcode = et_suppliername.getText().toString().trim();
                    cvenname = "";
                }
                break;
        }
        //规格型号
        if (et_models.getText().toString().equals("")) {
            cinvstd = "";
        } else {
            cinvstd = et_models.getText().toString();
        }
        //单据号
        if (et_document.getText().toString().equals("")) {
            ccode = "";
        } else {
            ccode = et_document.getText().toString();
        }
        //起始日期
        if (tv_time.getText().toString().equals("点击选择日期")) {
            dkeepdate1 = "";
        } else {
            dkeepdate1 = tv_time.getText().toString();
        }
        //结束日期
        if (tv_time2.getText().toString().equals("点击选择日期")) {
            dkeepdate2 = "";
        } else {
            dkeepdate2 = tv_time2.getText().toString();
        }
        //仓库名称
        if (tv_warehouse.getText().toString().equals("请选择仓库")) {
            cwhname = "";
        } else {
            cwhname = tv_warehouse.getText().toString();
        }
        //入库类别
        if (spin_people.getSelectedItem().toString().equals("请选择入库类别")) {
            crdcode = "";
        } else {
            crdcode = spin_people.getSelectedItem().toString();
        }
        //审核状态
        if (spin_state.getSelectedItem().toString().equals("请选择审核状态")) {
            exam = "";
        } else if (spin_state.getSelectedItem().toString().equals("已审核")) {
            exam = "1";
        } else if (spin_state.getSelectedItem().toString().equals("未审核")) {
            exam = "0";
        }
    }

    //获取入库类别
    public void getInBoundType() {
        try {
            HttpNetworkRequest.get("goods/rs/hpRdtype", new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    inBoundType = gson.fromJson(rawResponse, InBoundType.class);
                    for (int j = 0; j < inBoundType.getJsonData().getList().size(); j++) {
                        if (!inBoundType.getJsonData().getList().get(j).getAlias().equals("02 出库")) {
                            list_people.add(inBoundType.getJsonData().getList().get(j).getAlias());
                        } else {
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    Log.e("error", "出错鸟");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityGoodsReceipt.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {


                    gel=null;
                    list.clear();
                    pageN = "1";
                    page = 2;
                    bln_is = true;
                    hm_goodsreceipt.clear();
                    bln_judge = true;
                    bln_check = true;
                    listscq.clear();
                    list_gel.clear();
                    list_hprguidch.clear();
                    list_cinvcode.clear();
                    list_cinvname.clear();
                    list_hpiguid.clear();
                    list_chandler2.clear();
                    //显示布局
                    showLayout();
                    NetworkRequest(pageN);
                    dataChanged();
                    onLoad();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }

    // 加载更多
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    if (page < ((Integer.parseInt(gel.getJsonData().getTotalCount()) / 10) + 3)) {
                        NetworkRequest(pageN);
                        dataChanged();
                    } else {
                        Toast.makeText(ActivityGoodsReceipt.this, "最后一页了", Toast.LENGTH_SHORT).show();
                    }

                    onLoad();
                    //显示布局
                    showLayout();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityGoodsReceipt.this, "异常", Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
    }

    /**
     * 停止刷新，
     */
    private void onLoad() {
        list_wzrk.stopRefresh();
        list_wzrk.stopLoadMore();
        list_wzrk.setRefreshTime(getTime());
    }

    //下拉或上拉刷新请求网络的接口
    public void NetworkRequest(final String pageNum) {
        try {
            getParams();//获取接口的参数
            //接口参数
            RequestParams params = new RequestParams();
            params.put("pageNum", pageNum);
            params.put("cwhcodes", ActivityHomePage.wareReadhouseBeanString);
            params.put("cparentid", cparentid);//货位名称
            params.put("cinvname", cinvname);//物资名称
            params.put("cinvstd", cinvstd);//规格型号
            params.put("ccode", ccode);//单据号
            params.put("cinvcode", cinvcode);//物资编码
            params.put("oldcord", oldcode);//旧编码
            params.put("cbatch", cbatch);//批次
            params.put("cposcode", cposcode);//货位编码
            params.put("cvencode", cvencode);//供应商编码
            params.put("cvenname", cvenname);//供应商名称
            params.put("dkeepdate1", dkeepdate1);//起始日期
            params.put("dkeepdate2", dkeepdate2);//结束日期
            params.put("cwhname", cwhname);//仓库名称
            params.put("hpwGuid", "");//
            params.put("iwhpos", "");//
            params.put("iassets", "");//
            params.put("crdcode", crdcode);//入库类别
            params.put("exam", exam);//审核状态
            //查询入库单列表接口
            HttpNetworkRequest.get("goods/rs/hpPutstorage", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                    Gson gson = new Gson();
                    gel = gson.fromJson(rawResponse, GodownEntryList.class);
                    for (int i = 0; i < gel.getJsonData().getList().size(); i++) {
                        list.add(gel.getJsonData().getList().get(i).getHPRGUID());
                        list_cinvcode.add(gel.getJsonData().getList().get(i).getCINVCODE());//添加物资编码
                        list_cinvname.add(gel.getJsonData().getList().get(i).getCINVNAME());//添加物资名称
                        list_hpiguid.add(gel.getJsonData().getList().get(i).getHPIGUID());
                        list_hprguidch.add(gel.getJsonData().getList().get(i).getHPRGUIDCH());
                        list_chandler2.add(gel.getJsonData().getList().get(i).getCHANDLER());
                    }
                    list_gel.add(gel);//将实体类加入集合
                    //如果请求网络成功就加载数据
                    addData(gel.getJsonData().getList().size());
                    pageN = String.valueOf(page++);
                    Toast.makeText(ActivityGoodsReceipt.this, "当前为第 " + pageNum + " 页", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                    System.out.println("失败了");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ActivityGoodsReceipt.this, "出错", Toast.LENGTH_SHORT).show();
        }
    }

    //显示布局
    public void showLayout() {
        Linear_screenUI.setVisibility(View.GONE);
        btn_redact.setVisibility(View.VISIBLE);
        btn_cancel.setVisibility(View.VISIBLE);
        img_direction.setImageResource(R.drawable.down);
        btn_delete.setVisibility(View.GONE);//删除按钮隐藏
        btn_added.setVisibility(View.VISIBLE);//新增按钮显示
        btn_inquire.setVisibility(View.GONE);//查看按钮隐藏
        btn_inquire.setText("全选");
        btn_redact.setText("编辑");
    }

    //同一入库单自动多选
    public void change(boolean bln, int posision) {
        for (int k = 0; k < listscq.size(); k++) {
            if (bln) {
                if (listscq.get(k).get("content2").equals(listscq.get(posision - 1).get("content2"))) {
                    listscq.get(k).put("flag", "true");
                }
            } else {
                if (listscq.get(k).get("content2").equals(listscq.get(posision - 1).get("content2"))) {
                    listscq.get(k).put("flag", "false");
                }
            }
        }
        dataChanged();
    }

    //这里啥都不用写
    public void cancel_chaxun_wzrkd(View view) {
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("HH:mm:ss");//大写的HH是24小时制，小写的hh则是12小时制
        String time = format.format(date);
        return time;
    }

    //点击置顶
    public void stick(View view) {
        list_wzrk.setSelection(0);
    }
}
