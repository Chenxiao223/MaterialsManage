package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.Element;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener4;
import com.zjrfid.materialsmanage.acdbentity.CAllGoodsAllocationData;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocation;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocationRfid;
import com.zjrfid.materialsmanage.adapter.AdapterQueryGoodsAllocation;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 货位绑定
 */

public class ActivityGoodsAllocationRFIDBind extends AppCompatActivity {

    public CgoodsAllocationRfid.JsonDataBean cgarbean = new CgoodsAllocationRfid.JsonDataBean();
    Animation animation;
    public ImageView iv_verdict;
    public Button btn_rfid;
    public Button btn_binding;
    private boolean is = true;
    public TextView textView1, textView2, textView3,
            textView4, textView5, textView6,
            textView7, textView8, textView9, textView10;
    public static ActivityGoodsAllocationRFIDBind goodsAllocationRfidBind;
    private ListView GA_lv_treeview;
    private LinearLayout GA_ll_tree;
    private EditText et_seek;
    private Button clear_query_code_Btn;
    private CgoodsAllocation mCGA = new CgoodsAllocation();
    private TreeViewAdapter treeViewAdapter;
    protected static final int MSG_SHOW_Message = 1;
    protected static final int MSG_SHOW_Message_Result = 2;
    private Spinner spin_code;
    private HashMap<String, Integer> hashMapMySelf = new HashMap<>();
    private HashMap<Integer, String> hashMapParentSelf = new HashMap<>();
    private ArrayList<Element> list_element = new ArrayList<>();
    private ArrayList<Element> elements = new ArrayList<>();
    private List<Integer> list_level = new ArrayList<>();//存节点level
    private ArrayAdapter<String> arrayAdapter;
    private List<String> list_spin = new ArrayList<>();
    private static int flag = 0;
    private String et_query_tiaojian = "";
    public static String cwhcodeFromOuter;//仓库编码
    private static String cwhnameFromOuter;//仓库名称
    private List<String> list_reuslt = new ArrayList<>();
    private List<String> list_code = new ArrayList<>();
    private ListView GA_lv_reult;
    private AdapterQueryGoodsAllocation arrayAdapter_result;
    public static String cwhcodeMain = "0";//仓库编码
    private static String cwhnameMain = "分仓与货位档案";//仓库名称


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_goods_allocation_rfidbind);
        //
        goodsAllocationRfidBind = this;
        SysApplication.getInstance().addActivity(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = getIntent();
        cwhcodeFromOuter = intent.getStringExtra("cwhcode");//仓库编码
        cwhnameFromOuter = intent.getStringExtra("cwhname");//仓库名称

        iv_verdict = (ImageView) findViewById(R.id.iv_verdict);
        btn_rfid = (Button) findViewById(R.id.btn_rfid);
        btn_binding = (Button) findViewById(R.id.btn_binding);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);

        GA_ll_tree = (LinearLayout) findViewById(R.id.GA_ll_tree);
        GA_lv_treeview = (ListView) findViewById(R.id.GA_lv_treeview);

        et_seek = (EditText) findViewById(R.id.et_seek);
        clear_query_code_Btn = (Button) findViewById(R.id.clear_query_code_Btn);
        spin_code = (Spinner) findViewById(R.id.spin_code);

        // 仓库下所有货位
        GA_lv_treeview = (ListView) findViewById(R.id.GA_lv_treeview);
        treeViewAdapter = new TreeViewAdapter(elements, list_element, inflater, this);
        TreeViewItemClickListener4 treeViewItemClickListener = new TreeViewItemClickListener4(treeViewAdapter);
        GA_lv_treeview.setAdapter(treeViewAdapter);
        GA_lv_treeview.setOnItemClickListener(treeViewItemClickListener);
        //搜索的货位
        GA_lv_reult = (ListView) findViewById(R.id.GA_lv_reult);
        arrayAdapter_result = new AdapterQueryGoodsAllocation(this, list_reuslt);
        GA_lv_reult.setAdapter(arrayAdapter_result);
        GA_lv_reult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = list_reuslt.get(position).toString();
                String code = value.substring(0, value.indexOf(" "));
                request_GoodsAllcationRfid(code.substring(0, 2) + code);
                return;
            }
        });


        et_seek.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                listern_searchcode_isempty();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listern_searchcode_isempty();
            }

            @Override
            public void afterTextChanged(Editable s) {
                listern_searchcode_isempty();
                et_query_tiaojian = et_seek.getText().toString();
            }
        });

        list_spin.add("货位名称");
        list_spin.add("货位编码");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_spin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_code.setAdapter(arrayAdapter);
        spin_code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flag = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RequestAllGoodsAllocationInfo();
    }

    /*
    * 网络请求货位列表信息
    * 数据获取成功，保存于mCGA
    * 数据获取失败，mCGA=null
    * flag为true，全查，falge为false，选择查询
     */
    private void RequestGoodsAllocationInfo(String str_cposcode, String str_cparentid, final Boolean flag) {

        RequestParams rp = new RequestParams();
        rp.put("cposcode", str_cposcode);
        rp.put("cparentid", str_cparentid);
        rp.put("hpwGuid", "");


        HttpNetworkRequest.get("goods/rs/hpPositionRefer", rp, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                mCGA = gson.fromJson(s, CgoodsAllocation.class);
                collectGoodsAllocationInfo(mCGA);
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

            }
        });
    }


    /*
  * 网络请求货位列表信息
  * 数据获取成功，保存于mCGA
  * 数据获取失败，mCGA=null
  * flag为true，全查，falge为false，选择查询
   */
    private void RequestAllGoodsAllocationInfo() {


        HttpNetworkRequest.get("goods/rs/hpPosition", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                CAllGoodsAllocationData cGAD = gson.fromJson(s, CAllGoodsAllocationData.class);
                collectAllGoodsAllocationInfo(cGAD);
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

            }
        });
    }


    //flag为true，全查，falge为false，选择查询,   选择不同的适配器和listview
    private void collectAllGoodsAllocationInfo(CAllGoodsAllocationData newCAGA) {

        int size = newCAGA.getJsonData().size();

        //hashMapMySelf （cwhcode, 索引点）,初始parent为“0”
        hashMapMySelf.put(cwhcodeMain, 0);
        //list-element 元素集
        list_element.add(new Element(cwhnameMain, Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false));
        //list_level元素层集
        list_level.add(Element.TOP_LEVEL);

        for (int j = 0; j < size; j++) {
            hashMapMySelf.put(newCAGA.getJsonData().get(j).getCposcode(), j + 1);
            hashMapParentSelf.put(j + 1, newCAGA.getJsonData().get(j).getParentid());
            list_level.add(Element.TOP_LEVEL + toLevelResult(newCAGA.getJsonData().get(j).getCposcode()));
        }
        for (int k = 0; k < size; k++) {
            String pid = hashMapParentSelf.get(k + 1);
            int index = hashMapMySelf.get(pid);
            boolean haschild = hashMapParentSelf.containsValue(newCAGA.getJsonData().get(k).getCposcode());
            list_element.add(new Element(newCAGA.getJsonData().get(k).getAlias(), list_level.get(k + 1), k + 1, index, haschild, false));
        }
        Message msg = new Message();
        msg.obj = list_element;
        msg.arg1 = size;
        msg.what = MSG_SHOW_Message;
        hMsg.sendMessage(msg);


    }


    //flag为true，全查，falge为false，选择查询,   选择不同的适配器和listview
    private void collectGoodsAllocationInfo(CgoodsAllocation newCGA) {
        int size = newCGA.getJsonData().getList().size();
        for (int i = 0; i < size; i++) {
            list_reuslt.add(newCGA.getJsonData().getList().get(i).getAlias());
            list_code.add(newCGA.getJsonData().getList().get(i).getCposcode());
        }
        Message msg = new Message();
        msg.obj = list_reuslt;
        msg.arg1 = size;
        msg.what = MSG_SHOW_Message_Result;
        hMsg.sendMessage(msg);


    }


    private class StartHander extends Handler {
        WeakReference<Activity> mActivityRef;

        StartHander(Activity activity) {
            mActivityRef = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mActivityRef.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {

                case MSG_SHOW_Message:
                    list_element = (ArrayList<Element>) msg.obj;
                    elements.add(list_element.get(0));
                    treeViewAdapter.notifyDataSetChanged();
                    break;
                case MSG_SHOW_Message_Result:
                    arrayAdapter_result.notifyDataSetChanged();
                    break;

            }
        }
    }

    private Handler hMsg = new StartHander(this);

    //字符串长度，来判断层级
    public int toLevelResult(String str) {

        int i = str.length() / 2;
        if (i >= 3) {
            i = 3;
        }
        return i - 1;
    }

    //回退到listview初始页面
    public void back_FirstItem(View view) {

        if (list_element.size() != 0) {
            //  GA_lv_treeview.smoothScrollToPosition(0);
            GA_lv_treeview.setSelection(0);
        }
        if (list_reuslt.size() != 0) {
            // GA_lv_reult.smoothScrollToPosition(0);
            GA_lv_reult.setSelection(0);
        }

    }

    //搜索
    public void goods_allocation_query(View view) {

        if (et_query_tiaojian.equals("") || et_query_tiaojian == null) {
            GA_lv_treeview.setVisibility(View.VISIBLE);
            GA_lv_reult.setVisibility(View.GONE);
        } else {
            GA_lv_treeview.setVisibility(View.GONE);
            GA_lv_reult.setVisibility(View.VISIBLE);
            list_reuslt.clear();
            switch (flag) {
                case 0:
                    RequestGoodsAllocationInfo("", et_query_tiaojian, false);
                    break;
                case 1:
                    RequestGoodsAllocationInfo(et_query_tiaojian, "", false);
                    break;
                default:
                    break;
            }
        }

    }

    //清空查询选项
    public void clear_query_code(View view) {
        et_seek.setText("");
    }


    //清空查询按钮 显示隐藏
    public void listern_searchcode_isempty() {
        if (et_seek.getText().toString() == null || et_seek.getText().toString().equals("")) {
            clear_query_code_Btn.setVisibility(View.GONE);
        } else {
            clear_query_code_Btn.setVisibility(View.VISIBLE);

        }
    }

    //点击箭头
    public void back(View view) {
        finish();
    }

    //货位与RFID绑定查询接口
    public void request_GoodsAllcationRfid(String str_cposcode) {
        RequestParams rp = new RequestParams();
        rp.put("cposcode", str_cposcode);

        iv_verdict.clearAnimation();
        iv_verdict.setVisibility(View.INVISIBLE);
        is = true;
        btn_binding.setText("标签绑定");
        btn_binding.setClickable(false);
        clearGoodsAllocationRfidBoundary();

        HttpNetworkRequest.get("goods/rs/rfid", rp, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                CgoodsAllocationRfid cGAR = gson.fromJson(s, CgoodsAllocationRfid.class);
                if (cGAR.getMessage().equals("操作成功") && cGAR.getJsonData().size() > 0) {
                    cgarbean = cGAR.getJsonData().get(0);
                    setGoodsAllocationRfidBoundary(cgarbean);
                    if (cgarbean.getRFID() == null) {
                        is = true;
                        btn_binding.setText("标签绑定");
                        btn_binding.setClickable(true);
                    } else {
                        is = false;
                        btn_binding.setText("标签解绑");
                        btn_binding.setClickable(true);
                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "网络请求失败，无法获取货位RFID信息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置选中货位显示界面
    public void setGoodsAllocationRfidBoundary(CgoodsAllocationRfid.JsonDataBean newCGARbean) {
        textView1.setText("物品编码:" + newCGARbean.getCINVCODE());//物品编码
        textView2.setText("物品名称:" + newCGARbean.getCINVNAME());//物品名称
        textView3.setText("规格型号:" + newCGARbean.getCINVSTD());//规格型号
        textView4.setText("计量单位:" + newCGARbean.getCCOMUNITNAME());//计量单位
        textView5.setText("仓库编码:" + newCGARbean.getCWHCODE());//仓库编码
        textView6.setText("仓库名称:" + newCGARbean.getCWHNAME());//仓库名称
        textView7.setText("货位编码:" + newCGARbean.getCPOSCODE());//货位编码
        textView8.setText("货位名称:" + newCGARbean.getCPARENTID());//货位名称
        textView9.setText("老编码:" + newCGARbean.getOLDCORD());//老编码
        textView10.setText("RFID:" + newCGARbean.getRFID());//rfid
    }

    //清除选中货位显示界面
    public void clearGoodsAllocationRfidBoundary() {
        textView1.setText("");//物品编码
        textView2.setText("");//物品名称
        textView3.setText("");//规格型号
        textView4.setText("");//计量单位
        textView5.setText("");//仓库编码
        textView6.setText("");//仓库名称
        textView7.setText("");//货位编码
        textView8.setText("");//货位名称
        textView9.setText("");//老编码
        textView10.setText("");//rfid
    }

    //点击绑定
    public void binding(View view) {
        //让图片显示
        if (is == true) {//绑定
            //扫描RFID
            String result_rfid = getRfid();
            if (result_rfid.equals("")) {
                Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
            } else {
                getrfid(result_rfid);//查看rfid是否被绑定过
            }
        } else {//解绑
            uploadInfoNull();//上传数据给服务器
        }
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

    //rfid绑定下
    public void getrfid(final String rfid) {
        try {
            RequestParams params = new RequestParams();
            params.put("rfid", rfid);
            //物资档案接口rfid
            HttpNetworkRequest.get("goods/rs/rfid", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {

                    Gson gson = new Gson();
                    CgoodsAllocationRfid cGAR = gson.fromJson(s, CgoodsAllocationRfid.class);
                    if (cGAR.getMessage().equals("操作成功") && cGAR.getJsonData().size() != 0) {
                        Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "此RFID已绑定其他货位", Toast.LENGTH_SHORT).show();

                    } else {
                        //如果没绑定就上传数据给服务器
                        uploadInfo(rfid);
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "网络请求失败，标签绑定失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //解绑上传
    public void uploadInfoNull() {
        try {
            RequestParams params = new RequestParams();
            params.put("hpiGuid", cgarbean.getHPIGUID());
            params.put("rfid", "null");
            params.put("cwhcode", cgarbean.getCWHCODE());
            params.put("cposcode", cgarbean.getCPOSCODE());
            //修改物资档案信息RFID码单条接口
            HttpNetworkRequest.post("goods/rs/rfid", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                        if (jsonObject.getString("message").equals("success")) {

                            iv_verdict.setVisibility(View.VISIBLE);
                            animation = AnimationUtils.loadAnimation(ActivityGoodsAllocationRFIDBind.this, R.anim.alls);
                            iv_verdict.startAnimation(animation);//动画效果
                            btn_binding.setText("标签绑定");
                            is = true;
                            textView10.setText("RFID：null");

                            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "解绑成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "解绑失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "网络请求失败，解绑失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //绑定信息上传
    public void uploadInfo(final String rfid) {
        try {
            RequestParams params = new RequestParams();
            params.put("hpiGuid", cgarbean.getHPIGUID());
            params.put("rfid", rfid);
            params.put("cwhcode", cgarbean.getCWHCODE());
            params.put("cposcode", cgarbean.getCPOSCODE());
            //修改物资档案信息RFID码单条接口
            HttpNetworkRequest.post("goods/rs/rfid", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                        if (jsonObject.getString("message").equals("success")) {

                            iv_verdict.setVisibility(View.VISIBLE);
                            animation = AnimationUtils.loadAnimation(ActivityGoodsAllocationRFIDBind.this, R.anim.alls);
                            iv_verdict.startAnimation(animation);//动画效果
                            btn_binding.setText("标签解绑");
                            is = false;
                            textView10.setText("RFID：" + rfid);

                            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "绑定成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "绑定失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "网络请求失败，绑定失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //点击扫描RFID
    public void smRfid(View view) {

        String result_rfid = getRfid();
        RequestParams params = new RequestParams();

        iv_verdict.clearAnimation();
        iv_verdict.setVisibility(View.INVISIBLE);
        is = true;
        btn_binding.setText("标签绑定");
        btn_binding.setClickable(false);
        clearGoodsAllocationRfidBoundary();

        if (result_rfid.equals("")) {
            return;
        } else {
            try {
                params.put("rfid", result_rfid);
                //物资档案接口rfid
                HttpNetworkRequest.get("goods/rs/rfid", params, new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, String s, Object o) {

                        Gson gson = new Gson();
                        CgoodsAllocationRfid cGAR = gson.fromJson(s, CgoodsAllocationRfid.class);
                        if (cGAR.getMessage().equals("操作成功") && cGAR.getJsonData().size() != 0) {
                            cgarbean = cGAR.getJsonData().get(0);
                            setGoodsAllocationRfidBoundary(cgarbean);
                            if (!cgarbean.getRFID().equals("") && cgarbean.getRFID() != null) {
                                is = false;
                                btn_binding.setText("标签解绑");
                                btn_binding.setClickable(true);
                            }
                        } else {
                            Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "此RFID未绑定", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                        Toast.makeText(ActivityGoodsAllocationRFIDBind.this, "网络请求失败，标签绑定失败", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(ActivityGoodsAllocationRFIDBind.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}


